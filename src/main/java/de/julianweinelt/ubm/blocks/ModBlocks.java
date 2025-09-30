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
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
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

import java.util.*;

@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection"})
@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModBlocks {
    public static final Map<ResourceLocation, ResourceLocation> WAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> UNWAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> PREVIOUS_OXIDATION = new HashMap<>();
    
    private static final List<Block> blocks = new ArrayList<>();

    public static Block NETHERITE_BLOCK;
    public static Block LIGHTNING_ROD;
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

    public static Block STRIPPED_PALE_OAK_LOG;
    public static Block PALE_OAK_LOG;
    public static Block PALE_OAK_PLANKS;
    public static Block PALE_OAK_LEAVES;
    public static Block PALE_OAK_DOOR;
    public static Block PALE_OAK_TRAPDOOR;
    public static Block PALE_OAK_SAPLING;
    public static Block PALE_OAK_MOSS;
    public static Block PALE_OAK_MOSS_CARPET;

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
    public static Block DEEPSLATE_CHISELED;
    public static Block DEEPSLATE_POLISHED;
    public static Block DEEPSLATE_BRICKS_CRACKED;
    public static Block DEEPSLATE_TILES_CRACKED;
    public static Block DEEPSLATE_COBBLED_STAIRS;
    public static Block DEEPSLATE_COBBLED_WALL;
    public static Block DEEPSLATE_COBBLED_SLAB;
    public static Block DEEPSLATE_POLISHED_STAIRS;
    public static Block DEEPSLATE_POLISHED_SLAB;
    public static Block DEEPSLATE_POLISHED_WALL;
    public static Block DEEPSLATE_BRICK_STAIRS;
    public static Block DEEPSLATE_BRICK_SLAB;
    public static Block DEEPSLATE_BRICK_WALL;
    public static Block DEEPSLATE_TILE_STAIRS;
    public static Block DEEPSLATE_TILE_SLAB;
    public static Block DEEPSLATE_TILE_WALL;

    public static Block ANDESITE_POLISHED_STAIRS;
    public static Block GRANITE_POLISHED_STAIRS;
    public static Block DIORITE_POLISHED_STAIRS;
    public static Block ANDESITE_STAIRS;
    public static Block GRANITE_STAIRS;
    public static Block DIORITE_STAIRS;

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
        register(CAMPFIRE, event);

        LOOM = new BlockRotated(Material.WOOD)
                .setUnlocalizedName("loom")
                .setRegistryName("loom")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(LOOM, event);
        SMOOTH_STONE = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_stone")
                .setRegistryName("smooth_stone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(SMOOTH_STONE, event);

        GRINDSTONE = new BlockRotated(Material.ROCK)
                .setUnlocalizedName("grindstone")
                .setRegistryName("grindstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(GRINDSTONE, event);

        SOUL_CAMPFIRE = new BlockCampFire(true)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(SOUL_CAMPFIRE, event);

        FLETCHING_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("fletching_table")
                .setUnlocalizedName("fletching_table")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(FLETCHING_TABLE, event);
        SMOKER = new BlockRotated(Material.ROCK)
                .setRegistryName("smoker")
                .setUnlocalizedName("smoker")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(SMOKER, event);
        BLAST_FURNACE = new BlockRotated(Material.ROCK)
                .setRegistryName("blast_furnace")
                .setUnlocalizedName("blast_furnace")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(BLAST_FURNACE, event);
        CARTOGRAPHY_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("cartography_table")
                .setUnlocalizedName("cartography_table")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(CARTOGRAPHY_TABLE, event);

        CRIMSON_BUTTON = new BlockModButton(true, "crimson_button")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_BUTTON, event);
        WARPED_BUTTON = new BlockModButton(true, "warped_button")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_BUTTON, event);
        SMITHING_TABLE = new BlockSmithingTable();
        register(SMITHING_TABLE, event);

        COPPER_TORCH = new BlockCopperTorch("copper_torch");
        register(COPPER_TORCH, event);
        LIGHTNING_ROD = new BlockLightningRod();
        register(LIGHTNING_ROD, event);

        SMALL_AMETHYST_BUD = new BlockAmethystBud("small_amethyst_bud");
        register(SMALL_AMETHYST_BUD, event);
        MEDIUM_AMETHYST_BUD = new BlockAmethystBud("medium_amethyst_bud");
        register(MEDIUM_AMETHYST_BUD, event);
        LARGE_AMETHYST_BUD = new BlockAmethystBud("large_amethyst_bud");
        register(LARGE_AMETHYST_BUD, event);
        AMETHYST_CLUSTER = new BlockAmethystBud("amethyst_cluster");
        register(AMETHYST_CLUSTER, event);
        BUDDING_AMETHYST = new Block(Material.ROCK)
                .setUnlocalizedName("budding_amethyst")
                .setRegistryName("budding_amethyst")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(BUDDING_AMETHYST, event);


        NETHERITE_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("netherite_block")
                .setRegistryName("netherite_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(NETHERITE_BLOCK, event);

        TARGET = new Block(Material.PLANTS)
                .setUnlocalizedName("target")
                .setRegistryName("target")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(TARGET, event);

        GLOW_LICHEN = new BlockGlowLichen();
        register(GLOW_LICHEN, event);

        ANCIENT_DEBRIS = new Block(Material.ROCK)
                .setUnlocalizedName("ancient_debris")
                .setRegistryName("ancient_debris")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(ANCIENT_DEBRIS, event);

        CRIMSON_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_stem")
                .setRegistryName("crimson_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_STEM, event);
        CRIMSON_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_hyphae")
                .setRegistryName("crimson_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_HYPHAE, event);
        WARPED_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("warped_stem")
                .setRegistryName("warped_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_STEM, event);
        WARPED_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("warped_hyphae")
                .setRegistryName("warped_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_HYPHAE, event);

        STRIPPED_CRIMSON_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_crimson_stem")
                .setRegistryName("stripped_crimson_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(STRIPPED_CRIMSON_STEM, event);
        STRIPPED_CRIMSON_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_crimson_hyphae")
                .setRegistryName("stripped_crimson_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(STRIPPED_CRIMSON_HYPHAE, event);
        STRIPPED_WARPED_STEM = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_warped_stem")
                .setRegistryName("stripped_warped_stem")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(STRIPPED_WARPED_STEM, event);
        STRIPPED_WARPED_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_warped_hyphae")
                .setRegistryName("stripped_warped_hyphae")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(STRIPPED_WARPED_HYPHAE, event);
        BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("blackstone")
                .setRegistryName("blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(BLACKSTONE, event);
        POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone")
                .setRegistryName("polished_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(POLISHED_BLACKSTONE, event);

        CHISELED_POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("chiseled_polished_blackstone")
                .setRegistryName("chiseled_polished_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CHISELED_POLISHED_BLACKSTONE, event);

        POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone_bricks")
                .setRegistryName("polished_blackstone_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(POLISHED_BLACKSTONE_BRICKS, event);

        CRACKED_POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("cracked_polished_blackstone_bricks")
                .setRegistryName("cracked_polished_blackstone_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRACKED_POLISHED_BLACKSTONE_BRICKS, event);

        GILDED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("gilded_blackstone")
                .setRegistryName("gilded_blackstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(GILDED_BLACKSTONE, event);

        NETHER_GOLD_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("nether_gold_ore")
                .setRegistryName("nether_gold_ore")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(NETHER_GOLD_ORE, event);

        SOUL_SOIL = new Block(Material.GROUND)
                .setUnlocalizedName("soul_soil")
                .setRegistryName("soul_soil")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(SOUL_SOIL, event);

        WARPED_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("warped_nylium")
                .setRegistryName("warped_nylium")
                .setHardness(2.0F)
                .setResistance(5.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_NYLIUM, event);

        CRYING_OBSIDIAN = new Block(Material.ROCK)
                .setUnlocalizedName("crying_obsidian")
                .setRegistryName("crying_obsidian")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRYING_OBSIDIAN, event);

        CRIMSON_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("crimson_nylium")
                .setRegistryName("crimson_nylium")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_NYLIUM, event);

        CRIMSON_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_planks")
                .setRegistryName("crimson_planks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_PLANKS, event);

        WARPED_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("warped_planks")
                .setRegistryName("warped_planks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_PLANKS, event);

        STRIPPED_OAK_STEM = new BlockStrippedStem("oak", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_OAK_STEM, event);

        STRIPPED_OAK_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_oak_wood")
                .setRegistryName("stripped_oak_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_OAK_WOOD, event);

        STRIPPED_SPRUCE_STEM = new BlockStrippedStem("spruce", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_SPRUCE_STEM, event);
        STRIPPED_SPRUCE_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_spruce_wood")
                .setRegistryName("stripped_spruce_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_SPRUCE_WOOD, event);

        STRIPPED_BIRCH_STEM = new BlockStrippedStem("birch", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_BIRCH_STEM, event);

        STRIPPED_BIRCH_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_birch_wood")
                .setRegistryName("stripped_birch_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_BIRCH_WOOD, event);


        STRIPPED_DARK_OAK_STEM = new BlockStrippedStem("dark_oak", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_DARK_OAK_STEM, event);

        STRIPPED_DARK_OAK_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_dark_oak_wood")
                .setRegistryName("stripped_dark_oak_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_DARK_OAK_WOOD, event);

        STRIPPED_ACACIA_STEM = new BlockStrippedStem("acacia", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_ACACIA_STEM, event);

        STRIPPED_ACACIA_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_acacia_wood")
                .setRegistryName("stripped_acacia_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_ACACIA_WOOD, event);

        STRIPPED_JUNGLE_STEM = new BlockStrippedStem("jungle", ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_JUNGLE_STEM, event);

        STRIPPED_JUNGLE_WOOD = new Block(Material.WOOD)
                .setUnlocalizedName("stripped_jungle_wood")
                .setRegistryName("stripped_jungle_wood")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        register(STRIPPED_JUNGLE_WOOD, event);


        WARPED_STAIRS = new BlockModStairs(WARPED_PLANKS.getDefaultState(), "warped_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_STAIRS, event);
        CRIMSON_STAIRS = new BlockModStairs(CRIMSON_PLANKS.getDefaultState(), "crimson_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_STAIRS, event);
        WARPED_FENCE = new BlockModFence(Material.WOOD, "warped_fence")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_FENCE, event);
        CRIMSON_FENCE = new BlockModFence(Material.WOOD, "crimson_fence")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(CRIMSON_FENCE, event);

        WARPED_WART_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("warped_wart_block")
                .setRegistryName("warped_wart_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(WARPED_WART_BLOCK, event);

        BLACKSTONE_WALL = new BlockWall(BLACKSTONE)
                .setUnlocalizedName("blackstone_wall")
                .setRegistryName("blackstone_wall")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(BLACKSTONE_WALL, event);

        PEARLESCENT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("pearlescent_froglight")
                .setRegistryName("pearlescent_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        register(PEARLESCENT_FROGLIGHT, event);

        OCHRE_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("ochre_froglight")
                .setRegistryName("ochre_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        register(OCHRE_FROGLIGHT, event);

        VERDANT_FROGLIGHT = new Block(Material.PLANTS)
                .setUnlocalizedName("verdant_froglight")
                .setRegistryName("verdant_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        register(VERDANT_FROGLIGHT, event);

        POWDER_SNOW = new BlockPowderSnow();
        event.getRegistry().register(POWDER_SNOW);


        BEE_NEST = new BlockBeeNest();
        register(BEE_NEST, event);
        BEE_HIVE = new Block(Material.WOOD)
                .setUnlocalizedName("bee_hive")
                .setRegistryName("bee_hive")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        register(BEE_HIVE, event);

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
        register(COPPER_BLOCK, event);

        CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("chiseled_copper")
                .setRegistryName("chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(CHISELED_COPPER, event);

        COPPER_GRATE = new BlockCopperGrate("");
        register(COPPER_GRATE, event);

        CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("cut_copper")
                .setRegistryName("cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(CUT_COPPER, event);

        COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("copper_bulb")
                .setRegistryName("copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(COPPER_BULB, event);

        EXPOSED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_copper_block")
                .setRegistryName("exposed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(EXPOSED_COPPER_BLOCK, event);

        EXPOSED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_chiseled_copper")
                .setRegistryName("exposed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(EXPOSED_CHISELED_COPPER, event);

        EXPOSED_COPPER_GRATE = new BlockCopperGrate("exposed");
        register(EXPOSED_COPPER_GRATE, event);

        EXPOSED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_cut_copper")
                .setRegistryName("exposed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(EXPOSED_CUT_COPPER, event);

        EXPOSED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_copper_bulb")
                .setRegistryName("exposed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(EXPOSED_COPPER_BULB, event);

        WEATHERED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_copper_block")
                .setRegistryName("weathered_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WEATHERED_COPPER_BLOCK, event);

        WEATHERED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_chiseled_copper")
                .setRegistryName("weathered_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WEATHERED_CHISELED_COPPER, event);

        WEATHERED_COPPER_GRATE = new BlockCopperGrate("weathered");
        register(WEATHERED_COPPER_GRATE, event);

        WEATHERED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_cut_copper")
                .setRegistryName("weathered_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WEATHERED_CUT_COPPER, event);

        WEATHERED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_copper_bulb")
                .setRegistryName("weathered_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WEATHERED_COPPER_BULB, event);

        OXIDIZED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_copper_block")
                .setRegistryName("oxidized_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(OXIDIZED_COPPER_BLOCK, event);

        OXIDIZED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_chiseled_copper")
                .setRegistryName("oxidized_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(OXIDIZED_CHISELED_COPPER, event);

        OXIDIZED_COPPER_GRATE = new BlockCopperGrate("oxidized");
        register(OXIDIZED_COPPER_GRATE, event);

        OXIDIZED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_cut_copper")
                .setRegistryName("oxidized_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(OXIDIZED_CUT_COPPER, event);

        OXIDIZED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_copper_bulb")
                .setRegistryName("oxidized_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(OXIDIZED_COPPER_BULB, event);

        // WAXED
        WAXED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_block")
                .setRegistryName("waxed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_COPPER_BLOCK, event);

        WAXED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_chiseled_copper")
                .setRegistryName("waxed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_CHISELED_COPPER, event);

        WAXED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_grate")
                .setRegistryName("waxed_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_COPPER_GRATE, event);

        WAXED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_cut_copper")
                .setRegistryName("waxed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_CUT_COPPER, event);

        WAXED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_copper_bulb")
                .setRegistryName("waxed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_COPPER_BULB, event);

// WAXED_EXPOSED
        WAXED_EXPOSED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_block")
                .setRegistryName("waxed_exposed_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_EXPOSED_COPPER_BLOCK, event);

        WAXED_EXPOSED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_chiseled_copper")
                .setRegistryName("waxed_exposed_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_EXPOSED_CHISELED_COPPER, event);

        WAXED_EXPOSED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_grate")
                .setRegistryName("waxed_exposed_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_EXPOSED_COPPER_GRATE, event);

        WAXED_EXPOSED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_cut_copper")
                .setRegistryName("waxed_exposed_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_EXPOSED_CUT_COPPER, event);

        WAXED_EXPOSED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_exposed_copper_bulb")
                .setRegistryName("waxed_exposed_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_EXPOSED_COPPER_BULB, event);

// WAXED_WEATHERED
        WAXED_WEATHERED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_block")
                .setRegistryName("waxed_weathered_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_WEATHERED_COPPER_BLOCK, event);

        WAXED_WEATHERED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_chiseled_copper")
                .setRegistryName("waxed_weathered_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_WEATHERED_CHISELED_COPPER, event);

        WAXED_WEATHERED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_grate")
                .setRegistryName("waxed_weathered_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_WEATHERED_COPPER_GRATE, event);

        WAXED_WEATHERED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_cut_copper")
                .setRegistryName("waxed_weathered_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_WEATHERED_CUT_COPPER, event);

        WAXED_WEATHERED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_weathered_copper_bulb")
                .setRegistryName("waxed_weathered_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_WEATHERED_COPPER_BULB, event);

// WAXED_OXIDIZED
        WAXED_OXIDIZED_COPPER_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_block")
                .setRegistryName("waxed_oxidized_copper_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_OXIDIZED_COPPER_BLOCK, event);

        WAXED_OXIDIZED_CHISELED_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_chiseled_copper")
                .setRegistryName("waxed_oxidized_chiseled_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_OXIDIZED_CHISELED_COPPER, event);

        WAXED_OXIDIZED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_grate")
                .setRegistryName("waxed_oxidized_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_OXIDIZED_COPPER_GRATE, event);

        WAXED_OXIDIZED_CUT_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_cut_copper")
                .setRegistryName("waxed_oxidized_cut_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_OXIDIZED_CUT_COPPER, event);

        WAXED_OXIDIZED_COPPER_BULB = new Block(Material.ROCK)
                .setUnlocalizedName("waxed_oxidized_copper_bulb")
                .setRegistryName("waxed_oxidized_copper_bulb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(WAXED_OXIDIZED_COPPER_BULB, event);

        TINTED_GLASS = new BlockTintedGlass();
        register(TINTED_GLASS, event);

        AMETHYST_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("amethyst")
                .setRegistryName("amethyst")
                        .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(AMETHYST_BLOCK, event);

        CALCITE = new Block(Material.ROCK)
                .setUnlocalizedName("calcite")
                .setRegistryName("calcite")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(CALCITE, event);

        TUFF = new Block(Material.ROCK)
                .setUnlocalizedName("tuff")
                .setRegistryName("tuff")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(TUFF, event);

        COPPER_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("copper_ore")
                .setRegistryName("copper_ore")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(COPPER_ORE, event);

        DEEPSLATE = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate")
                .setRegistryName("deepslate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE, event);

        DEEPSLATE_ORE_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_copper")
                .setRegistryName("deepslate_ore_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_COPPER, event);

        DEEPSLATE_ORE_GOLD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_gold")
                .setRegistryName("deepslate_ore_gold")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_GOLD, event);

        DEEPSLATE_ORE_IRON = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_iron")
                .setRegistryName("deepslate_ore_iron")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_IRON, event);

        DEEPSLATE_ORE_REDSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_redstone")
                .setRegistryName("deepslate_ore_redstone")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_REDSTONE, event);

        DEEPSLATE_ORE_LAPISLAZULI = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_lapislazuli")
                .setRegistryName("deepslate_ore_lapislazuli")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_LAPISLAZULI, event);

        DEEPSLATE_ORE_EMERALD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_emerald")
                .setRegistryName("deepslate_ore_emerald")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_EMERALD, event);

        DEEPSLATE_ORE_DIAMOND = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_diamond")
                .setRegistryName("deepslate_ore_diamond")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_ORE_DIAMOND, event);

        DEEPSLATE_COBBLED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_cobbled")
                .setRegistryName("deepslate_cobbled")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_COBBLED, event);

        DEEPSLATE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks")
                .setRegistryName("deepslate_bricks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_BRICKS, event);

        DEEPSLATE_TILES = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles")
                .setRegistryName("deepslate_tiles")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_TILES, event);

        DEEPSLATE_POLISHED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_polished")
                .setRegistryName("deepslate_polished")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_POLISHED, event);

        DEEPSLATE_BRICKS_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks_cracked")
                .setRegistryName("deepslate_bricks_cracked")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_BRICKS_CRACKED, event);

        DEEPSLATE_TILES_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles_cracked")
                .setRegistryName("deepslate_tiles_cracked")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_TILES_CRACKED, event);

        ROOTED_DIRT = new Block(Material.GROUND)
                .setUnlocalizedName("rooted_dirt")
                .setRegistryName("rooted_dirt")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(ROOTED_DIRT, event);

        SMOOTH_BASALT = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_basalt")
                .setRegistryName("smooth_basalt")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(SMOOTH_BASALT, event);

        MOSS_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("moss_block")
                .setRegistryName("moss_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(MOSS_BLOCK, event);

        MOSS_CARPET = new BlockMossCarpet();
        register(MOSS_CARPET, event);

        DEEPSLATE_COBBLED_STAIRS = new BlockModStairs(DEEPSLATE_COBBLED.getDefaultState(), "deepslate_cobbled_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_COBBLED_STAIRS, event);
        DEEPSLATE_BRICK_STAIRS = new BlockModStairs(DEEPSLATE_BRICKS.getDefaultState(), "deepslate_brick_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_BRICK_STAIRS, event);
        DEEPSLATE_POLISHED_STAIRS = new BlockModStairs(DEEPSLATE_POLISHED.getDefaultState(), "deepslate_polished_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_POLISHED_STAIRS, event);
        DEEPSLATE_TILE_STAIRS = new BlockModStairs(DEEPSLATE_TILES.getDefaultState(), "deepslate_tile_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        register(DEEPSLATE_TILE_STAIRS, event);
        BLACKSTONE_STAIRS = new BlockModStairs(BLACKSTONE.getDefaultState(), "blackstone_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(BLACKSTONE_STAIRS, event);
        POLISHED_BLACKSTONE_BRICK_STAIRS = new BlockModStairs(POLISHED_BLACKSTONE_BRICKS.getDefaultState(), "polished_blackstone_brick_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(POLISHED_BLACKSTONE_BRICK_STAIRS, event);
        POLISHED_BLACKSTONE_STAIRS = new BlockModStairs(POLISHED_BLACKSTONE.getDefaultState(), "polished_blackstone_stairs")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        register(POLISHED_BLACKSTONE_STAIRS, event);


        ModBiomes.init();
        GameRegistry.registerTileEntity(TileEntityBeeNest.class, new ResourceLocation("ubm", "bee_nest"));
        GameRegistry.registerWorldGenerator(new WorldGenBeeNest(), 0);
        GameRegistry.registerTileEntity(TileEntityCampfire.class, new ResourceLocation("ubm", "campfire"));
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public static void registerItemServerBlocks(RegistryEvent.Register<Item> event) {
        for (Block b : blocks) {
            registerItemServer(b, event);
        }

    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (Block b : blocks) {
            registerItem(b, event);
        }
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
                    UBM.getLogger().warn(e.getMessage());
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
        Item item;
        if (block instanceof BlockDoor) {
            item = new ItemDoor(block).setRegistryName(block.getRegistryName());
        } //TODO: Add handling for signs
        else if (block instanceof BlockCopperTorch) return;
        else item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        event.getRegistry().register(item);
        registerItemModel(item);
    }


    public static void registerItemServer(Block block, RegistryEvent.Register<Item> event) {
        Item item;
        if (block instanceof BlockDoor) {
            item = new ItemDoor(block).setRegistryName(block.getRegistryName());
        } //TODO: Add handling for signs
        else if (block instanceof BlockCopperTorch) return;
        else item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        event.getRegistry().register(item);
    }
    
    public static void register(Block block, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
        blocks.add(block);
    }

    public static void registerAll(List<Block> blockss, RegistryEvent.Register<Block> event) {
        //for (Block block : blockss) register(block, event);
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