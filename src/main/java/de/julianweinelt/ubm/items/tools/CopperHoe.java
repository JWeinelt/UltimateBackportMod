package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class CopperHoe extends ItemAxe {
    public CopperHoe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("copper_hoe");
        this.setRegistryName("copper_hoe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
    }
}
