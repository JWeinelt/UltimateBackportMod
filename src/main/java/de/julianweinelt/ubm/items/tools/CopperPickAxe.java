package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class CopperPickAxe extends ItemAxe {
    public CopperPickAxe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("copper_pickaxe");
        this.setRegistryName("copper_pickaxe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
    }
}
