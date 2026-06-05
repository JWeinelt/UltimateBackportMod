package de.julianweinelt.ubm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentWindburst extends Enchantment {
    protected EnchantmentWindburst() {
        super(Rarity.RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
        setName("wind_burst");
        setRegistryName("wind_burst");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
