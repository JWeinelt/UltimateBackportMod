package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemGlowBerry extends ItemFood {
    public ItemGlowBerry() {
        super(2, 0.1F, false);
        this.setAlwaysEdible();
        this.setRegistryName("glow_berries");
        this.setUnlocalizedName("glow_berries");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
    }
}