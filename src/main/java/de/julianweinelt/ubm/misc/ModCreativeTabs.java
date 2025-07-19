package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeTabs UBM_TAB_BEES = new CreativeTabs("ubm_tab_bees") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.BEE_NEST);
        }
    };
    public static final CreativeTabs UBM_TAB_NETHER = new CreativeTabs("ubm_tab_nether") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.NETHERITE_INGOT);
        }
    };
    public static final CreativeTabs UBM_TAB_CAVES = new CreativeTabs("ubm_tab_caves") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.POWDER_SNOW_BUCKET);
        }
    };
    public static final CreativeTabs UBM_TAB_TRAILS_TALES = new CreativeTabs("ubm_tab_trails_tales") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.ANGLER_POTTERY_SHERD);
        }
    };
    public static final CreativeTabs UBM_TAB_WILD = new CreativeTabs("ubm_tab_wild") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.PEARLESCENT_FROGLIGHT);
        }
    };
}
