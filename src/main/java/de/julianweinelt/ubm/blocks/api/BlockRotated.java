package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockRotated extends Block {
    public static final PropertyEnum<EnumHorizontalFacing> FACING =
            PropertyEnum.create("facing", EnumHorizontalFacing.class);

    public BlockRotated(Material materialIn) {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FACING, EnumHorizontalFacing.NORTH));
    }


    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        boolean lit = (meta & 4) != 0;
        EnumHorizontalFacing facing = EnumHorizontalFacing.values()[meta & 3];
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).ordinal();
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState()
                .withProperty(FACING, EnumHorizontalFacing.fromFacing(placer.getHorizontalFacing().getOpposite()));
    }

    @Override
    @Nonnull
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        EnumFacing facing = rot.rotate(state.getValue(FACING).toEnumFacing());
        return state.withProperty(FACING, EnumHorizontalFacing.fromEnumFacing(facing));
    }

    @Override
    @Nonnull
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        EnumFacing facing = mirrorIn.toRotation(state.getValue(FACING).toEnumFacing()).rotate(EnumFacing.NORTH);
        return state.withProperty(FACING, EnumHorizontalFacing.fromEnumFacing(facing));
    }
}