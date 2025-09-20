package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class CopperShovel extends ItemAxe {
    public CopperShovel(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("copper_shovel");
        this.setRegistryName("copper_shovel");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
    }
}