package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockHoneyBlock extends Block {
    protected static final AxisAlignedBB HONEY_AABB =
            new AxisAlignedBB(0.07D, 0.07D, 0.07D,
                    0.9375D, 0.9375D, 0.9375D);

    public BlockHoneyBlock() {
        super(Material.PLANTS);
        setRegistryName("honey_block");
        setUnlocalizedName("honey_block");
        setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
    }


    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source,
                                        @Nonnull BlockPos pos) {
        return HONEY_AABB;
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
}
