package de.julianweinelt.ubm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentQuickCharge extends Enchantment {
    protected EnchantmentQuickCharge() {
        super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        this.setName("quick_charge");
        this.setRegistryName("quick_charge");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 10 + (level - 1) * 20;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return this.getMinEnchantability(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}