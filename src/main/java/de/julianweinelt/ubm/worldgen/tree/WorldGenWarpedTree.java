package de.julianweinelt.ubm.worldgen.tree;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenWarpedTree extends WorldGenAbstractTree {

    private IBlockState LOG = ModBlocks.WARPED_STEM.getDefaultState();
    private final IBlockState LEAVES = ModBlocks.WARPED_WART_BLOCK.getDefaultState();

    public WorldGenWarpedTree() {
        super(false);
        LOG = LOG.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        IBlockState soil = world.getBlockState(pos.down());
        if (soil.getBlock() != Blocks.NETHERRACK && soil.getBlock() != ModBlocks.WARPED_NYLIUM) {
            return false;
        }

        int height = 4 + rand.nextInt(4);

        for (int y = 0; y <= height + 1; y++) {
            BlockPos checkPos = pos.up(y);
            if (!world.isAirBlock(checkPos)) return false;
        }

        for (int y = 0; y < height; y++) {
            BlockPos trunkPos = pos.up(y);
            world.setBlockState(trunkPos, LOG);
        }

        BlockPos top = pos.up(height - 1);
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                if (Math.abs(dx) + Math.abs(dz) < 4) {
                    BlockPos leafPos = top.add(dx, 1, dz);
                    if (world.isAirBlock(leafPos)) {
                        world.setBlockState(leafPos, LEAVES);
                    }
                }
            }
        }

        world.setBlockState(top.up(2), LEAVES);

        /*for (int i = 0; i < rand.nextInt(4); i++) {
            BlockPos rootPos = pos.add(rand.nextInt(3) - 1, -1, rand.nextInt(3) - 1);
            if (world.getBlockState(rootPos).getBlock() == Blocks.NETHERRACK) {
                world.setBlockState(rootPos.up(), ModBlocks.WARPED_ROOTS.getDefaultState());
            }
        }*/

        return true;
    }
}
