package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMossCarpet extends Block {
    public BlockMossCarpet() {
        super(Material.PLANTS);
        setUnlocalizedName("moss_carpet");
        setRegistryName("moss_carpet");
        setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
    }

    public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(
            0.0D, 0.0D, 0.0D,
            1.0D, 0.0625D, 1.0D
    );

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BOUNDING_BOX;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canBeConnectedTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
        return false;
    }


}
