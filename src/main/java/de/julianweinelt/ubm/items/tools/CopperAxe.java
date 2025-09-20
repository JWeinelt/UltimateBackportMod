package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class CopperAxe extends ItemAxe {
    public CopperAxe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("copper_axe");
        this.setRegistryName("copper_axe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
    }
}