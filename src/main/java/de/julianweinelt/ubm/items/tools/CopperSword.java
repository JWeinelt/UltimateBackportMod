package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemSword;

public class CopperSword extends ItemSword {
    public CopperSword(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("copper_sword");
        this.setRegistryName("copper_sword");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
    }
}