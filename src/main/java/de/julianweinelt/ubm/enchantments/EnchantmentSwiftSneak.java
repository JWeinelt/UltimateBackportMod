package de.julianweinelt.ubm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentSwiftSneak extends Enchantment {
    protected EnchantmentSwiftSneak() {
        super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[] {EntityEquipmentSlot.LEGS});
        this.setName("swift_sneak");
        this.setRegistryName("swift_sneak");
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