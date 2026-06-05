package de.julianweinelt.ubm.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class ItemBlockHeavyCore extends ItemBlock {
    public ItemBlockHeavyCore(@NotNull Block heavyCore) {
        super(heavyCore);
        setRegistryName(heavyCore.getRegistryName());
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(@Nonnull ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return TextFormatting.LIGHT_PURPLE + super.getItemStackDisplayName(stack);
    }
}
