package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemSweetBerry extends ItemFood {

    public ItemSweetBerry() {
        super(2, 0.4F, false);
        setUnlocalizedName("sweet_berry");
        setRegistryName("sweet_berry");
        setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
    }
}