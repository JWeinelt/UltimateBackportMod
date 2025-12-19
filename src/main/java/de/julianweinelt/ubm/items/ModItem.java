package de.julianweinelt.ubm.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItem extends Item {
    public ModItem(String name, CreativeTabs tab) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(tab);
    }
}