package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockMod extends Block {
    private boolean transparent = false;
    private ItemStack silkTouchDrop;
    private final Item drop;
    private int quantity = 0;
    private SoundType type = SoundType.STONE;

    public BlockMod(Material materialIn, String name, Item drop) {
        super(materialIn);
        this.drop = drop;
        setRegistryName(name);
        setUnlocalizedName(name);
        silkTouchDrop = new ItemStack(this);
    }

    public BlockMod hardness(float hardness) {
        setHardness(hardness);
        return this;
    }
    public BlockMod resistance(float resistance) {
        setResistance(resistance);
        return this;
    }

    public BlockMod creativeTab(CreativeTabs tab) {
        setCreativeTab(tab);
        return this;
    }

    public BlockMod transparent() {
        transparent = true;
        return this;
    }

    public BlockMod silkTouchDrop(ItemStack silkTouchDrop) {
        this.silkTouchDrop = silkTouchDrop;
        return this;
    }
    public BlockMod dropQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
    public BlockMod soundType(SoundType sound) {
        this.type = sound;
        return this;
    }


    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return !transparent;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return !transparent;
    }


    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(@Nonnull IBlockState state) {
        return silkTouchDrop;
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState state, @Nonnull Random rand, int fortune) {
        return drop;
    }

    @Override
    public int quantityDropped(@Nonnull Random random) {
        return quantity;
    }

    @Override
    @Nonnull
    public SoundType getSoundType(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos,
                                  @Nullable Entity entity) {
        return type;
    }
}
