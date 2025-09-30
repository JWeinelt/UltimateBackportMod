package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.item.ItemSign;

public class ItemModSign extends ItemSign {
    public ItemModSign(String name) {
        this.setRegistryName(name + "_sign");
        this.setUnlocalizedName(name + "_sign");
    }
}
