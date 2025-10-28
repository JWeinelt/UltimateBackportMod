package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.tiles.TileEntitySculkSensor;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockSculkSensor extends Block {
    public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");

    public static final AxisAlignedBB SENSOR_AABB = new AxisAlignedBB(
            0.0D, 0.0D, 0.0D,
            1.0D, 0.5D, 1.0D
    );

    public BlockSculkSensor() {
        super(Material.ROCK);

        setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        setRegistryName("sculk_sensor");
        setUnlocalizedName("sculk_sensor");
        setHarvestLevel("hoe", 0);
        setHardness(1.5F);
        setResistance(1.5F);
        setDefaultState(this.blockState.getBaseState().withProperty(TRIGGERED, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TRIGGERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TRIGGERED, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TRIGGERED) ? 1 : 0;
    }


    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }



    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(@Nonnull IBlockState state) {
        return new ItemStack(ModBlocks.SCULK_SENSOR);
    }

    @Override
    public int getExpDrop(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, int fortune) {
        return 5;
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return 0;
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source,
                                        @Nonnull BlockPos pos) {
        return SENSOR_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn,
                                                 @Nonnull BlockPos pos) {
        return SENSOR_AABB;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntitySculkSensor();
    }

    @Override
    public boolean canProvidePower(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(@Nonnull IBlockState state, IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        return state.getValue(TRIGGERED) ? 15 : 0;
    }

    @Override
    public boolean canConnectRedstone(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
                                      @Nullable EnumFacing side) {
        return true;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        worldIn.setBlockState(pos, state.withProperty(TRIGGERED, false), 3);
        worldIn.notifyNeighborsOfStateChange(pos, this, true);
    }
}
