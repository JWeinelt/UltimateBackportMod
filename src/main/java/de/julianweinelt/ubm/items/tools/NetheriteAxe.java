package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class NetheriteAxe extends ItemAxe {
    public NetheriteAxe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_axe");
        this.setRegistryName("netherite_axe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }
}