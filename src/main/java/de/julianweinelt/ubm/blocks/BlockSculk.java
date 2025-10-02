package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockSculk extends Block {
    public BlockSculk() {
        super(Material.CLOTH);
        this.setRegistryName("sculk");
        this.setUnlocalizedName("sculk");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.setHardness(0.2F);
        this.setResistance(0.2F);
        this.setHarvestLevel("hoe", 0);
    }

    @Override
    public MapColor getMapColor(@Nonnull IBlockState state, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
        return MapColor.getBlockColor(EnumDyeColor.BLACK);
    }

    @Override
    public int getExpDrop(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, int fortune) {
        return 1;
    }

    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(@Nonnull IBlockState state) {
        return new ItemStack(ModBlocks.SCULK);
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return 0;
    }
}