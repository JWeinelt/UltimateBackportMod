package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.ItemBoat;

public class ItemBambooRaft extends ItemBoat {
    public ItemBambooRaft() {
        super(EntityBoat.Type.OAK);
        setUnlocalizedName("bamboo_raft");
        setRegistryName("bamboo_raft");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
    }
}