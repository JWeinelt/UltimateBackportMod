package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.EnumBlockFace;
import de.julianweinelt.ubm.blocks.api.EnumHorizontalFacing;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockGrindstone extends Block {
    public BlockGrindstone(Material materialIn) {
        super(materialIn);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        this.setRegistryName("grindstone");
        this.setUnlocalizedName("grindstone");
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FACING, EnumHorizontalFacing.NORTH).withProperty(FACE, EnumBlockFace.FLOOR));
    }



    public static final PropertyEnum<EnumHorizontalFacing> FACING =
            PropertyEnum.create("facing", EnumHorizontalFacing.class);
    public static final PropertyEnum<EnumBlockFace> FACE =
            PropertyEnum.create("face", EnumBlockFace.class);

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, FACE);
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        EnumHorizontalFacing facing = EnumHorizontalFacing.values()[meta & 3]; // bits 0–1
        EnumBlockFace face = EnumBlockFace.values()[(meta >> 2) & 3];          // bits 2–3
        return this.getDefaultState()
                .withProperty(FACING, facing)
                .withProperty(FACE, face);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(FACING).ordinal();
        meta |= state.getValue(FACE).ordinal() << 2;
        return meta;
    }


    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumHorizontalFacing horizontal = EnumHorizontalFacing.fromFacing(placer.getHorizontalFacing().getOpposite());
        EnumBlockFace face;

        if (facing == EnumFacing.UP) {
            face = EnumBlockFace.FLOOR;
        } else if (facing == EnumFacing.DOWN) {
            face = EnumBlockFace.CEILING;
        } else {
            face = EnumBlockFace.WALL;
        }

        return this.getDefaultState()
                .withProperty(FACING, horizontal)
                .withProperty(FACE, face);
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
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING).toEnumFacing()));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

}