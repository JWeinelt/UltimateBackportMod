package de.julianweinelt.ubm.enchantments;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class EnchantmentBreach extends Enchantment {
    protected EnchantmentBreach() {
        super(Rarity.RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("breach");
        setRegistryName("breach");
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem().equals(ModItems.MACE);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        if (stack.getItem().equals(ModItems.MACE)) return true;
        return false;
    }

    @Override
    protected boolean canApplyTogether(@Nonnull Enchantment enchantment) {
        // EnchantmentDamage: All = Sharpness (type 0), Undead = Site (type 1), arthropods = Bane of Arthropods (type 2)

        if (enchantment instanceof EnchantmentDamage) return false;
        if (enchantment instanceof EnchantmentDensity) return false;
        // TODO: Add impaling as incompatible enchantment
        return super.canApplyTogether(enchantment);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
