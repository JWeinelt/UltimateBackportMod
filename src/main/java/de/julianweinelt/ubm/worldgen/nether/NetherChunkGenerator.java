package de.julianweinelt.ubm.worldgen.nether;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NetherChunkGenerator implements IChunkGenerator {

    private static final IBlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
    private static final IBlockState LAVA = Blocks.LAVA.getDefaultState();
    private static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    private static final IBlockState AIR = Blocks.AIR.getDefaultState();

    private final World world;
    private final Random rand;


    private final NoiseGeneratorOctaves noiseMain;
    private final NoiseGeneratorOctaves noiseHeight;
    private final NoiseGeneratorOctaves noiseDepth;


    private final NoiseGeneratorPerlin noiseSurface;


    private double[] bufMain = new double[5 * 17 * 5];
    private double[] bufHeight = new double[5 * 17 * 5];
    private double[] bufDepth = new double[5 * 17 * 5];
    private double[] bufSurface = new double[16 * 16];

    public NetherChunkGenerator(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);

        this.noiseMain = new NoiseGeneratorOctaves(rand, 16);
        this.noiseHeight = new NoiseGeneratorOctaves(rand, 16);
        this.noiseDepth = new NoiseGeneratorOctaves(rand, 8);
        this.noiseSurface = new NoiseGeneratorPerlin(rand, 4);
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {

        rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);

        ChunkPrimer primer = new ChunkPrimer();

        fillTerrain(primer, chunkX, chunkZ);
        buildSurface(primer, chunkX, chunkZ);
        placeBedrock(primer);

        Chunk chunk = new Chunk(world, primer, chunkX, chunkZ);


        Biome[] biomes = world.getBiomeProvider().getBiomes(
                null, chunkX * 16, chunkZ * 16, 16, 16
        );
        byte[] biomeArray = chunk.getBiomeArray();
        for (int i = 0; i < biomeArray.length; i++) {
            biomeArray[i] = (byte) Biome.getIdForBiome(biomes[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }


    private void fillTerrain(ChunkPrimer primer, int cx, int cz) {

        generateNoiseBuffers(cx, cz);

        for (int ix = 0; ix < 4; ix++) {
            for (int iz = 0; iz < 4; iz++) {

                for (int iy = 0; iy < 16; iy++) {

                    double n000 = noiseAt(ix, iy, iz);
                    double n100 = noiseAt(ix + 1, iy, iz);
                    double n010 = noiseAt(ix, iy + 1, iz);
                    double n110 = noiseAt(ix + 1, iy + 1, iz);

                    double n001 = noiseAt(ix, iy, iz + 1);
                    double n101 = noiseAt(ix + 1, iy, iz + 1);
                    double n011 = noiseAt(ix, iy + 1, iz + 1);
                    double n111 = noiseAt(ix + 1, iy + 1, iz + 1);

                    for (int dy = 0; dy < 8; dy++) {

                        double ty = dy / 8.0;

                        double nx00 = lerp(n000, n010, ty);
                        double nx10 = lerp(n100, n110, ty);
                        double nx01 = lerp(n001, n011, ty);
                        double nx11 = lerp(n101, n111, ty);

                        for (int dx = 0; dx < 4; dx++) {

                            double tx = dx / 4.0;

                            double nxz0 = lerp(nx00, nx10, tx);
                            double nxz1 = lerp(nx01, nx11, tx);

                            for (int dz = 0; dz < 4; dz++) {

                                double tz = dz / 4.0;

                                double val = lerp(nxz0, nxz1, tz);

                                int x = ix * 4 + dx;
                                int y = iy * 8 + dy;
                                int z = iz * 4 + dz;

                                if (y >= 128) continue;

                                double heightBias = (y - 64) / 64.0;

                                double density = val - heightBias * 0.4;

                                if (density > 0.05) {
                                    primer.setBlockState(x, y, z, NETHERRACK);
                                } else {
                                    primer.setBlockState(x, y, z, AIR);
                                }

                                if (y < 32 && density < -0.2) {
                                    if (rand.nextFloat() < 0.03f) {
                                        primer.setBlockState(x, y, z, LAVA);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateNoiseBuffers(int cx, int cz) {
        double baseX = cx * 4;
        double baseZ = cz * 4;

        bufMain = noiseMain.generateNoiseOctaves(bufMain, (int) baseX, 0, (int) baseZ, 5, 17, 5, 0.025, 0.025 / 3.0, 0.025);
        bufHeight = noiseHeight.generateNoiseOctaves(bufHeight, (int) baseX, 0, (int) baseZ, 5, 17, 5, 0.025, 0.025 / 3.0, 0.025);
        bufDepth = noiseDepth.generateNoiseOctaves(bufDepth, (int) baseX, 0, (int) baseZ, 5, 17, 5, 0.05, 0.05, 0.05);
    }

    
    private double noiseAt(int ix, int iy, int iz) {
        int idx = (ix * 5 + iz) * 17 + iy;

        double main = bufMain[idx] / 512.0;
        double height = bufHeight[idx] / 512.0;
        double depth = bufDepth[idx] / 20.0;


        double blended;
        if (depth < 0) {
            blended = main + height * Math.max(depth, -1.0);
        } else {
            blended = main + (main - height) * Math.min(depth, 1.0);
        }



        double absY = iy * 8.0;
        double topBias = Math.max(0, (absY - 100.0) / 16.0);
        double bottomBias = Math.max(0, (30.0 - absY) / 16.0);

        blended += bottomBias;
        blended -= topBias;

        double density = blended;
        density -= 0.4;
        density *= 1.8;

        return density;
    }





    
    private void buildSurface(ChunkPrimer primer, int cx, int cz) {
        if (true) return;
        Biome[] biomes = world.getBiomeProvider().getBiomes(
                null, cx * 16, cz * 16, 16, 16
        );

        bufSurface = noiseSurface.getRegion(
                bufSurface, cx * 16, cz * 16, 16, 16, 0.0625, 0.0625, 1.0
        );

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                Biome biome = biomes[z * 16 + x];


                IBlockState top = biome.topBlock;
                IBlockState filler = biome.fillerBlock;


                int depth = (int) (bufSurface[z * 16 + x] / 3.0 + 3.0
                        + rand.nextDouble() * 0.25);

                int remaining = -1;

                for (int y = 127; y >= 0; y--) {
                    IBlockState current = primer.getBlockState(x, y, z);

                    if (current == AIR) {
                        remaining = -1;
                    } else if (current == NETHERRACK) {
                        if (remaining < 0) {
                            remaining = depth;
                            primer.setBlockState(x, y, z, top);
                        } else if (remaining > 0) {
                            remaining--;
                            primer.setBlockState(x, y, z, filler);
                        }
                    }
                }
            }
        }
    }





    
    private void placeBedrock(ChunkPrimer primer) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                for (int y = 0; y <= 4; y++) {
                    if (y == 0 || rand.nextInt(5 - y) == 0) {
                        primer.setBlockState(x, y, z, BEDROCK);
                    }
                }

                for (int y = 127; y >= 123; y--) {
                    if (y == 127 || rand.nextInt(y - 122) == 0) {
                        primer.setBlockState(x, y, z, BEDROCK);
                    }
                }
            }
        }
    }





    @Override
    public void populate(int chunkX, int chunkZ) {




    }

    @Override
    public boolean generateStructures(@Nonnull Chunk chunk, int x, int z) {
        return false;
    }

    @Override
    @Nonnull
    public List<Biome.SpawnListEntry> getPossibleCreatures(
            @Nonnull EnumCreatureType creatureType, @Nonnull BlockPos pos) {
        return world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getNearestStructurePos(@Nonnull World world, @Nonnull String name,
                                           @Nonnull BlockPos pos, boolean findUnexplored) {
        return null;
    }

    @Override
    public boolean isInsideStructure(@Nonnull World world, @Nonnull String name, @Nonnull BlockPos pos) {
        return false;
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunk, int x, int z) {

    }

    private static double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }
}