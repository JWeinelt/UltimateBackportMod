package de.julianweinelt.ubm.misc;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModMaterials {
    public static final Item.ToolMaterial NETHERITE = EnumHelper.addToolMaterial("NETHERITE",
            3, 2031, 9.0F, 4.0F, 15);

    public static final ItemArmor.ArmorMaterial NETHERITE_ARMOR = EnumHelper.addArmorMaterial(
            "NETHERITE",
            "ubm:netherite",
            33,
            new int[]{3, 6, 8, 3},
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.0F
    );
}