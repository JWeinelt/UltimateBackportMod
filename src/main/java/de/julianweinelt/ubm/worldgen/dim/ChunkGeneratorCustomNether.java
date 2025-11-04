package de.julianweinelt.ubm.worldgen.dim;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorCustomNether implements IChunkGenerator {
    private final World world;
    private final Random rand;
    private final NoiseGeneratorOctaves netherNoise;

    private final ChunkGeneratorHell vanillaGenerator;

    public ChunkGeneratorCustomNether(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
        this.netherNoise = new NoiseGeneratorOctaves(this.rand, 16);
        this.vanillaGenerator = new ChunkGeneratorHell(world, true, seed);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        return vanillaGenerator.generateChunk(x, z);
    }

    @Override
    public void populate(int x, int z) {
        vanillaGenerator.populate(x, z);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return vanillaGenerator.generateStructures(chunkIn, x, z);
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        List<Biome.SpawnListEntry> entries = vanillaGenerator.getPossibleCreatures(creatureType, pos);
        return entries;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return vanillaGenerator.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        vanillaGenerator.recreateStructures(chunkIn, x, z);
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}