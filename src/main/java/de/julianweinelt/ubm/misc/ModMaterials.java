package de.julianweinelt.ubm.misc;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {
    public static final Item.ToolMaterial NETHERITE = EnumHelper.addToolMaterial("NETHERITE",
            3, 2031, 9.0F, 4.0F, 15);
    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("COPPER",
            3, 121, 9.0F, 4.0F, 8);

    public static final ItemArmor.ArmorMaterial NETHERITE_ARMOR = EnumHelper.addArmorMaterial(
            "NETHERITE",
            "ubm:netherite",
            33,
            new int[]{3, 6, 8, 3},
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0F
    );

    public static final ItemArmor.ArmorMaterial COPPER_ARMOR = EnumHelper.addArmorMaterial(
            "COPPER",
            "ubm:copper",
            121,
            new int[]{2, 4, 3, 1},
            8,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F
    );
}