package de.julianweinelt.ubm.items.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NetheriteSword extends ItemSword {
    public NetheriteSword(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_sword");
        this.setRegistryName("netherite_sword");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (entityItem.isBurning()) {
            entityItem.extinguish();
        }
        entityItem.setFire(0);
        return false;
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, @Nullable ItemStack stack) {
        Multimap<String, AttributeModifier> modifiers = HashMultimap.create();

        if (slot == EntityEquipmentSlot.MAINHAND) {
            modifiers.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                    new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 7.0F, 0));
            modifiers.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                    new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4F, 0));
        }

        return modifiers;
    }
}