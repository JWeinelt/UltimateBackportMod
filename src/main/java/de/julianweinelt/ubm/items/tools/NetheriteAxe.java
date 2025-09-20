package de.julianweinelt.ubm.items.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NetheriteAxe extends ItemAxe {
    public NetheriteAxe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_axe");
        this.setRegistryName("netherite_axe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }


    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, @Nullable ItemStack stack) {
        Multimap<String, AttributeModifier> modifiers = HashMultimap.create();

        if (slot == EntityEquipmentSlot.MAINHAND) {
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 9.0F, 0));
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", -3F, 0));
        }

        return modifiers;
    }
}