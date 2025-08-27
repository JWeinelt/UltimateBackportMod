package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.plant.BlockSweetBerry;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBeeNest;
import de.julianweinelt.ubm.items.BlockCopperTorch;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.worldgen.ModBiomes;
import de.julianweinelt.ubm.worldgen.WorldGenBeeNest;
import de.julianweinelt.ubm.worldgen.WorldTypeSelectableBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("ConstantConditions")
@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModBlocks {
    public static final Map<ResourceLocation, ResourceLocation> WAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> UNWAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> PREVIOUS_OXIDATION = new HashMap<>();


    public static Block NETHERITE_BLOCK;
    public static Block ANCIENT_DEBRIS;

    public static Block CRIMSON_STEM;
    public static Block CRIMSON_HYPHAE;
    public static Block STRIPPED_CRIMSON_STEM;
    public static Block STRIPPED_CRIMSON_HYPHAE;
    public static Block CRIMSON_FUNGUS;
    public static Block CRIMSON_ROOTS;
    public static Block CRIMSON_SLAB;
    public static Block CRIMSON_STAIRS;
    public static Block CRIMSON_PRESSURE_PLATE;
    public static Block CRIMSON_BUTTON;
    public static Block CRIMSON_SIGN;
    public static Block CRIMSON_FENCE;
    public static Block CRIMSON_FENCE_GATE;
    public static Block CRIMSON_DOOR;
    public static Block CRIMSON_TRAPDOOR;
    public static Block CRIMSON_NYLIUM;
    public static Block CRIMSON_PLANKS;
    public static Block WARPED_STEM;
    public static Block WARPED_HYPHAE;
    public static Block STRIPPED_WARPED_STEM;
    public static Block STRIPPED_WARPED_HYPHAE;
    public static Block WARPED_FUNGUS;
    public static Block WARPED_ROOTS;
    public static Block WARPED_SLAB;
    public static Block WARPED_STAIRS;
    public static Block WARPED_PRESSURE_PLATE;
    public static Block WARPED_BUTTON;
    public static Block WARPED_SIGN;
    public static Block WARPED_FENCE;
    public static Block WARPED_FENCE_GATE;
    public static Block WARPED_DOOR;
    public static Block WARPED_TRAPDOOR;
    public static Block WARPED_NYLIUM;
    public static Block WARPED_PLANKS;
    public static Block WARPED_WART_BLOCK;

    public static Block WEEPING_VINES;
    public static Block TWISTING_VINES;

    public static Block NETHER_SPROUTS;

    public static Block CRYING_OBSIDIAN;

    public static Block COPPER_TORCH;

    public static Block SOUL_CAMPFIRE;
    public static Block SOUL_TORCH;
    public static Block SOUL_FIRE;

    public static Block NETHER_GOLD_ORE;
    public static Block SOUL_SOIL;
    public static Block BLACKSTONE;
    public static Block POLISHED_BLACKSTONE;
    public static Block CHISELED_POLISHED_BLACKSTONE;
    public static Block POLISHED_BLACKSTONE_BRICKS;
    public static Block CRACKED_POLISHED_BLACKSTONE_BRICKS;
    public static Block GILDED_BLACKSTONE;
    public static Block BLACKSTONE_SLAB;
    public static Block BLACKSTONE_STAIRS;
    public static Block POLISHED_BLACKSTONE_SLAB;
    public static Block POLISHED_BLACKSTONE_STAIRS;
    public static Block POLISHED_BLACKSTONE_BRICK_SLAB;
    public static Block POLISHED_BLACKSTONE_BRICK_STAIRS;
    public static Block POLISHED_BLACKSTONE_BUTTON;
    public static Block POLISHED_BLACKSTONE_PRESSURE_PLATE;
    public static Block POLISHED_BLACKSTONE_BRICK_WALL;
    public static Block POLISHED_BLACKSTONE_WALL;
    public static Block BLACKSTONE_WALL;

    public static Block BASALT;
    public static Block POLISHED_BASALT;

    public static Block OCHRE_FROGLIGHT;
    public static Block VERDANT_FROGLIGHT;
    public static Block PEARLESCENT_FROGLIGHT;

    public static Block POWDER_SNOW;

    public static Block BEE_NEST;
    public static Block BEE_HIVE;

    public static Block SWEET_BERRY_BUSH;

    public static Block CANDLE;
    public static Block BLACK_CANDLE;
    public static Block RED_CANDLE;
    public static Block GREEN_CANDLE;
    public static Block BROWN_CANDLE;
    public static Block BLUE_CANDLE;
    public static Block PURPLE_CANDLE;
    public static Block CYAN_CANDLE;
    public static Block LIGHT_GRAY_CANDLE;
    public static Block GRAY_CANDLE;
    public static Block PINK_CANDLE;
    public static Block LIME_CANDLE;
    public static Block YELLOW_CANDLE;
    public static Block LIGHT_BLUE_CANDLE;
    public static Block MAGENTA_CANDLE;
    public static Block ORANGE_CANDLE;
    public static Block WHITE_CANDLE;

    public static Block TINTED_GLASS;
    public static Block AMETHYST_BLOCK;
    public static Block CALCITE;
    public static Block TUFF;
    public static Block COPPER_ORE;
    public static Block DEEPSLATE;
    public static Block DEEPSLATE_ORE_COPPER;
    public static Block DEEPSLATE_ORE_GOLD;
    public static Block DEEPSLATE_ORE_IRON;
    public static Block DEEPSLATE_ORE_REDSTONE;
    public static Block DEEPSLATE_ORE_LAPISLAZULI;
    public static Block DEEPSLATE_ORE_EMERALD;
    public static Block DEEPSLATE_ORE_DIAMOND;
    public static Block DEEPSLATE_COBBLED;
    public static Block DEEPSLATE_BRICKS;
    public static Block DEEPSLATE_TILES;
    public static Block DEEPSLATE_POLISHED;
    public static Block DEEPSLATE_BRICKS_CRACKED;
    public static Block DEEPSLATE_TILES_CRACKED;

    public static Block ROOTED_DIRT;
    public static Block SMOOTH_BASALT;

    public static Block MOSS_BLOCK;
    public static Block MOSS_CARPET;

    public static Block COPPER_BLOCK, CHISELED_COPPER, COPPER_GRATE, CUT_COPPER, COPPER_BULB,
        EXPOSED_COPPER_BLOCK, EXPOSED_CHISELED_COPPER, EXPOSED_COPPER_GRATE, EXPOSED_CUT_COPPER, EXPOSED_COPPER_BULB,
        WEATHERED_COPPER_BLOCK, WEATHERED_CHISELED_COPPER, WEATHERED_COPPER_GRATE, WEATHERED_CUT_COPPER, WEATHERED_COPPER_BULB,
        OXIDIZED_COPPER_BLOCK, OXIDIZED_CHISELED_COPPER, OXIDIZED_COPPER_GRATE, OXIDIZED_CUT_COPPER, OXIDIZED_COPPER_BULB,
        WAXED_COPPER_BLOCK, WAXED_CHISELED_COPPER, WAXED_COPPER_GRATE, WAXED_CUT_COPPER, WAXED_COPPER_BULB,
        WAXED_EXPOSED_COPPER_BLOCK, WAXED_EXPOSED_CHISELED_COPPER, WAXED_EXPOSED_COPPER_GRATE, WAXED_EXPOSED_CUT_COPPER, WAXED_EXPOSED_COPPER_BULB,
        WAXED_WEATHERED_COPPER_BLOCK, WAXED_WEATHERED_CHISELED_COPPER, WAXED_WEATHERED_COPPER_GRATE, WAXED_WEATHERED_CUT_COPPER, WAXED_WEATHERED_COPPER_BULB,
        WAXED_OXIDIZED_COPPER_BLOCK, WAXED_OXIDIZED_CHISELED_COPPER, WAXED_OXIDIZED_COPPER_GRATE, WAXED_OXIDIZED_CUT_COPPER, WAXED_OXIDIZED_COPPER_BULB
    ;

    // Helper Methods
    private static Block createBasicBlock(String name, Material material, CreativeTabs tab) {
        return new Block(material)
                .setUnlocalizedName(name)
                .setRegistryName(name)
                .setCreativeTab(tab);
    }

    private static void registerBlock(RegistryEvent.Register<Block> event, Block block) {
        event.getRegistry().register(block);
    }

    private static void registerBlockWithItem(RegistryEvent.Register<Item> event, Block block) {
        Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        event.getRegistry().register(item);
        registerItemModel(item);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        COPPER_TORCH = new BlockCopperTorch("copper_torch");
        event.getRegistry().register(COPPER_TORCH);

        ANCIENT_DEBRIS = createBasicBlock("ancient_debris", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, ANCIENT_DEBRIS);

        // Crimson Wood Blocks
        CRIMSON_STEM = createBasicBlock("crimson_stem", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRIMSON_STEM);

        CRIMSON_HYPHAE = createBasicBlock("crimson_hyphae", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRIMSON_HYPHAE);

        // Warped Wood Blocks
        WARPED_STEM = createBasicBlock("warped_stem", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_STEM);

        WARPED_HYPHAE = createBasicBlock("warped_hyphae", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_HYPHAE);

        // Stripped Wood Blocks
        STRIPPED_CRIMSON_STEM = createBasicBlock("stripped_crimson_stem", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, STRIPPED_CRIMSON_STEM);

        STRIPPED_CRIMSON_HYPHAE = createBasicBlock("stripped_crimson_hyphae", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, STRIPPED_CRIMSON_HYPHAE);

        STRIPPED_WARPED_STEM = createBasicBlock("stripped_warped_stem", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, STRIPPED_WARPED_STEM);

        STRIPPED_WARPED_HYPHAE = createBasicBlock("stripped_warped_hyphae", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, STRIPPED_WARPED_HYPHAE);

        // Blackstone Blocks
        BLACKSTONE = createBasicBlock("blackstone", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, BLACKSTONE);

        POLISHED_BLACKSTONE = createBasicBlock("polished_blackstone", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, POLISHED_BLACKSTONE);

        CHISELED_POLISHED_BLACKSTONE = createBasicBlock("chiseled_polished_blackstone", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CHISELED_POLISHED_BLACKSTONE);

        POLISHED_BLACKSTONE_BRICKS = createBasicBlock("polished_blackstone_bricks", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, POLISHED_BLACKSTONE_BRICKS);

        CRACKED_POLISHED_BLACKSTONE_BRICKS = createBasicBlock("cracked_polished_blackstone_bricks", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRACKED_POLISHED_BLACKSTONE_BRICKS);

        GILDED_BLACKSTONE = createBasicBlock("gilded_blackstone", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, GILDED_BLACKSTONE);

        // Other Nether Blocks
        NETHER_GOLD_ORE = createBasicBlock("nether_gold_ore", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, NETHER_GOLD_ORE);

        SOUL_SOIL = createBasicBlock("soul_soil", Material.GROUND, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, SOUL_SOIL);

        WARPED_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("warped_nylium")
                .setRegistryName("warped_nylium")
                .setHardness(2.0F)
                .setResistance(5.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_NYLIUM);

        CRYING_OBSIDIAN = createBasicBlock("crying_obsidian", Material.ROCK, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRYING_OBSIDIAN);

        CRIMSON_NYLIUM = createBasicBlock("crimson_nylium", Material.GROUND, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRIMSON_NYLIUM);

        CRIMSON_PLANKS = createBasicBlock("crimson_planks", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRIMSON_PLANKS);

        WARPED_PLANKS = createBasicBlock("warped_planks", Material.WOOD, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_PLANKS);

        // Special Blocks
        WARPED_STAIRS = new BlockModStairs(WARPED_PLANKS.getDefaultState(), "warped_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_STAIRS);

        CRIMSON_STAIRS = new BlockModStairs(CRIMSON_PLANKS.getDefaultState(), "crimson_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, CRIMSON_STAIRS);

        WARPED_FENCE = new BlockModFence(Material.WOOD, "warped_fence")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_FENCE);

        WARPED_WART_BLOCK = createBasicBlock("warped_wart_block", Material.PLANTS, ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, WARPED_WART_BLOCK);

        BLACKSTONE_WALL = new BlockWall(BLACKSTONE)
                .setUnlocalizedName("blackstone_wall")
                .setRegistryName("blackstone_wall")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerBlock(event, BLACKSTONE_WALL);

        // Froglight Blocks
        PEARLESCENT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("pearlescent_froglight")
                .setRegistryName("pearlescent_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        registerBlock(event, PEARLESCENT_FROGLIGHT);

        OCHRE_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("ochre_froglight")
                .setRegistryName("ochre_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        registerBlock(event, OCHRE_FROGLIGHT);

        VERDANT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("verdant_froglight")
                .setRegistryName("verdant_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        registerBlock(event, VERDANT_FROGLIGHT);

        // Special Blocks
        POWDER_SNOW = new BlockPowderSnow();
        registerBlock(event, POWDER_SNOW);

        // Bee Blocks
        BEE_NEST = createBasicBlock("bee_nest", Material.WOOD, ModCreativeTabs.UBM_TAB_BEES);
        registerBlock(event, BEE_NEST);

        BEE_HIVE = createBasicBlock("bee_hive", Material.WOOD, ModCreativeTabs.UBM_TAB_BEES);
        registerBlock(event, BEE_HIVE);

        // Plant Blocks
        SWEET_BERRY_BUSH = new BlockSweetBerry().setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        registerBlock(event, SWEET_BERRY_BUSH);

        // Candles
        CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("candle").setUnlocalizedName("candle");
        registerBlock(event, CANDLE);

        BLACK_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("black_candle").setUnlocalizedName("black_candle");
        RED_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("red_candle").setUnlocalizedName("red_candle");
        GREEN_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("green_candle").setUnlocalizedName("green_candle");
        BROWN_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("brown_candle").setUnlocalizedName("brown_candle");
        BLUE_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("blue_candle").setUnlocalizedName("blue_candle");
        PURPLE_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("purple_candle").setUnlocalizedName("purple_candle");
        CYAN_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("cyan_candle").setUnlocalizedName("cyan_candle");
        LIGHT_GRAY_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("light_gray_candle").setUnlocalizedName("light_gray_candle");
        GRAY_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("gray_candle").setUnlocalizedName("gray_candle");
        PINK_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("pink_candle").setUnlocalizedName("pink_candle");
        LIME_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("lime_candle").setUnlocalizedName("lime_candle");
        YELLOW_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("yellow_candle").setUnlocalizedName("yellow_candle");
        LIGHT_BLUE_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("light_blue_candle").setUnlocalizedName("light_blue_candle");
        MAGENTA_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("magenta_candle").setUnlocalizedName("magenta_candle");
        ORANGE_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("orange_candle").setUnlocalizedName("orange_candle");
        WHITE_CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("white_candle").setUnlocalizedName("white_candle");

        event.getRegistry().registerAll(
                BLACK_CANDLE, RED_CANDLE, GREEN_CANDLE, BROWN_CANDLE, BLUE_CANDLE,
                PURPLE_CANDLE, CYAN_CANDLE, LIGHT_GRAY_CANDLE, GRAY_CANDLE, PINK_CANDLE,
                LIME_CANDLE, YELLOW_CANDLE, LIGHT_BLUE_CANDLE, MAGENTA_CANDLE,
                ORANGE_CANDLE, WHITE_CANDLE
        );

        // Copper Blocks - Normal
        COPPER_BLOCK = createBasicBlock("copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, COPPER_BLOCK);

        CHISELED_COPPER = createBasicBlock("chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, CHISELED_COPPER);

        COPPER_GRATE = new BlockCopperGrate("");
        registerBlock(event, COPPER_GRATE);

        CUT_COPPER = createBasicBlock("cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, CUT_COPPER);

        COPPER_BULB = createBasicBlock("copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, COPPER_BULB);

        // Copper Blocks - Exposed
        EXPOSED_COPPER_BLOCK = createBasicBlock("exposed_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, EXPOSED_COPPER_BLOCK);

        EXPOSED_CHISELED_COPPER = createBasicBlock("exposed_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, EXPOSED_CHISELED_COPPER);

        EXPOSED_COPPER_GRATE = new BlockCopperGrate("exposed");
        registerBlock(event, EXPOSED_COPPER_GRATE);

        EXPOSED_CUT_COPPER = createBasicBlock("exposed_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, EXPOSED_CUT_COPPER);

        EXPOSED_COPPER_BULB = createBasicBlock("exposed_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, EXPOSED_COPPER_BULB);

        // Copper Blocks - Weathered
        WEATHERED_COPPER_BLOCK = createBasicBlock("weathered_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WEATHERED_COPPER_BLOCK);

        WEATHERED_CHISELED_COPPER = createBasicBlock("weathered_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WEATHERED_CHISELED_COPPER);

        WEATHERED_COPPER_GRATE = new BlockCopperGrate("weathered");
        registerBlock(event, WEATHERED_COPPER_GRATE);

        WEATHERED_CUT_COPPER = createBasicBlock("weathered_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WEATHERED_CUT_COPPER);

        WEATHERED_COPPER_BULB = createBasicBlock("weathered_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WEATHERED_COPPER_BULB);

        // Copper Blocks - Oxidized
        OXIDIZED_COPPER_BLOCK = createBasicBlock("oxidized_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, OXIDIZED_COPPER_BLOCK);

        OXIDIZED_CHISELED_COPPER = createBasicBlock("oxidized_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, OXIDIZED_CHISELED_COPPER);

        OXIDIZED_COPPER_GRATE = new BlockCopperGrate("oxidized");
        registerBlock(event, OXIDIZED_COPPER_GRATE);

        OXIDIZED_CUT_COPPER = createBasicBlock("oxidized_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, OXIDIZED_CUT_COPPER);

        OXIDIZED_COPPER_BULB = createBasicBlock("oxidized_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, OXIDIZED_COPPER_BULB);

        // Copper Blocks - Waxed
        WAXED_COPPER_BLOCK = createBasicBlock("waxed_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_COPPER_BLOCK);

        WAXED_CHISELED_COPPER = createBasicBlock("waxed_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_CHISELED_COPPER);

        WAXED_COPPER_GRATE = createBasicBlock("waxed_copper_grate", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_COPPER_GRATE);

        WAXED_CUT_COPPER = createBasicBlock("waxed_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_CUT_COPPER);

        WAXED_COPPER_BULB = createBasicBlock("waxed_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_COPPER_BULB);

        // Copper Blocks - Waxed Exposed
        WAXED_EXPOSED_COPPER_BLOCK = createBasicBlock("waxed_exposed_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_EXPOSED_COPPER_BLOCK);

        WAXED_EXPOSED_CHISELED_COPPER = createBasicBlock("waxed_exposed_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_EXPOSED_CHISELED_COPPER);

        WAXED_EXPOSED_COPPER_GRATE = createBasicBlock("waxed_exposed_copper_grate", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_EXPOSED_COPPER_GRATE);

        WAXED_EXPOSED_CUT_COPPER = createBasicBlock("waxed_exposed_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_EXPOSED_CUT_COPPER);

        WAXED_EXPOSED_COPPER_BULB = createBasicBlock("waxed_exposed_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_EXPOSED_COPPER_BULB);

        // Copper Blocks - Waxed Weathered
        WAXED_WEATHERED_COPPER_BLOCK = createBasicBlock("waxed_weathered_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_WEATHERED_COPPER_BLOCK);

        WAXED_WEATHERED_CHISELED_COPPER = createBasicBlock("waxed_weathered_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_WEATHERED_CHISELED_COPPER);

        WAXED_WEATHERED_COPPER_GRATE = createBasicBlock("waxed_weathered_copper_grate", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_WEATHERED_COPPER_GRATE);

        WAXED_WEATHERED_CUT_COPPER = createBasicBlock("waxed_weathered_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_WEATHERED_CUT_COPPER);

        WAXED_WEATHERED_COPPER_BULB = createBasicBlock("waxed_weathered_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_WEATHERED_COPPER_BULB);

        // Copper Blocks - Waxed Oxidized
        WAXED_OXIDIZED_COPPER_BLOCK = createBasicBlock("waxed_oxidized_copper_block", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_OXIDIZED_COPPER_BLOCK);

        WAXED_OXIDIZED_CHISELED_COPPER = createBasicBlock("waxed_oxidized_chiseled_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_OXIDIZED_CHISELED_COPPER);

        WAXED_OXIDIZED_COPPER_GRATE = createBasicBlock("waxed_oxidized_copper_grate", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_OXIDIZED_COPPER_GRATE);

        WAXED_OXIDIZED_CUT_COPPER = createBasicBlock("waxed_oxidized_cut_copper", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_OXIDIZED_CUT_COPPER);

        WAXED_OXIDIZED_COPPER_BULB = createBasicBlock("waxed_oxidized_copper_bulb", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, WAXED_OXIDIZED_COPPER_BULB);

        // Other Cave Blocks
        TINTED_GLASS = new BlockTintedGlass();
        registerBlock(event, TINTED_GLASS);

        AMETHYST_BLOCK = createBasicBlock("amethyst", Material.ROCK, ModCreativeTabs.UBM_TAB_CAVES);
        registerBlock(event, AMETHYST_BLOCK);

        CALCITE = new Block(Material.ROCK)
                .setUnlocalizedName("calcite")
                .setRegistryName("calcite")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(CALCITE);

        TUFF = new Block(Material.ROCK)
                .setUnlocalizedName("tuff")
                .setRegistryName("tuff")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(TUFF);

        COPPER_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("copper_ore")
                .setRegistryName("copper_ore")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(COPPER_ORE);

        DEEPSLATE = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate")
                .setRegistryName("deepslate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE);

        DEEPSLATE_ORE_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_copper")
                .setRegistryName("deepslate_ore_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_COPPER);

        DEEPSLATE_ORE_GOLD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_gold")
                .setRegistryName("deepslate_ore_gold")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_GOLD);

        DEEPSLATE_ORE_IRON = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_iron")
                .setRegistryName("deepslate_ore_iron")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_IRON);

        DEEPSLATE_ORE_REDSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_redstone")
                .setRegistryName("deepslate_ore_redstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_REDSTONE);

        DEEPSLATE_ORE_LAPISLAZULI = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_lapislazuli")
                .setRegistryName("deepslate_ore_lapislazuli")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_LAPISLAZULI);

        DEEPSLATE_ORE_EMERALD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_emerald")
                .setRegistryName("deepslate_ore_emerald")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_EMERALD);

        DEEPSLATE_ORE_DIAMOND = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_diamond")
                .setRegistryName("deepslate_ore_diamond")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_ORE_DIAMOND);

        DEEPSLATE_COBBLED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_cobbled")
                .setRegistryName("deepslate_cobbled")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_COBBLED);

        DEEPSLATE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks")
                .setRegistryName("deepslate_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_BRICKS);

        DEEPSLATE_TILES = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles")
                .setRegistryName("deepslate_tiles")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_TILES);

        DEEPSLATE_POLISHED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_polished")
                .setRegistryName("deepslate_polished")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_POLISHED);

        DEEPSLATE_BRICKS_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks_cracked")
                .setRegistryName("deepslate_bricks_cracked")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_BRICKS_CRACKED);

        DEEPSLATE_TILES_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles_cracked")
                .setRegistryName("deepslate_tiles_cracked")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(DEEPSLATE_TILES_CRACKED);

        ROOTED_DIRT = new Block(Material.GROUND)
                .setUnlocalizedName("rooted_dirt")
                .setRegistryName("rooted_dirt")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(ROOTED_DIRT);

        SMOOTH_BASALT = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_basalt")
                .setRegistryName("smooth_basalt")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(SMOOTH_BASALT);

        MOSS_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("moss_block")
                .setRegistryName("moss_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(MOSS_BLOCK);

        MOSS_CARPET = new Block(Material.PLANTS)
                .setUnlocalizedName("moss_carpet")
                .setRegistryName("moss_carpet")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(MOSS_CARPET);


        ModBiomes.init();
        new WorldTypeSelectableBiome("selectable_biome");
        GameRegistry.registerTileEntity(TileEntityBeeNest.class, new ResourceLocation("ubm", "bee_nest"));
        GameRegistry.registerWorldGenerator(new WorldGenBeeNest(), 0);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        // Netherite Blocks
        registerBlockWithItem(event, NETHERITE_BLOCK);
        registerBlockWithItem(event, ANCIENT_DEBRIS);

        // Crimson Wood Blocks
        registerBlockWithItem(event, CRIMSON_STEM);
        registerBlockWithItem(event, CRIMSON_HYPHAE);
        registerBlockWithItem(event, STRIPPED_CRIMSON_STEM);
        registerBlockWithItem(event, STRIPPED_CRIMSON_HYPHAE);
        registerBlockWithItem(event, CRIMSON_PLANKS);

        // Warped Wood Blocks
        registerBlockWithItem(event, WARPED_STEM);
        registerBlockWithItem(event, WARPED_HYPHAE);
        registerBlockWithItem(event, STRIPPED_WARPED_STEM);
        registerBlockWithItem(event, STRIPPED_WARPED_HYPHAE);
        registerBlockWithItem(event, WARPED_PLANKS);

        // Special Blocks
        registerBlockWithItem(event, WARPED_STAIRS);
        registerBlockWithItem(event, WARPED_FENCE);
        registerBlockWithItem(event, CRIMSON_STAIRS);
        registerBlockWithItem(event, WARPED_WART_BLOCK);

        // Blackstone Blocks
        registerBlockWithItem(event, BLACKSTONE);
        registerBlockWithItem(event, POLISHED_BLACKSTONE);
        registerBlockWithItem(event, CHISELED_POLISHED_BLACKSTONE);
        registerBlockWithItem(event, POLISHED_BLACKSTONE_BRICKS);
        registerBlockWithItem(event, CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerBlockWithItem(event, GILDED_BLACKSTONE);
        registerBlockWithItem(event, BLACKSTONE_WALL);

        // Other Nether Blocks
        registerBlockWithItem(event, NETHER_GOLD_ORE);
        registerBlockWithItem(event, SOUL_SOIL);
        registerBlockWithItem(event, WARPED_NYLIUM);
        registerBlockWithItem(event, CRYING_OBSIDIAN);
        registerBlockWithItem(event, CRIMSON_NYLIUM);

        // Froglight Blocks
        registerBlockWithItem(event, OCHRE_FROGLIGHT);
        registerBlockWithItem(event, VERDANT_FROGLIGHT);
        registerBlockWithItem(event, PEARLESCENT_FROGLIGHT);

        // Bee Blocks
        registerBlockWithItem(event, BEE_NEST);
        registerBlockWithItem(event, BEE_HIVE);

        // Plant Blocks
        registerBlockWithItem(event, SWEET_BERRY_BUSH);

        // Copper Blocks - Normal
        registerBlockWithItem(event, COPPER_BLOCK);
        registerBlockWithItem(event, CHISELED_COPPER);
        registerBlockWithItem(event, COPPER_GRATE);
        registerBlockWithItem(event, CUT_COPPER);
        registerBlockWithItem(event, COPPER_BULB);

        // Copper Blocks - Exposed
        registerBlockWithItem(event, EXPOSED_COPPER_BLOCK);
        registerBlockWithItem(event, EXPOSED_CHISELED_COPPER);
        registerBlockWithItem(event, EXPOSED_COPPER_GRATE);
        registerBlockWithItem(event, EXPOSED_CUT_COPPER);
        registerBlockWithItem(event, EXPOSED_COPPER_BULB);

        // Copper Blocks - Weathered
        registerBlockWithItem(event, WEATHERED_COPPER_BLOCK);
        registerBlockWithItem(event, WEATHERED_CHISELED_COPPER);
        registerBlockWithItem(event, WEATHERED_COPPER_GRATE);
        registerBlockWithItem(event, WEATHERED_CUT_COPPER);
        registerBlockWithItem(event, WEATHERED_COPPER_BULB);

        // Copper Blocks - Oxidized
        registerBlockWithItem(event, OXIDIZED_COPPER_BLOCK);
        registerBlockWithItem(event, OXIDIZED_CHISELED_COPPER);
        registerBlockWithItem(event, OXIDIZED_COPPER_GRATE);
        registerBlockWithItem(event, OXIDIZED_CUT_COPPER);
        registerBlockWithItem(event, OXIDIZED_COPPER_BULB);

        // Copper Blocks - Waxed
        registerBlockWithItem(event, WAXED_COPPER_BLOCK);
        registerBlockWithItem(event, WAXED_CHISELED_COPPER);
        registerBlockWithItem(event, WAXED_COPPER_GRATE);
        registerBlockWithItem(event, WAXED_CUT_COPPER);
        registerBlockWithItem(event, WAXED_COPPER_BULB);

        // Copper Blocks - Waxed Exposed
        registerBlockWithItem(event, WAXED_EXPOSED_COPPER_BLOCK);
        registerBlockWithItem(event, WAXED_EXPOSED_CHISELED_COPPER);
        registerBlockWithItem(event, WAXED_EXPOSED_COPPER_GRATE);
        registerBlockWithItem(event, WAXED_EXPOSED_CUT_COPPER);
        registerBlockWithItem(event, WAXED_EXPOSED_COPPER_BULB);

        // Copper Blocks - Waxed Weathered
        registerBlockWithItem(event, WAXED_WEATHERED_COPPER_BLOCK);
        registerBlockWithItem(event, WAXED_WEATHERED_CHISELED_COPPER);
        registerBlockWithItem(event, WAXED_WEATHERED_COPPER_GRATE);
        registerBlockWithItem(event, WAXED_WEATHERED_CUT_COPPER);
        registerBlockWithItem(event, WAXED_WEATHERED_COPPER_BULB);

        // Copper Blocks - Waxed Oxidized
        registerBlockWithItem(event, WAXED_OXIDIZED_COPPER_BLOCK);
        registerBlockWithItem(event, WAXED_OXIDIZED_CHISELED_COPPER);
        registerBlockWithItem(event, WAXED_OXIDIZED_COPPER_GRATE);
        registerBlockWithItem(event, WAXED_OXIDIZED_CUT_COPPER);
        registerBlockWithItem(event, WAXED_OXIDIZED_COPPER_BULB);

        // Other Cave Blocks
        registerBlockWithItem(event, TINTED_GLASS);
        registerBlockWithItem(event, AMETHYST_BLOCK);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EnumHand hand = event.getHand();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        ItemStack heldItem = player.getHeldItem(hand);

        if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.POWDER_SNOW_BUCKET) {
            if (!world.isRemote) {
                BlockPos targetPos = pos.offset(event.getFace());
                world.setBlockState(targetPos, ModBlocks.POWDER_SNOW.getDefaultState());

                world.playSound(null, targetPos, SoundEvents.BLOCK_SNOW_PLACE,
                        SoundCategory.BLOCKS, 1.0F, 1.0F);

                if (!player.isCreative()) {
                    heldItem.shrink(1);
                    player.addItemStackToInventory(new ItemStack(Items.BUCKET));
                }
            }

            event.setCanceled(true);
            event.setCancellationResult(EnumActionResult.SUCCESS);
        }
        if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.SWEET_BERRY) {
            if (!world.isRemote) {
                BlockPos targetPos = pos.offset(event.getFace());
                world.setBlockState(targetPos, ModBlocks.SWEET_BERRY_BUSH.getDefaultState());

                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
            }

            event.setCanceled(true);
            event.setCancellationResult(EnumActionResult.SUCCESS);
        }
        if (!heldItem.isEmpty() && heldItem.getItem().getRegistryName().getResourcePath().toLowerCase().contains("candle")) {
            if (!world.isRemote) {
                BlockPos targetPos = pos.offset(event.getFace());
                String rawName = heldItem.getItem().getUnlocalizedName();
                String blockName = rawName.replace("item.", "").toUpperCase();

                try {
                    Block block2 = (Block) ModBlocks.class.getField(blockName).get(null);
                    world.setBlockState(targetPos, block2.getDefaultState());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!player.isCreative()) {
                    heldItem.shrink(1);
                }
            }

            event.setCanceled(true);
            event.setCancellationResult(EnumActionResult.SUCCESS);
        }
        if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.HONEYCOMB) {
            ResourceLocation res = block.getRegistryName();
            if (WAXED_VARIANTS.containsKey(res)) {
                if (!world.isRemote) {
                    world.setBlockState(pos, ForgeRegistries.BLOCKS.getValue(WAXED_VARIANTS.get(res)).getDefaultState(), 3);
                    if (!player.capabilities.isCreativeMode) heldItem.shrink(1);
                    world.playSound(null, pos, SoundEvents.BLOCK_SLIME_HIT, SoundCategory.BLOCKS, 1f, 1f);
                }
                event.setCanceled(true);
            }
        }
        if (!heldItem.isEmpty() && heldItem.getItem() instanceof ItemAxe) {
            ResourceLocation res = block.getRegistryName();
            if (UNWAXED_VARIANTS.containsKey(res)) {
                if (!world.isRemote) {
                    world.setBlockState(pos, ForgeRegistries.BLOCKS.getValue(UNWAXED_VARIANTS.get(res)).getDefaultState(), 3);
                    heldItem.damageItem(1, player);
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_HIT, SoundCategory.BLOCKS, 1f, 0.8f);
                }
                event.setCanceled(true);
            }
            if (PREVIOUS_OXIDATION.containsKey(res)) {
                if (!world.isRemote) {

                    world.setBlockState(pos, ForgeRegistries.BLOCKS.getValue(PREVIOUS_OXIDATION.get(res)).getDefaultState(), 3);
                    heldItem.damageItem(1, player);
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_HIT, SoundCategory.BLOCKS, 1f, 0.8f);
                }
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() == Blocks.GOLD_ORE) {
            event.getDrops().clear();

            if (!event.isSilkTouching()) {
                int fortune = event.getFortuneLevel();
                Random rand = event.getWorld().rand;

                int count = 1 + rand.nextInt(fortune + 1);

                for (int i = 0; i < count; i++) {
                    event.getDrops().add(new ItemStack(ModItems.RAW_GOLD));
                }
            } else {
                event.getDrops().add(new ItemStack(Blocks.GOLD_ORE));
            }
        }
        if (event.getState().getBlock() == Blocks.IRON_ORE) {
            event.getDrops().clear();

            if (!event.isSilkTouching()) {
                int fortune = event.getFortuneLevel();
                Random rand = event.getWorld().rand;

                int count = 1 + rand.nextInt(fortune + 1);

                for (int i = 0; i < count; i++) {
                    event.getDrops().add(new ItemStack(ModItems.RAW_IRON));
                }
            } else {
                event.getDrops().add(new ItemStack(Blocks.IRON_ORE));
            }
        }

    }



    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Item parItem) {
        registerItemModel(parItem, 0);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModel(Item parItem, int parMetaData) {

        ModelLoader.setCustomModelResourceLocation(parItem, parMetaData,
                new ModelResourceLocation(parItem.getRegistryName(), "inventory"));
    }
}