package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void init() {
        GameRegistry.addSmelting(ModItems.RAW_GOLD, new ItemStack(Items.GOLD_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModItems.RAW_IRON, new ItemStack(Items.IRON_INGOT, 1), 0.7F);
    }
}