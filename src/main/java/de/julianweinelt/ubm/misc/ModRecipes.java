package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {
    public static void init() {
        OreDictionary.registerOre("logWood", ModBlocks.PALE_OAK_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.PALE_OAK_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.CHERRY_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.CHERRY_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.MANGROVE_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.MANGROVE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.CRIMSON_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.WARPED_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.WARPED_HYPHAE);
        OreDictionary.registerOre("logWood", ModBlocks.CRIMSON_HYPHAE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_ACACIA_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_ACACIA_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_BIRCH_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_BIRCH_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CHERRY_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CHERRY_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CRIMSON_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_WARPED_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_CRIMSON_HYPHAE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_WARPED_HYPHAE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_PALE_OAK_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_PALE_OAK_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_JUNGLE_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_JUNGLE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_SPRUCE_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_SPRUCE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_MANGROVE_LOG);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_MANGROVE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_OAK_STEM);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_OAK_WOOD);

        OreDictionary.registerOre("cobblestone", ModBlocks.DEEPSLATE_COBBLED);
        OreDictionary.registerOre("cobblestone", ModBlocks.BLACKSTONE);

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

        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_COBBLED, new ItemStack(ModBlocks.DEEPSLATE, 1), 0.1F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_TILES, new ItemStack(ModBlocks.DEEPSLATE_TILES_CRACKED, 1), 0.1F);
        GameRegistry.addSmelting(ModBlocks.DEEPSLATE_BRICKS, new ItemStack(ModBlocks.DEEPSLATE_BRICKS_CRACKED, 1), 0.1F);

    }
}