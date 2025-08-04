package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockTintedGlass extends Block {
    public BlockTintedGlass() {
        super(Material.GLASS);
        setRegistryName("tinted_glass");
        setUnlocalizedName("tinted_glass");
        setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        setLightOpacity(255);
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return true;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT; // Echte Glastransparenz
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, IBlockAccess world, BlockPos pos, @Nonnull EnumFacing side) {
        IBlockState neighbor = world.getBlockState(pos.offset(side));
        return neighbor.getBlock() != this;
    }
}
