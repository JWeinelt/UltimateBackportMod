package de.julianweinelt.ubm.items.tools;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class NetheritePickAxe extends ItemAxe {
    public NetheritePickAxe(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_pickaxe");
        this.setRegistryName("netherite_pickaxe");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }
}
