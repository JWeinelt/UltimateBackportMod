package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemAxe;

public class NetheriteShovel extends ItemAxe {
    protected NetheriteShovel(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_shovel");
        this.setRegistryName("netherite_shovel");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }
}