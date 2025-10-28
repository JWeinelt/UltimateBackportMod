package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BlockModSlab extends BlockSlab {

    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);

    private final boolean isDouble;

    public BlockModSlab(String name, Material material, boolean isDouble) {
        super(material);
        this.isDouble = isDouble;

        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
        if (!isDouble) {
            state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        }
        this.setDefaultState(state);

        this.useNeighborBrightness = true;
    }

    @Override
    public boolean isDouble() {
        return this.isDouble;
    }

    @Override
    @Nonnull
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    @Nonnull
    public Comparable<?> getTypeForItem(@Nonnull ItemStack stack) {
        return Variant.DEFAULT;
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return isDouble
                ? new BlockStateContainer(this, VARIANT)
                : new BlockStateContainer(this, VARIANT, HALF);
    }

    @Override
    public int getMetaFromState(@Nonnull IBlockState state) {
        if (isDouble) {
            return 0;
        } else {
            return state.getValue(HALF) == EnumBlockHalf.TOP ? 8 : 0;
        }
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        if (isDouble) {
            return this.getDefaultState();
        } else {
            return this.getDefaultState().withProperty(HALF, (meta & 8) != 0 ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        }
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return getItemDroppedSlab().getItemDropped(state, rand, fortune);
    }

    protected abstract BlockModSlab getItemDroppedSlab();

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        @Nonnull
        public String getName() {
            return "default";
        }
    }
}
