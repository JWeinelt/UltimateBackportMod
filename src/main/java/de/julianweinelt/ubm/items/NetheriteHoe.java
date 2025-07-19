package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class NetheriteHoe extends ItemAxe {
    protected NetheriteHoe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_hoe");
        this.setRegistryName("netherite_hoe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }
}
