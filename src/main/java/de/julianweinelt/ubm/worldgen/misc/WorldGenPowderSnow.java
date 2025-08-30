package de.julianweinelt.ubm.worldgen.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenPowderSnow extends WorldGenerator {

    private final IBlockState snowBlock;

    public WorldGenPowderSnow(IBlockState snowBlock) {
        this.snowBlock = snowBlock;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        int radius = 8; 
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos target = pos.add(x, 0, z);
                int y = world.getTopSolidOrLiquidBlock(target).getY();
                BlockPos top = new BlockPos(target.getX(), y, target.getZ());

                if (world.getBlockState(top).getBlock().isFullCube(world.getBlockState(top))) {
                    if (rand.nextFloat() < 0.7f) {
                        world.setBlockState(top.up(), snowBlock, 2);
                    }
                }
            }
        }
        return true;
    }
}
