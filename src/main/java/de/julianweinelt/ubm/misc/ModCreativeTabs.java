package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeTabs UBM_TAB_SPAWN_EGGS = new CreativeTabs("ubm_tab_spawn_eggs") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SPAWN_EGG_FROG);
        }
    };
    public static final CreativeTabs UBM_TAB_AQUATIC = new CreativeTabs("ubm_tab_aquatic") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.TRIDENT);
        }
    };
    public static final CreativeTabs UBM_TAB_PILLAGE = new CreativeTabs("ubm_tab_pillage") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SWEET_BERRY);
        }
    };
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
    public static final CreativeTabs UBM_TAB_WILD = new CreativeTabs("ubm_tab_wild") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.PEARLESCENT_FROGLIGHT);
        }
    };
    public static final CreativeTabs UBM_TAB_TRAILS_TALES = new CreativeTabs("ubm_tab_trails_tales") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.CANDLE);
        }
    };
    public static final CreativeTabs UBM_TAB_COPPER_AGE = new CreativeTabs("ubm_tab_copper_age") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.COPPER_NUGGET);
        }
    };
}
