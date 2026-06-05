package de.julianweinelt.ubm.enchantments;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.enchantment.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class EnchantmentDensity extends Enchantment {
    protected EnchantmentDensity() {
        super(Rarity.RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("density");
        setRegistryName("density");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack) {
        return true;
    }

    @Override
    protected boolean canApplyTogether(@Nonnull Enchantment enchantment) {
        // EnchantmentDamage: All = Sharpness (type 0), Undead = Site (type 1), arthropods = Bane of Arthropods (type 2)

        if (enchantment instanceof EnchantmentDamage) return false;
        if (enchantment instanceof EnchantmentBreach) return false;
        // TODO: Add impaling as incompatible enchantment
        return super.canApplyTogether(enchantment);
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem().equals(ModItems.MACE);
    }
}
