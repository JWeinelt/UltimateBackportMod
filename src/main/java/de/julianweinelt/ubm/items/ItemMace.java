package de.julianweinelt.ubm.items;

import com.google.common.collect.Multimap;
import de.julianweinelt.ubm.enchantments.EnchantmentBreach;
import de.julianweinelt.ubm.enchantments.EnchantmentDensity;
import de.julianweinelt.ubm.enchantments.EnchantmentWindburst;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.enchantment.*;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class ItemMace extends Item {
    public ItemMace() {
        this.setRegistryName("mace");
        this.setUnlocalizedName("mace");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        this.setMaxStackSize(1);
        this.setMaxDamage(500);
    }

    @Override @Nonnull
    public EnumRarity getRarity(@Nonnull ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack stack) {
        return true;
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(@Nonnull EntityEquipmentSlot slot) {

        Multimap<String, AttributeModifier> map = super.getItemAttributeModifiers(slot);

        if (slot == EntityEquipmentSlot.MAINHAND) {

            map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
                            "Weapon modifier",
                            5.0D,
                            0));

            map.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER,
                            "Weapon modifier",
                            -3.4D,
                            0));
        }

        return map;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 15;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, @Nonnull Enchantment enchantment) {
        if (enchantment.equals(Enchantments.FIRE_ASPECT)) return true;
        if (enchantment.equals(Enchantments.BANE_OF_ARTHROPODS)) return true;
        if (enchantment.equals(Enchantments.SHARPNESS)) return true;
        if (enchantment.equals(Enchantments.VANISHING_CURSE)) return true;
        if (enchantment.equals(Enchantments.SMITE)) return true;
        if (enchantment.equals(Enchantments.MENDING)) return true;
        if (enchantment.equals(Enchantments.UNBREAKING)) return true;
        if (enchantment.equals(Enchantments.LOOTING)) return true;
        if (enchantment instanceof EnchantmentBreach) return true;
        if (enchantment instanceof EnchantmentDensity) return true;
        return false;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return TextFormatting.LIGHT_PURPLE + super.getItemStackDisplayName(stack);
    }


}