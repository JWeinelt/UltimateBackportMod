package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.tiles.TileEntitySculkSensor;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockSculkSensor extends Block {
    public BlockSculkSensor() {
        super(Material.ROCK);
        setRegistryName("sculk_sensor");
        setUnlocalizedName("sculk_sensor");
        setHardness(1.5F);
        setResistance(1.5F);
        setLightLevel(0.1F);
        setHarvestLevel("hoe", 0);
    }

    @Override
    public boolean canProvidePower(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(@Nonnull IBlockState blockState, IBlockAccess world, @Nonnull BlockPos pos,
                            @Nonnull EnumFacing side) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntitySculkSensor) {
            return ((TileEntitySculkSensor) te).isActive() ? 15 : 0;
        }
        return 0;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull World worldIn, @Nonnull IBlockState state) {
        return new TileEntitySculkSensor();
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
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.getBlockColor(EnumDyeColor.CYAN);
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(ModBlocks.SCULK_SENSOR);
    }
}
