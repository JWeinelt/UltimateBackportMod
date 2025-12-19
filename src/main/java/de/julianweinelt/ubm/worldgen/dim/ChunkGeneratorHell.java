package de.julianweinelt.ubm.worldgen.dim;

import java.util.List;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCavesHell;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ChunkGeneratorHell implements IChunkGenerator
{
    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState NETHERRACK = Blocks.NETHERRACK.getDefaultState();
    protected static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    protected static final IBlockState LAVA = Blocks.LAVA.getDefaultState();
    protected static final IBlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
    protected static final IBlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
    private final World world;
    private final boolean generateStructures;
    private final Random rand;
    /** Holds the noise used to determine whether slowsand can be generated at a location */
    private double[] slowsandNoise = new double[256];
    private double[] gravelNoise = new double[256];
    private double[] depthBuffer = new double[256];
    private double[] buffer;
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;
    /** Determines whether slowsand or gravel can be generated at a location */
    private NoiseGeneratorOctaves slowsandGravelNoiseGen;
    /** Determines whether something other than nettherack can be generated at a location */
    private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;
    private final WorldGenFire fireFeature = new WorldGenFire();
    private final WorldGenGlowStone1 lightGemGen = new WorldGenGlowStone1();
    private final WorldGenGlowStone2 hellPortalGen = new WorldGenGlowStone2();
    private final WorldGenerator quartzGen = new WorldGenMinable(Blocks.QUARTZ_ORE.getDefaultState(), 14, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator goldGen = new WorldGenMinable(ModBlocks.NETHER_GOLD_ORE.getDefaultState(), 16, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator magmaGen = new WorldGenMinable(Blocks.MAGMA.getDefaultState(), 33, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenHellLava lavaTrapGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, true);
    private final WorldGenHellLava hellSpringGen = new WorldGenHellLava(Blocks.FLOWING_LAVA, false);
    private final WorldGenBush brownMushroomFeature = new WorldGenBush(Blocks.BROWN_MUSHROOM);
    private final WorldGenBush redMushroomFeature = new WorldGenBush(Blocks.RED_MUSHROOM);
    private MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
    private MapGenBase genNetherCaves = new MapGenCavesHell();
    double[] pnr;
    double[] ar;
    double[] br;
    double[] noiseData4;
    double[] dr;

    public ChunkGeneratorHell(World worldIn, boolean generateStructures, long seed)
    {
        this.world = worldIn;
        this.generateStructures = generateStructures;
        this.rand = new Random(seed);
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.rand, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.rand, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.rand, 16);
        worldIn.setSeaLevel(63);

        InitNoiseGensEvent.ContextHell ctx =
                new InitNoiseGensEvent.ContextHell(lperlinNoise1, lperlinNoise2, perlinNoise1, slowsandGravelNoiseGen,
                        netherrackExculsivityNoiseGen, scaleNoise, depthNoise);
        ctx = TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.lperlinNoise1 = ctx.getLPerlin1();
        this.lperlinNoise2 = ctx.getLPerlin2();
        this.perlinNoise1 = ctx.getPerlin();
        this.slowsandGravelNoiseGen = ctx.getPerlin2();
        this.netherrackExculsivityNoiseGen = ctx.getPerlin3();
        this.scaleNoise = ctx.getScale();
        this.depthNoise = ctx.getDepth();
        this.genNetherBridge = (MapGenNetherBridge) TerrainGen.getModdedMapGen(genNetherBridge, InitMapGenEvent.EventType.NETHER_BRIDGE);
        this.genNetherCaves = TerrainGen.getModdedMapGen(genNetherCaves, InitMapGenEvent.EventType.NETHER_CAVE);
    }

    public void prepareHeights(int p_185936_1_, int p_185936_2_, ChunkPrimer primer)
    {
        int i = 4;
        int j = this.world.getSeaLevel() / 2 + 1;
        int k = 5;
        int l = 17;
        int i1 = 5;
        this.buffer = this.getHeights(this.buffer, p_185936_1_ * 4, 0, p_185936_2_ * 4, 5, 17, 5);

        for (int j1 = 0; j1 < 4; ++j1) {
            for (int k1 = 0; k1 < 4; ++k1) {
                for (int l1 = 0; l1 < 16; ++l1) {
                    double d0 = 0.125D;
                    double d1 = this.buffer[((j1) * 5 + k1) * 17 + l1];
                    double d2 = this.buffer[((j1) * 5 + k1 + 1) * 17 + l1];
                    double d3 = this.buffer[((j1 + 1) * 5 + k1) * 17 + l1];
                    double d4 = this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1];
                    double d5 = (this.buffer[((j1) * 5 + k1) * 17 + l1 + 1] - d1) * 0.125D;
                    double d6 = (this.buffer[((j1) * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125D;
                    double d7 = (this.buffer[((j1 + 1) * 5 + k1) * 17 + l1 + 1] - d3) * 0.125D;
                    double d8 = (this.buffer[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125D;

                    for (int i2 = 0; i2 < 8; ++i2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int j2 = 0; j2 < 4; ++j2) {
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.25D;

                            for (int k2 = 0; k2 < 4; ++k2) {
                                IBlockState iblockstate = null;

                                if (l1 * 8 + i2 < j) {
                                    iblockstate = LAVA;
                                }

                                if (d15 > 0.0D) {
                                    iblockstate = NETHERRACK;
                                }

                                int l2 = j2 + j1 * 4;
                                int i3 = i2 + l1 * 8;
                                int j3 = k2 + k1 * 4;
                                primer.setBlockState(l2, i3, j3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void buildSurfaces(int chunkX, int chunkZ, ChunkPrimer primer) {
        Biome[] biomes = this.world.getBiomeProvider().getBiomes(null, chunkX * 16, chunkZ * 16, 16, 16);

        int seaLevel = this.world.getSeaLevel();

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                Biome biome = biomes[z + x * 16];

                IBlockState topBlock = biome.topBlock != null ? biome.topBlock : NETHERRACK;
                IBlockState fillerBlock = biome.fillerBlock != null ? biome.fillerBlock : NETHERRACK;

                int surfaceDepth = 4;
                int depth = -1;

                for (int y = 127; y >= 0; --y) {
                    IBlockState current = primer.getBlockState(x, y, z);

                    if (current == AIR) {
                        depth = -1;
                    } else if (current == NETHERRACK) {
                        if (depth == -1) {
                            primer.setBlockState(x, y, z, topBlock);
                            depth = surfaceDepth;
                        } else if (depth > 0) {
                            primer.setBlockState(x, y, z, fillerBlock);
                            depth--;
                        }
                    }
                }
            }
        }
    }



    /**
     * Generates the chunk at the specified position, from scratch
     */
    @Nonnull
    public Chunk generateChunk(int x, int z) {
        this.rand.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.prepareHeights(x, z, chunkprimer);
        this.buildSurfaces(x, z, chunkprimer);
        this.genNetherCaves.generate(this.world, x, z, chunkprimer);

        if (this.generateStructures) this.genNetherBridge.generate(this.world, x, z, chunkprimer);


        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
        Biome[] abiome = this.world.getBiomeProvider().getBiomes((Biome[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte)Biome.getIdForBiome(abiome[i]);
        }

        chunk.resetRelightChecks();
        return chunk;
    }

    private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_) {
        if (p_185938_1_ == null) {
            p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
        }

        ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return event.getNoisefield();

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
        this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D, 100.0D);
        this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 8.555150000000001D, 34.2206D, 8.555150000000001D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        int i = 0;
        double[] adouble = new double[p_185938_6_];

        for (int j = 0; j < p_185938_6_; ++j) {
            adouble[j] = Math.cos((double)j * Math.PI * 6.0D / (double)p_185938_6_) * 2.0D;
            double d2 = (double)j;

            if (j > p_185938_6_ / 2) {
                d2 = (double)(p_185938_6_ - 1 - j);
            }

            if (d2 < 4.0D) {
                d2 = 4.0D - d2;
                adouble[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < p_185938_5_; ++l) {
            for (int i1 = 0; i1 < p_185938_7_; ++i1) {
                double d3 = 0.0D;

                for (int k = 0; k < p_185938_6_; ++k) {
                    double d4 = adouble[k];
                    double d5 = this.ar[i] / 512.0D;
                    double d6 = this.br[i] / 512.0D;
                    double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
                    double d8;

                    if (d7 < 0.0D) {
                        d8 = d5;
                    }
                    else if (d7 > 1.0D) {
                        d8 = d6;
                    }
                    else {
                        d8 = d5 + (d6 - d5) * d7;
                    }

                    d8 = d8 - d4;

                    if (k > p_185938_6_ - 4) {
                        double d9 = (double)((float)(k - (p_185938_6_ - 4)) / 3.0F);
                        d8 = d8 * (1.0D - d9) + -10.0D * d9;
                    }

                    if ((double)k < 0.0D) {
                        double d10 = (0.0D - (double)k) / 4.0D;
                        d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
                        d8 = d8 * (1.0D - d10) + -10.0D * d10;
                    }

                    p_185938_1_[i] = d8;
                    ++i;
                }
            }
        }

        return p_185938_1_;
    }

    /**
     * Generate initial structures in this chunk, e.g. mineshafts, temples, lakes, and dungeons
     */
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.world.getBiome(blockpos.add(16, 0, 16));
        ChunkPos chunkpos = new ChunkPos(x, z);
        this.genNetherBridge.generateStructure(this.world, this.rand, chunkpos);

            if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA))
                for (int k = 0; k < 8; ++k) {
                    this.hellSpringGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
                }

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.FIRE))
            for (int i1 = 0; i1 < this.rand.nextInt(this.rand.nextInt(10) + 1) + 1; ++i1) {
                this.fireFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.GLOWSTONE)) {
            for (int j1 = 0; j1 < this.rand.nextInt(this.rand.nextInt(10) + 1); ++j1) {
                this.lightGemGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(120) + 4, this.rand.nextInt(16) + 8));
            }

            for (int k1 = 0; k1 < 10; ++k1) {
                this.hellPortalGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world, this.rand, chunkpos));

        if (TerrainGen.decorate(this.world, this.rand, chunkpos, DecorateBiomeEvent.Decorate.EventType.SHROOM)) {
            if (this.rand.nextBoolean()) {
                this.brownMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }

            if (this.rand.nextBoolean()) {
                this.redMushroomFeature.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + 8, this.rand.nextInt(128), this.rand.nextInt(16) + 8));
            }
        }


        if (TerrainGen.generateOre(this.world, this.rand, quartzGen, blockpos, OreGenEvent.GenerateMinable.EventType.QUARTZ))
            for (int l1 = 0; l1 < 16; ++l1) {
                this.quartzGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
            }

        if (TerrainGen.generateOre(this.world, this.rand, quartzGen, blockpos, OreGenEvent.GenerateMinable.EventType.GOLD))
            for (int l1 = 0; l1 < 14; ++l1) {
                this.goldGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), this.rand.nextInt(108) + 10, this.rand.nextInt(16)));
            }

        int i2 = this.world.getSeaLevel() / 2 + 1;

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_MAGMA))
            for (int l = 0; l < 4; ++l) {
                this.magmaGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16), i2 - 5 + this.rand.nextInt(10), this.rand.nextInt(16)));
            }

        if (TerrainGen.populate(this, this.world, this.rand, x, z, false, PopulateChunkEvent.Populate.EventType.NETHER_LAVA2))
            for (int j2 = 0; j2 < 16; ++j2) {
                int offset = net.minecraftforge.common.ForgeModContainer.fixVanillaCascading ? 8 : 0; // MC-117810
                this.lavaTrapGen.generate(this.world, this.rand, blockpos.add(this.rand.nextInt(16) + offset, this.rand.nextInt(108) + 10, this.rand.nextInt(16) + offset));
            }

        biome.decorate(this.world, this.rand, new BlockPos(i, 0, j));

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world, this.rand, blockpos));

        BlockFalling.fallInstantly = false;
    }

    /**
     * Called to generate additional structures after initial worldgen, used by ocean monuments
     */
    public boolean generateStructures(@Nonnull Chunk chunkIn, int x, int z)
    {
        return false;
    }


    @Nonnull
    public List<Biome.SpawnListEntry> getPossibleCreatures(@Nonnull EnumCreatureType creatureType, @Nonnull BlockPos pos) {
        if (creatureType == EnumCreatureType.MONSTER) {
            if (this.genNetherBridge.isInsideStructure(pos)) {
                return this.genNetherBridge.getSpawnList();
            }

            if (this.genNetherBridge.isPositionInStructure(this.world, pos) && this.world.getBlockState(pos.down()).getBlock() == Blocks.NETHER_BRICK) {
                return this.genNetherBridge.getSpawnList();
            }
        }

        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    public BlockPos getNearestStructurePos(@Nonnull World worldIn, @Nonnull String structureName, @Nonnull BlockPos position, boolean findUnexplored) {
        return "Fortress".equals(structureName) && this.genNetherBridge != null ? this.genNetherBridge.getNearestStructurePos(worldIn, position, findUnexplored) : null;
    }

    public boolean isInsideStructure(@Nonnull World worldIn, @Nonnull String structureName, @Nonnull BlockPos pos) {
        return "Fortress".equals(structureName) && this.genNetherBridge != null && this.genNetherBridge.isInsideStructure(pos);
    }

    /**
     * Recreates data about structures intersecting given chunk (used for example by getPossibleCreatures), without
     * placing any blocks. When called for the first time before any chunk is generated - also initializes the internal
     * state needed by getPossibleCreatures.
     */
    public void recreateStructures(@Nonnull Chunk chunkIn, int x, int z) {
        this.genNetherBridge.generate(this.world, x, z, (ChunkPrimer)null);
    }
}