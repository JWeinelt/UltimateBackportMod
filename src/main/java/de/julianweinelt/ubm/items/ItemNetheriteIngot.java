package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemNetheriteIngot extends Item {
    public ItemNetheriteIngot() {
        setUnlocalizedName("netherite_ingot");
        setRegistryName("netherite_ingot");
        setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }

    @Override
    public boolean isBeaconPayment(@Nonnull ItemStack stack) {
        return true;
    }
}