package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.api.BlockAmethystBud;
import de.julianweinelt.ubm.blocks.interactable.BlockSmithingTable;
import de.julianweinelt.ubm.blocks.plant.BlockGlowBerryVine;
import de.julianweinelt.ubm.blocks.plant.BlockGlowLichen;
import de.julianweinelt.ubm.blocks.plant.BlockSweetBerry;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBeeNest;
import de.julianweinelt.ubm.blocks.tiles.TileEntityCampfire;
import de.julianweinelt.ubm.items.BlockCopperTorch;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.AdvancementHelper;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.worldgen.ModBiomes;
import de.julianweinelt.ubm.worldgen.WorldGenBeeNest;
import de.julianweinelt.ubm.worldgen.WorldTypeModern;
import de.julianweinelt.ubm.worldgen.WorldTypeSelectableBiome;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
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
    private static Block LIGHTNING_ROD;
    public static Block ANCIENT_DEBRIS;

    public static Block SMITHING_TABLE;
    public static Block FLETCHING_TABLE;
    public static Block LOOM;
    public static Block CARTOGRAPHY_TABLE;
    public static Block SMOKER;
    public static Block BLAST_FURNACE;
    public static Block BARREL;
    public static Block BELL;
    public static Block SMOOTH_STONE;
    public static Block CAMPFIRE;
    public static Block GRINDSTONE;

    public static Block TARGET;

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

    public static Block STRIPPED_OAK_STEM;
    public static Block STRIPPED_OAK_WOOD;
    public static Block STRIPPED_SPRUCE_STEM;
    public static Block STRIPPED_SPRUCE_WOOD;
    public static Block STRIPPED_DARK_OAK_STEM;
    public static Block STRIPPED_DARK_OAK_WOOD;
    public static Block STRIPPED_ACACIA_STEM;
    public static Block STRIPPED_ACACIA_WOOD;
    public static Block STRIPPED_BIRCH_STEM;
    public static Block STRIPPED_BIRCH_WOOD;
    public static Block STRIPPED_JUNGLE_STEM;
    public static Block STRIPPED_JUNGLE_WOOD;

    public static Block GLOW_LICHEN;
    public static Block GLOW_BERRIES;

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
    public static Block BUDDING_AMETHYST;
    public static Block AMETHYST_CLUSTER;
    public static Block SMALL_AMETHYST_BUD;
    public static Block MEDIUM_AMETHYST_BUD;
    public static Block LARGE_AMETHYST_BUD;
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        CAMPFIRE = new BlockCampFire(false);
        event.getRegistry().register(CAMPFIRE);

        LOOM = new BlockRotated(Material.WOOD)
                .setUnlocalizedName("loom")
                .setRegistryName("loom")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(LOOM);
        SMOOTH_STONE = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_stone")
                .setRegistryName("smooth_stone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(SMOOTH_STONE);

        GRINDSTONE = new BlockRotated(Material.ROCK)
                .setUnlocalizedName("grindstone")
                .setRegistryName("grindstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(GRINDSTONE);

        SOUL_CAMPFIRE = new BlockCampFire(true)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(SOUL_CAMPFIRE);

        FLETCHING_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("fletching_table")
                .setUnlocalizedName("fletching_table")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(FLETCHING_TABLE);
        SMOKER = new BlockRotated(Material.ROCK)
                .setRegistryName("smoker")
                .setUnlocalizedName("smoker")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(SMOKER);
        BLAST_FURNACE = new BlockRotated(Material.ROCK)
                .setRegistryName("blast_furnace")
                .setUnlocalizedName("blast_furnace")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(BLAST_FURNACE);
        CARTOGRAPHY_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("cartography_table")
                .setUnlocalizedName("cartography_table")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(CARTOGRAPHY_TABLE);

        CRIMSON_BUTTON = new BlockModButton(true, "crimson_button")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_BUTTON);
        SMITHING_TABLE = new BlockSmithingTable();
        event.getRegistry().register(SMITHING_TABLE);

        COPPER_TORCH = new BlockCopperTorch("copper_torch");
        event.getRegistry().register(COPPER_TORCH);
        LIGHTNING_ROD = new BlockLightningRod();
        event.getRegistry().register(LIGHTNING_ROD);

        SMALL_AMETHYST_BUD = new BlockAmethystBud("small_amethyst_bud");
        event.getRegistry().register(SMALL_AMETHYST_BUD);
        MEDIUM_AMETHYST_BUD = new BlockAmethystBud("medium_amethyst_bud");
        event.getRegistry().register(MEDIUM_AMETHYST_BUD);
        LARGE_AMETHYST_BUD = new BlockAmethystBud("large_amethyst_bud");
        event.getRegistry().register(LARGE_AMETHYST_BUD);
        AMETHYST_CLUSTER = new BlockAmethystBud("amethyst_cluster");
        event.getRegistry().register(AMETHYST_CLUSTER);
        BUDDING_AMETHYST = new Block(Material.ROCK)
                .setUnlocalizedName("budding_amethyst")
                .setRegistryName("budding_amethyst")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(BUDDING_AMETHYST);


        NETHERITE_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("netherite_block")
                .setRegistryName("netherite_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(NETHERITE_BLOCK);

        TARGET = new Block(Material.PLANTS)
                .setUnlocalizedName("target")
                .setRegistryName("target")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(TARGET);

        GLOW_LICHEN = new BlockGlowLichen();
        event.getRegistry().register(GLOW_LICHEN);

        ANCIENT_DEBRIS = new Block(Material.ROCK)
                .setUnlocalizedName("ancient_debris")
                .setRegistryName("ancient_debris")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(ANCIENT_DEBRIS);

        CRIMSON_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_stem")
                .setRegistryName("crimson_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_STEM);
        CRIMSON_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_hyphae")
                .setRegistryName("crimson_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_HYPHAE);
        WARPED_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("warped_stem")
                .setRegistryName("warped_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_STEM);
        WARPED_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("warped_hyphae")
                .setRegistryName("warped_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_HYPHAE);

        STRIPPED_CRIMSON_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_crimson_stem")
                .setRegistryName("stripped_crimson_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(STRIPPED_CRIMSON_STEM);
        STRIPPED_CRIMSON_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_crimson_hyphae")
                .setRegistryName("stripped_crimson_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(STRIPPED_CRIMSON_HYPHAE);
        STRIPPED_WARPED_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_warped_stem")
                .setRegistryName("stripped_warped_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(STRIPPED_WARPED_STEM);
        STRIPPED_WARPED_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_warped_hyphae")
                .setRegistryName("stripped_warped_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(STRIPPED_WARPED_HYPHAE);
        BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("blackstone")
                .setRegistryName("blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(BLACKSTONE);
        POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone")
                .setRegistryName("polished_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(POLISHED_BLACKSTONE);

        CHISELED_POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("chiseled_polished_blackstone")
                .setRegistryName("chiseled_polished_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CHISELED_POLISHED_BLACKSTONE);

        POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone_bricks")
                .setRegistryName("polished_blackstone_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(POLISHED_BLACKSTONE_BRICKS);

        CRACKED_POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("cracked_polished_blackstone_bricks")
                .setRegistryName("cracked_polished_blackstone_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRACKED_POLISHED_BLACKSTONE_BRICKS);

        GILDED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("gilded_blackstone")
                .setRegistryName("gilded_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(GILDED_BLACKSTONE);

        NETHER_GOLD_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("nether_gold_ore")
                .setRegistryName("nether_gold_ore")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(NETHER_GOLD_ORE);

        SOUL_SOIL = new Block(Material.GROUND)
                .setUnlocalizedName("soul_soil")
                .setRegistryName("soul_soil")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(SOUL_SOIL);

        WARPED_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("warped_nylium")
                .setRegistryName("warped_nylium")
                .setHardness(2.0F)
                .setResistance(5.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_NYLIUM);

        CRYING_OBSIDIAN = new Block(Material.ROCK)
                .setUnlocalizedName("crying_obsidian")
                .setRegistryName("crying_obsidian")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRYING_OBSIDIAN);

        CRIMSON_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("crimson_nylium")
                .setRegistryName("crimson_nylium")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_NYLIUM);

        CRIMSON_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_planks")
                .setRegistryName("crimson_planks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_PLANKS);

        WARPED_PLANKS = new BlockNewLog()
                .setUnlocalizedName("warped_planks")
                .setRegistryName("warped_planks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_PLANKS);

        STRIPPED_OAK_STEM = new BlockStrippedStem("oak", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_OAK_STEM);

        STRIPPED_OAK_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_oak_wood")
                .setRegistryName("stripped_oak_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_OAK_WOOD);

        STRIPPED_SPRUCE_STEM = new BlockStrippedStem("spruce", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_SPRUCE_STEM);
        STRIPPED_SPRUCE_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_spruce_wood")
                .setRegistryName("stripped_spruce_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_SPRUCE_WOOD);

        STRIPPED_BIRCH_STEM = new BlockStrippedStem("birch", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_BIRCH_STEM);

        STRIPPED_BIRCH_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_birch_wood")
                .setRegistryName("stripped_birch_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_BIRCH_WOOD);


        STRIPPED_DARK_OAK_STEM = new BlockStrippedStem("dark_oak", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_DARK_OAK_STEM);

        STRIPPED_DARK_OAK_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_dark_oak_wood")
                .setRegistryName("stripped_dark_oak_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_DARK_OAK_WOOD);

        STRIPPED_ACACIA_STEM = new BlockStrippedStem("acacia", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_ACACIA_STEM);

        STRIPPED_ACACIA_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_acacia_wood")
                .setRegistryName("stripped_acacia_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_ACACIA_WOOD);

        STRIPPED_JUNGLE_STEM = new BlockStrippedStem("jungle", ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_JUNGLE_STEM);

        STRIPPED_JUNGLE_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_jungle_wood")
                .setRegistryName("stripped_jungle_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(STRIPPED_JUNGLE_WOOD);


        WARPED_STAIRS = new BlockModStairs(WARPED_PLANKS.getDefaultState(), "warped_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_STAIRS);
        CRIMSON_STAIRS = new BlockModStairs(CRIMSON_PLANKS.getDefaultState(), "crimson_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(CRIMSON_STAIRS);
        WARPED_FENCE = new BlockModFence(Material.WOOD, "warped_fence")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_FENCE);

        WARPED_WART_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("warped_wart_block")
                .setRegistryName("warped_wart_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_WART_BLOCK);

        BLACKSTONE_WALL = new BlockWall(BLACKSTONE)
                .setUnlocalizedName("blackstone_wall")
                .setRegistryName("blackstone_wall")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(BLACKSTONE_WALL);

        PEARLESCENT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("pearlescent_froglight")
                .setRegistryName("pearlescent_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        event.getRegistry().register(PEARLESCENT_FROGLIGHT);

        OCHRE_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("ochre_froglight")
                .setRegistryName("ochre_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        event.getRegistry().register(OCHRE_FROGLIGHT);

        VERDANT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("verdant_froglight")
                .setRegistryName("verdant_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        event.getRegistry().register(VERDANT_FROGLIGHT);

        POWDER_SNOW = new BlockPowderSnow();
        event.getRegistry().register(POWDER_SNOW);


        BEE_NEST = new BlockBeeNest();
        event.getRegistry().register(BEE_NEST);
        BEE_HIVE = new Block(Material.WOOD)
                .setUnlocalizedName("bee_hive")
                .setRegistryName("bee_hive")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        event.getRegistry().register(BEE_HIVE);

        SWEET_BERRY_BUSH = new BlockSweetBerry().setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(SWEET_BERRY_BUSH);
        GLOW_BERRIES = new BlockGlowBerryVine();
        event.getRegistry().register(GLOW_BERRIES);

        CANDLE = new BlockCandle().setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setRegistryName("candle").setUnlocalizedName("candle");
        event.getRegistry().register(CANDLE);

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

        COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("copper_block")
                .setRegistryName("copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(COPPER_BLOCK);

        CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("chiseled_copper")
                .setRegistryName("chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(CHISELED_COPPER);

        COPPER_GRATE = new BlockCopperGrate("");
        event.getRegistry().register(COPPER_GRATE);

        CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("cut_copper")
                .setRegistryName("cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(CUT_COPPER);

        COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("copper_bulb")
                .setRegistryName("copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(COPPER_BULB);

        EXPOSED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_copper_block")
                .setRegistryName("exposed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(EXPOSED_COPPER_BLOCK);

        EXPOSED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_chiseled_copper")
                .setRegistryName("exposed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(EXPOSED_CHISELED_COPPER);

        EXPOSED_COPPER_GRATE = new BlockCopperGrate("exposed");
        event.getRegistry().register(EXPOSED_COPPER_GRATE);

        EXPOSED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_cut_copper")
                .setRegistryName("exposed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(EXPOSED_CUT_COPPER);

        EXPOSED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_copper_bulb")
                .setRegistryName("exposed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(EXPOSED_COPPER_BULB);

        WEATHERED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_copper_block")
                .setRegistryName("weathered_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WEATHERED_COPPER_BLOCK);

        WEATHERED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_chiseled_copper")
                .setRegistryName("weathered_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WEATHERED_CHISELED_COPPER);

        WEATHERED_COPPER_GRATE = new BlockCopperGrate("weathered");
        event.getRegistry().register(WEATHERED_COPPER_GRATE);

        WEATHERED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_cut_copper")
                .setRegistryName("weathered_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WEATHERED_CUT_COPPER);

        WEATHERED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_copper_bulb")
                .setRegistryName("weathered_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WEATHERED_COPPER_BULB);

        OXIDIZED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_copper_block")
                .setRegistryName("oxidized_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(OXIDIZED_COPPER_BLOCK);

        OXIDIZED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_chiseled_copper")
                .setRegistryName("oxidized_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(OXIDIZED_CHISELED_COPPER);

        OXIDIZED_COPPER_GRATE = new BlockCopperGrate("oxidized");
        event.getRegistry().register(OXIDIZED_COPPER_GRATE);

        OXIDIZED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_cut_copper")
                .setRegistryName("oxidized_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(OXIDIZED_CUT_COPPER);

        OXIDIZED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_copper_bulb")
                .setRegistryName("oxidized_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(OXIDIZED_COPPER_BULB);

        // WAXED
        WAXED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_block")
                .setRegistryName("waxed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_COPPER_BLOCK);

        WAXED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_chiseled_copper")
                .setRegistryName("waxed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_CHISELED_COPPER);

        WAXED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_grate")
                .setRegistryName("waxed_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_COPPER_GRATE);

        WAXED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_cut_copper")
                .setRegistryName("waxed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_CUT_COPPER);

        WAXED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_bulb")
                .setRegistryName("waxed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_COPPER_BULB);

// WAXED_EXPOSED
        WAXED_EXPOSED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_block")
                .setRegistryName("waxed_exposed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_EXPOSED_COPPER_BLOCK);

        WAXED_EXPOSED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_chiseled_copper")
                .setRegistryName("waxed_exposed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_EXPOSED_CHISELED_COPPER);

        WAXED_EXPOSED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_grate")
                .setRegistryName("waxed_exposed_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_EXPOSED_COPPER_GRATE);

        WAXED_EXPOSED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_cut_copper")
                .setRegistryName("waxed_exposed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_EXPOSED_CUT_COPPER);

        WAXED_EXPOSED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_bulb")
                .setRegistryName("waxed_exposed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_EXPOSED_COPPER_BULB);

// WAXED_WEATHERED
        WAXED_WEATHERED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_block")
                .setRegistryName("waxed_weathered_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_WEATHERED_COPPER_BLOCK);

        WAXED_WEATHERED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_chiseled_copper")
                .setRegistryName("waxed_weathered_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_WEATHERED_CHISELED_COPPER);

        WAXED_WEATHERED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_grate")
                .setRegistryName("waxed_weathered_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_WEATHERED_COPPER_GRATE);

        WAXED_WEATHERED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_cut_copper")
                .setRegistryName("waxed_weathered_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_WEATHERED_CUT_COPPER);

        WAXED_WEATHERED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_bulb")
                .setRegistryName("waxed_weathered_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_WEATHERED_COPPER_BULB);

// WAXED_OXIDIZED
        WAXED_OXIDIZED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_block")
                .setRegistryName("waxed_oxidized_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_OXIDIZED_COPPER_BLOCK);

        WAXED_OXIDIZED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_chiseled_copper")
                .setRegistryName("waxed_oxidized_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_OXIDIZED_CHISELED_COPPER);

        WAXED_OXIDIZED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_grate")
                .setRegistryName("waxed_oxidized_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_OXIDIZED_COPPER_GRATE);

        WAXED_OXIDIZED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_cut_copper")
                .setRegistryName("waxed_oxidized_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_OXIDIZED_CUT_COPPER);

        WAXED_OXIDIZED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_bulb")
                .setRegistryName("waxed_oxidized_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(WAXED_OXIDIZED_COPPER_BULB);

        TINTED_GLASS = new BlockTintedGlass();
        event.getRegistry().register(TINTED_GLASS);

        AMETHYST_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("amethyst")
                .setRegistryName("amethyst")
                        .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(AMETHYST_BLOCK);

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

        MOSS_CARPET = new BlockMossCarpet();
        event.getRegistry().register(MOSS_CARPET);


        ModBiomes.init();
        new WorldTypeSelectableBiome("selectable_biome");
        new WorldTypeModern();
        GameRegistry.registerTileEntity(TileEntityBeeNest.class, new ResourceLocation("ubm", "bee_nest"));
        GameRegistry.registerWorldGenerator(new WorldGenBeeNest(), 0);
        GameRegistry.registerTileEntity(TileEntityCampfire.class, "ubm:campfire");
    }
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(COPPER_TORCH).setRegistryName(COPPER_TORCH.getRegistryName()));

        registerItem(GRINDSTONE, event);
        registerItem(SMOOTH_STONE, event);
        registerItem(LOOM, event);

        registerItem(CARTOGRAPHY_TABLE, event);
        registerItem(FLETCHING_TABLE, event);
        registerItem(BLAST_FURNACE, event);
        registerItem(SMOKER, event);

        registerItem(CAMPFIRE, event);
        registerItem(SOUL_CAMPFIRE, event);
        registerItem(CRIMSON_BUTTON, event);
        registerItem(SMITHING_TABLE, event);

        registerItem(AMETHYST_CLUSTER, event);
        registerItem(BUDDING_AMETHYST, event);
        registerItem(SMALL_AMETHYST_BUD, event);
        registerItem(MEDIUM_AMETHYST_BUD, event);
        registerItem(LARGE_AMETHYST_BUD, event);

        registerItem(TARGET, event);

        registerItem(NETHERITE_BLOCK, event);
        registerItem(GLOW_LICHEN, event);
        registerItem(ANCIENT_DEBRIS, event);
        registerItem(CRIMSON_STEM, event);
        registerItem(CRIMSON_HYPHAE, event);
        registerItem(WARPED_STEM, event);
        registerItem(WARPED_HYPHAE, event);
        registerItem(WARPED_STAIRS, event);
        registerItem(WARPED_FENCE, event);
        registerItem(CRIMSON_STAIRS, event);

        registerItem(BLACKSTONE, event);
        registerItem(POLISHED_BLACKSTONE, event);
        registerItem(CHISELED_POLISHED_BLACKSTONE, event);
        registerItem(POLISHED_BLACKSTONE_BRICKS, event);
        registerItem(CRACKED_POLISHED_BLACKSTONE_BRICKS, event);
        registerItem(GILDED_BLACKSTONE, event);

        registerItem(NETHER_GOLD_ORE, event);
        registerItem(SOUL_SOIL, event);
        registerItem(WARPED_NYLIUM, event);
        registerItem(CRYING_OBSIDIAN, event);
        registerItem(CRIMSON_NYLIUM, event);
        registerItem(CRIMSON_PLANKS, event);
        registerItem(WARPED_PLANKS, event);
        registerItem(WARPED_WART_BLOCK, event);
        registerItem(BLACKSTONE_WALL, event);

        registerItem(OCHRE_FROGLIGHT, event);
        registerItem(VERDANT_FROGLIGHT, event);
        registerItem(PEARLESCENT_FROGLIGHT, event);

        registerItem(BEE_NEST, event);
        registerItem(BEE_HIVE, event);


        registerItem(COPPER_BLOCK, event);
        registerItem(CHISELED_COPPER, event);
        registerItem(COPPER_GRATE, event);
        registerItem(CUT_COPPER, event);
        registerItem(COPPER_BULB, event);

        registerItem(EXPOSED_COPPER_BLOCK, event);
        registerItem(EXPOSED_CHISELED_COPPER, event);
        registerItem(EXPOSED_COPPER_GRATE, event);
        registerItem(EXPOSED_CUT_COPPER, event);
        registerItem(EXPOSED_COPPER_BULB, event);

        registerItem(WEATHERED_COPPER_BLOCK, event);
        registerItem(WEATHERED_CHISELED_COPPER, event);
        registerItem(WEATHERED_COPPER_GRATE, event);
        registerItem(WEATHERED_CUT_COPPER, event);
        registerItem(WEATHERED_COPPER_BULB, event);

        registerItem(OXIDIZED_COPPER_BLOCK, event);
        registerItem(OXIDIZED_CHISELED_COPPER, event);
        registerItem(OXIDIZED_COPPER_GRATE, event);
        registerItem(OXIDIZED_CUT_COPPER, event);
        registerItem(OXIDIZED_COPPER_BULB, event);

        registerItem(WAXED_COPPER_BLOCK, event);
        registerItem(WAXED_CHISELED_COPPER, event);
        registerItem(WAXED_COPPER_GRATE, event);
        registerItem(WAXED_CUT_COPPER, event);
        registerItem(WAXED_COPPER_BULB, event);

        registerItem(WAXED_EXPOSED_COPPER_BLOCK, event);
        registerItem(WAXED_EXPOSED_CHISELED_COPPER, event);
        registerItem(WAXED_EXPOSED_COPPER_GRATE, event);
        registerItem(WAXED_EXPOSED_CUT_COPPER, event);
        registerItem(WAXED_EXPOSED_COPPER_BULB, event);

        registerItem(WAXED_WEATHERED_COPPER_BLOCK, event);
        registerItem(WAXED_WEATHERED_CHISELED_COPPER, event);
        registerItem(WAXED_WEATHERED_COPPER_GRATE, event);
        registerItem(WAXED_WEATHERED_CUT_COPPER, event);
        registerItem(WAXED_WEATHERED_COPPER_BULB, event);

        registerItem(WAXED_OXIDIZED_COPPER_BLOCK, event);
        registerItem(WAXED_OXIDIZED_CHISELED_COPPER, event);
        registerItem(WAXED_OXIDIZED_COPPER_GRATE, event);
        registerItem(WAXED_OXIDIZED_CUT_COPPER, event);
        registerItem(WAXED_OXIDIZED_COPPER_BULB, event);

        registerItem(TINTED_GLASS, event);
        registerItem(AMETHYST_BLOCK, event);
        registerItem(CALCITE, event);
        registerItem(TUFF, event);
        registerItem(COPPER_ORE, event);

        registerItem(DEEPSLATE, event);

        registerItem(DEEPSLATE_ORE_COPPER, event);
        registerItem(DEEPSLATE_ORE_GOLD, event);
        registerItem(DEEPSLATE_ORE_IRON, event);
        registerItem(DEEPSLATE_ORE_REDSTONE, event);
        registerItem(DEEPSLATE_ORE_LAPISLAZULI, event);
        registerItem(DEEPSLATE_ORE_EMERALD, event);
        registerItem(DEEPSLATE_ORE_DIAMOND, event);

        registerItem(DEEPSLATE_COBBLED, event);
        registerItem(DEEPSLATE_BRICKS, event);
        registerItem(DEEPSLATE_TILES, event);
        registerItem(DEEPSLATE_POLISHED, event);
        registerItem(DEEPSLATE_BRICKS_CRACKED, event);
        registerItem(DEEPSLATE_TILES_CRACKED, event);

        registerItem(ROOTED_DIRT, event);
        registerItem(SMOOTH_BASALT, event);
        registerItem(MOSS_BLOCK, event);
        registerItem(MOSS_CARPET, event);
        registerItem(LIGHTNING_ROD, event);


        registerItem(STRIPPED_OAK_STEM, event);
        registerItem(STRIPPED_OAK_WOOD, event);

        registerItem(STRIPPED_SPRUCE_STEM, event);
        registerItem(STRIPPED_SPRUCE_WOOD, event);

        registerItem(STRIPPED_BIRCH_STEM, event);
        registerItem(STRIPPED_BIRCH_WOOD, event);

        registerItem(STRIPPED_DARK_OAK_STEM, event);
        registerItem(STRIPPED_DARK_OAK_WOOD, event);

        registerItem(STRIPPED_ACACIA_STEM, event);
        registerItem(STRIPPED_ACACIA_WOOD, event);

        registerItem(STRIPPED_JUNGLE_STEM, event);
        registerItem(STRIPPED_JUNGLE_WOOD, event);

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
        if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.GLOW_BERRIES) {
            if (!world.isRemote) {
                if (!event.getFace().equals(EnumFacing.DOWN)) {
                    event.setCanceled(true);
                    event.setCancellationResult(EnumActionResult.PASS);
                    return;
                }
                BlockPos targetPos = pos.offset(event.getFace());
                world.setBlockState(targetPos, ModBlocks.GLOW_BERRIES.getDefaultState());

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
                AdvancementHelper.grantAdvancement(player, "wax_on");
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
                AdvancementHelper.grantAdvancement(player, "wax_off");
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


    public static void registerItem(Block block, RegistryEvent.Register<Item> event) {
        Item item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        event.getRegistry().register(item);
        registerItemModel(item);
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