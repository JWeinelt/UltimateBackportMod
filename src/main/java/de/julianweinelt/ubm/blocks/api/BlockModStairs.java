package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockModStairs extends BlockStairs {
    public BlockModStairs(IBlockState state, String name) {
        super(state);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumFacing facing = state.getValue(FACING);
        boolean isTop = state.getValue(HALF) == BlockStairs.EnumHalf.TOP;

        AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);

        switch(facing) {
            case NORTH:
                bb = isTop ? new AxisAlignedBB(0, 0.5, 0, 1, 1, 1) : new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
                break;
            case SOUTH:
                bb = isTop ? new AxisAlignedBB(0, 0.5, 0, 1, 1, 1) : new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
                break;
            case WEST:
                bb = isTop ? new AxisAlignedBB(0, 0.5, 0, 1, 1, 1) : new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
                break;
            case EAST:
                bb = isTop ? new AxisAlignedBB(0, 0.5, 0, 1, 1, 1) : new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
                break;
        }

        return bb;
    }

}
