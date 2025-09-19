package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.worldgen.biome.BiomeSnowySlopes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class PowderSnowWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

        if (world.provider.getDimension() != 0) return;

        BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        if (world.getBiome(chunkPos) instanceof BiomeSnowySlopes) {
            generatePowderSnowPatch(world, random, chunkPos);
        }
    }

    private void generatePowderSnowPatch(World world, Random random, BlockPos pos) {
        int x = pos.getX() + random.nextInt(16);
        int z = pos.getZ() + random.nextInt(16);
        int y = world.getHeight(x, z) - 1;

        BlockPos spawnPos = new BlockPos(x, y, z);

        for (int i = 0; i < 10; i++) {
            BlockPos blockPos = spawnPos.add(random.nextInt(4) - 2, 0, random.nextInt(4) - 2);
            if (world.isAirBlock(blockPos) && world.getBlockState(blockPos.down()).getBlock() == Blocks.STONE) {
                world.setBlockState(blockPos, ModBlocks.POWDER_SNOW.getDefaultState());
            }
        }
    }
}
