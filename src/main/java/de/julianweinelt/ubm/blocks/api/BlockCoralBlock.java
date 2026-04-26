package de.julianweinelt.ubm.blocks.api;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockCoralBlock extends Block {
    private final boolean dead;
    private final Block deadVariant;

    public BlockCoralBlock(String type, boolean dead, Block deadVariant) {
        super(Material.ROCK);
        this.dead = dead;
        this.deadVariant = deadVariant;
        String name = (dead ? "dead_" : "") + type + "_coral_block";
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_AQUATIC);
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nonnull IBlockState blockstate, @Nonnull Random rand, int fortune) {
        if (!dead) return Item.getItemFromBlock(deadVariant);
        else return Item.getItemFromBlock(this);
    }
}
