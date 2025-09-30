package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
    public static void init() {
        GameRegistry.addSmelting(ModItems.RAW_GOLD, new ItemStack(Items.GOLD_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModItems.RAW_IRON, new ItemStack(Items.IRON_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModItems.RAW_COPPER, new ItemStack(ModItems.COPPER_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.COPPER_ORE, new ItemStack(ModItems.COPPER_INGOT, 1), 0.7F);

        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_COPPER, new ItemStack(ModItems.COPPER_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_LAPISLAZULI, new ItemStack(Items.DYE, 1, 4), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_GOLD, new ItemStack(Items.GOLD_INGOT, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_REDSTONE, new ItemStack(Items.REDSTONE, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_EMERALD, new ItemStack(Items.EMERALD, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_DIAMOND, new ItemStack(Items.DIAMOND, 1), 0.7F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_ORE_IRON, new ItemStack(Items.IRON_INGOT, 1), 0.7F);


        GameRegistry.addSmelting(ModBlocks.ANCIENT_DEBRIS, new ItemStack(ModItems.NETHERITE_SCRAP, 1), 0.7F);

    }
}