package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.api.*;
import de.julianweinelt.ubm.blocks.interactable.BlockBlastFurnace;
import de.julianweinelt.ubm.blocks.interactable.BlockSmithingTable;
import de.julianweinelt.ubm.blocks.plant.BlockGlowBerryVine;
import de.julianweinelt.ubm.blocks.plant.BlockGlowLichen;
import de.julianweinelt.ubm.blocks.plant.BlockModPlant;
import de.julianweinelt.ubm.blocks.plant.BlockSweetBerry;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBeeNest;
import de.julianweinelt.ubm.blocks.tiles.TileEntityCampfire;
import de.julianweinelt.ubm.items.BlockCopperTorch;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.worldgen.ModBiomes;
import de.julianweinelt.ubm.worldgen.WorldGenBeeNest;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection", "unused"}) // Just for cleaner git
@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModBlocks {
    public static final Map<ResourceLocation, ResourceLocation> WAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> UNWAXED_VARIANTS = new HashMap<>();
    public static final Map<ResourceLocation, ResourceLocation> PREVIOUS_OXIDATION = new HashMap<>();
    
    private static final List<Block> blocks = new ArrayList<>();
    private static final HashMap<BlockSlab, BlockSlab> slabs = new HashMap<>();
    
    private static final CreativeTabs TB_NETHER = ModCreativeTabs.UBM_TAB_NETHER;
    private static final CreativeTabs TB_CAVES = ModCreativeTabs.UBM_TAB_CAVES;
    private static final CreativeTabs TB_WILD = ModCreativeTabs.UBM_TAB_WILD;
    private static final CreativeTabs TB_AQUATIC = ModCreativeTabs.UBM_TAB_AQUATIC;
    private static final CreativeTabs TB_PILLAGE = ModCreativeTabs.UBM_TAB_PILLAGE;
    private static final CreativeTabs TB_TRAILS_TALES = ModCreativeTabs.UBM_TAB_TRAILS_TALES;

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
    public static BlockSlab CRIMSON_SLAB;
    public static BlockSlab CRIMSON_SLAB_D;
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
    public static BlockSlab WARPED_SLAB;
    public static BlockSlab WARPED_SLAB_D;
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
    public static BlockSlab BLACKSTONE_SLAB;
    public static BlockSlab BLACKSTONE_SLAB_D;
    public static Block BLACKSTONE_STAIRS;
    public static BlockSlab POLISHED_BLACKSTONE_SLAB;
    public static BlockSlab POLISHED_BLACKSTONE_SLAB_D;
    public static Block POLISHED_BLACKSTONE_STAIRS;
    public static BlockSlab POLISHED_BLACKSTONE_BRICK_SLAB;
    public static BlockSlab POLISHED_BLACKSTONE_BRICK_SLAB_D;
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
    public static Block HONEYCOMB_BLOCK;
    public static Block HONEY_BLOCK;

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
    public static BlockMod AMETHYST_BLOCK;
    public static BlockMod BUDDING_AMETHYST;
    public static Block AMETHYST_CLUSTER;
    public static Block SMALL_AMETHYST_BUD;
    public static Block MEDIUM_AMETHYST_BUD;
    public static Block LARGE_AMETHYST_BUD;
    public static BlockMod CALCITE;

    public static BlockMod TUFF;
    public static Block TUFF_STAIRS;
    public static BlockSlab TUFF_SLAB;
    public static BlockSlab TUFF_SLAB_D;
    public static Block TUFF_WALL;
    public static Block CHISELED_TUFF;
    public static BlockMod POLISHED_TUFF;
    public static Block POLISHED_TUFF_STAIRS;
    public static BlockSlab POLISHED_TUFF_SLAB;
    public static BlockSlab POLISHED_TUFF_SLAB_D;
    public static Block POLISHED_TUFF_WALL;
    public static BlockMod TUFF_BRICKS;
    public static Block TUFF_BRICK_STAIRS;
    public static BlockSlab TUFF_BRICK_SLAB;
    public static BlockSlab TUFF_BRICK_SLAB_D;
    public static Block TUFF_BRICK_WALL;
    public static Block CHISELED_TUFF_BRICKS;

    public static Block COPPER_ORE;
    public static BlockMod DEEPSLATE;
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
    public static BlockSlab DEEPSLATE_COBBLED_SLAB;
    public static BlockSlab DEEPSLATE_COBBLED_SLAB_D;
    public static Block DEEPSLATE_POLISHED_STAIRS;
    public static BlockSlab DEEPSLATE_POLISHED_SLAB;
    public static BlockSlab DEEPSLATE_POLISHED_SLAB_D;
    public static Block DEEPSLATE_POLISHED_WALL;
    public static Block DEEPSLATE_BRICK_STAIRS;
    public static BlockSlab DEEPSLATE_BRICK_SLAB;
    public static BlockSlab DEEPSLATE_BRICK_SLAB_D;
    public static Block DEEPSLATE_BRICK_WALL;
    public static Block DEEPSLATE_TILE_STAIRS;
    public static BlockSlab DEEPSLATE_TILE_SLAB;
    public static BlockSlab DEEPSLATE_TILE_SLAB_D;
    public static Block DEEPSLATE_TILE_WALL;

    public static Block ANDESITE_POLISHED_STAIRS;
    public static Block GRANITE_POLISHED_STAIRS;
    public static Block DIORITE_POLISHED_STAIRS;
    public static Block ANDESITE_STAIRS;
    public static Block GRANITE_STAIRS;
    public static Block DIORITE_STAIRS;
    public static BlockSlab POLISHED_ANDESITE_SLAB;
    public static BlockSlab POLISHED_ANDESITE_SLAB_D;
    public static BlockSlab POLISHED_GRANITE_SLAB;
    public static BlockSlab POLISHED_GRANITE_SLAB_D;
    public static BlockSlab POLISHED_DIORITE_SLAB;
    public static BlockSlab POLISHED_DIORITE_SLAB_D;
    public static BlockSlab ANDESITE_SLAB;
    public static BlockSlab ANDESITE_SLAB_D;
    public static BlockSlab GRANITE_SLAB;
    public static BlockSlab GRANITE_SLAB_D;
    public static BlockSlab DIORITE_SLAB;
    public static BlockSlab DIORITE_SLAB_D;

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
    public static BlockSlab COPPER_SLAB, COPPER_SLAB_D, EXPOSED_COPPER_SLAB, EXPOSED_COPPER_SLAB_D,
        WEATHERED_COPPER_SLAB, WEATHERED_COPPER_SLAB_D, OXIDIZED_COPPER_SLAB, OXIDIZED_COPPER_SLAB_D,
        WAXED_COPPER_SLAB, WAXED_COPPER_SLAB_D, WAXED_EXPOSED_COPPER_SLAB, WAXED_EXPOSED_COPPER_SLAB_D,
        WAXED_WEATHERED_COPPER_SLAB, WAXED_WEATHERED_COPPER_SLAB_D, WAXED_OXIDIZED_COPPER_SLAB, WAXED_OXIDIZED_COPPER_SLAB_D,
        CUT_COPPER_SLAB, CUT_COPPER_SLAB_D, EXPOSED_CUT_COPPER_SLAB, EXPOSED_CUT_COPPER_SLAB_D, WEATHERED_CUT_COPPER_SLAB, WEATHERED_CUT_COPPER_SLAB_D,
        OXIDIZED_CUT_COPPER_SLAB, OXIDIZED_CUT_COPPER_SLAB_D, WAXED_CUT_COPPER_SLAB, WAXED_CUT_COPPER_SLAB_D, WAXED_EXPOSED_CUT_COPPER_SLAB,
        WAXED_EXPOSED_CUT_COPPER_SLAB_D, WAXED_WEATHERED_CUT_COPPER_SLAB, WAXED_WEATHERED_CUT_COPPER_SLAB_D,
        WAXED_OXIDIZED_CUT_COPPER_SLAB, WAXED_OXIDIZED_CUT_COPPER_SLAB_D;
    public static Block COPPER_STAIRS, EXPOSED_COPPER_STAIRS, WEATHERED_COPPER_STAIRS, OXIDIZED_COPPER_STAIRS,
        CUT_COPPER_STAIRS, EXPOSED_CUT_COPPER_STAIRS, WEATHERED_CUT_COPPER_STAIRS, OXIDIZED_CUT_COPPER_STAIRS,
            WAXED_COPPER_STAIRS, WAXED_EXPOSED_COPPER_STAIRS, WAXED_WEATHERED_COPPER_STAIRS, WAXED_OXIDIZED_COPPER_STAIRS,
            WAXED_CUT_COPPER_STAIRS, WAXED_EXPOSED_CUT_COPPER_STAIRS, WAXED_WEATHERED_CUT_COPPER_STAIRS, WAXED_OXIDIZED_CUT_COPPER_STAIRS;
    public static Block COPPER_DOOR, EXPOSED_COPPER_DOOR, WEATHERED_COPPER_DOOR, OXIDIZED_COPPER_DOOR,
        WAXED_COPPER_DOOR, WAXED_EXPOSED_COPPER_DOOR, WAXED_WEATHERED_COPPER_DOOR, WAXED_OXIDIZED_COPPER_DOOR;
    public static Block COPPER_TRAPDOOR, EXPOSED_COPPER_TRAPDOOR, WEATHERED_COPPER_TRAPDOOR, OXIDIZED_COPPER_TRAPDOOR,
        WAXED_COPPER_TRAPDOOR, WAXED_EXPOSED_COPPER_TRAPDOOR, WAXED_WEATHERED_COPPER_TRAPDOOR, WAXED_OXIDIZED_COPPER_TRAPDOOR;

    public static Block DRIED_KELP_BLOCK;
    public static Block SEAGRASS;
    public static Block KELP;
    public static Block BLUE_ICE;
    public static Block TURTLE_EGG;
    public static Block CONDUIT;
    public static Block SEA_PICKLE;
    public static Block TUBE_CORAL_BLOCK;
    public static Block TUBE_CORAL;
    public static Block BRAIN_CORAL_BLOCK;
    public static Block BRAIN_CORAL;
    public static Block BUBBLE_CORAL_BLOCK;
    public static Block BUBBLE_CORAL;
    public static Block FIRE_CORAL_BLOCK;
    public static Block FIRE_CORAL;
    public static Block HORN_CORAL_BLOCK;
    public static Block HORN_CORAL;
    public static Block TUBE_CORAL_FAN;
    public static Block BRAIN_CORAL_FAN;
    public static Block BUBBLE_CORAL_FAN;
    public static Block FIRE_CORAL_FAN;
    public static Block HORN_CORAL_FAN;
    public static Block DEAD_TUBE_CORAL;
    public static Block DEAD_BRAIN_CORAL;
    public static Block DEAD_BUBBLE_CORAL;
    public static Block DEAD_FIRE_CORAL;
    public static Block DEAD_HORN_CORAL;
    public static Block DEAD_TUBE_CORAL_BLOCK;
    public static Block DEAD_BRAIN_CORAL_BLOCK;
    public static Block DEAD_BUBBLE_CORAL_BLOCK;
    public static Block DEAD_FIRE_CORAL_BLOCK;
    public static Block DEAD_HORN_CORAL_BLOCK;
    public static Block DEAD_TUBE_CORAL_FAN;
    public static Block DEAD_BRAIN_CORAL_FAN;
    public static Block DEAD_BUBBLE_CORAL_FAN;
    public static Block DEAD_FIRE_CORAL_FAN;
    public static Block DEAD_HORN_CORAL_FAN;
    public static Block SPRUCE_TRAPDOOR;
    public static Block BIRCH_TRAPDOOR;
    public static Block JUNGLE_TRAPDOOR;
    public static Block ACACIA_TRAPDOOR;
    public static Block DARK_OAK_TRAPDOOR;
    public static Block SPRUCE_BUTTON;
    public static Block BIRCH_BUTTON;
    public static Block JUNGLE_BUTTON;
    public static Block ACACIA_BUTTON;
    public static Block DARK_OAK_BUTTON;
    public static Block SPRUCE_PRESSURE_PLATE;
    public static Block BIRCH_PRESSURE_PLATE;
    public static Block JUNGLE_PRESSURE_PLATE;
    public static Block ACACIA_PRESSURE_PLATE;
    public static Block DARK_OAK_PRESSURE_PLATE;

    public static Block DECORATED_POT;
    public static Block AZALEA;
    public static Block FLOWERING_AZALEA;
    public static Block AZALEA_LEAVES;
    public static Block FLOWERING_AZALEA_LEAVES;
    public static Block DRIPSTONE;
    public static Block DRIPSTONE_BLOCK;
    public static Block DRIPLEAF;
    public static Block SMALL_DRIPLEAF;
    public static Block HANGING_ROOTS;

    public static Block SCULK_SENSOR;
    public static Block SCULK_CATALYST;
    public static Block SCULK;
    public static Block SCULK_SHRIEKER;
    public static Block CALIBRATED_SCULK_SENSOR;
    public static Block SCULK_VEINS;
    public static Block SPORE_BLOSSOM;

    public static Block MUD;
    public static Block PACKED_MUD;
    public static Block MUD_BRICKS;
    public static BlockSlab MUD_BRICK_SLAB;
    public static BlockSlab MUD_BRICK_SLAB_D;
    public static Block MUD_BRICK_STAIRS;
    public static Block MUD_BRICK_WALL;
    public static Block MUDDY_MANGROVE_ROOTS;

    public static Block CHISILED_BOOKSHELF;
    public static Block SNIFFER_EGG;
    public static Block SUSPICIOUS_SAND;
    public static Block SUSPICIOUS_GRAVEL;
    public static Block PIGLIN_HEAD;
    public static Block PITCHER_PLANTS;
    public static Block TORCHFLOWER;
    public static Block PINK_PETALS;

    public static Block HANGING_SIGN_OAK;
    public static Block HANGING_SIGN_SPRUCE;
    public static Block HANGING_SIGN_BIRCH;
    public static Block HANGING_SIGN_ACACIA;
    public static Block HANGING_SIGN_JUNGLE;
    public static Block HANGING_SIGN_DARK_OAK;
    public static Block HANGING_SIGN_PALE_OAK;
    public static Block HANGING_SIGN_MANGROVE;
    public static Block HANGING_SIGN_CHERRY;
    public static Block HANGING_SIGN_BAMBOO;

    public static Block MANGROVE_PLANKS;
    public static Block MANGROVE_LOG;
    public static Block MANGROVE_WOOD;
    public static Block MANGROVE_DOOR;
    public static Block MANGROVE_BUTTON;
    public static Block MANGROVE_PRESSURE_PLATE;
    public static Block MANGROVE_TRAPDOOR;
    public static Block MANGROVE_STAIRS;
    public static BlockSlab MANGROVE_SLAB;
    public static BlockSlab MANGROVE_SLAB_D;
    public static Block MANGROVE_FENCE;
    public static Block MANGROVE_FENCE_GATE;
    public static Block STRIPPED_MANGROVE_LOG;
    public static Block STRIPPED_MANGROVE_WOOD;
    public static Block MANGROVE_SIGN;
    public static Block BLOCK_OF_BAMBOO;
    public static Block STRIPPED_BAMBOO;
    public static Block BAMBOO_MOSAIC;
    public static Block BAMBOO_PLANKS;
    public static Block BAMBOO_DOOR;
    public static Block BAMBOO_TRAPDOOR;
    public static Block BAMBOO_STAIRS;
    public static BlockSlab BAMBOO_SLAB;
    public static BlockSlab BAMBOO_SLAB_D;
    public static Block BAMBOO_BUTTON;
    public static Block BAMBOO_PRESSURE_PLATE;
    public static Block BAMBOO_FENCE;
    public static Block BAMBOO_FENCE_GATE;
    public static Block BAMBOO_MOSAIC_STAIRS;
    public static Block BAMBOO_MOSAIC_SLAB;
    public static Block BAMBOO_MOSAIC_SLAB_D;
    public static Block CHERRY_PLANKS;
    public static Block CHERRY_LOG;
    public static Block CHERRY_WOOD;
    public static Block CHERRY_DOOR;
    public static Block CHERRY_BUTTON;
    public static Block CHERRY_PRESSURE_PLATE;
    public static Block CHERRY_TRAPDOOR;
    public static Block CHERRY_STAIRS;
    public static BlockSlab CHERRY_SLAB;
    public static BlockSlab CHERRY_SLAB_D;
    public static Block CHERRY_FENCE;
    public static Block CHERRY_FENCE_GATE;
    public static Block STRIPPED_CHERRY_LOG;
    public static Block STRIPPED_CHERRY_WOOD;
    public static Block CHERRY_SIGN;
    public static Block CHERRY_SAPLING;
    public static Block CHERRY_LEAVES;

    public static Block PALE_OAK_PLANKS;
    public static Block PALE_OAK_LOG;
    public static Block PALE_OAK_WOOD;
    public static Block PALE_OAK_DOOR;
    public static Block PALE_OAK_BUTTON;
    public static Block PALE_OAK_PRESSURE_PLATE;
    public static Block PALE_OAK_TRAPDOOR;
    public static Block PALE_OAK_STAIRS;
    public static BlockSlab PALE_OAK_SLAB;
    public static BlockSlab PALE_OAK_SLAB_D;
    public static Block PALE_OAK_FENCE;
    public static Block PALE_OAK_FENCE_GATE;
    public static Block STRIPPED_PALE_OAK_LOG;
    public static Block STRIPPED_PALE_OAK_WOOD;
    public static Block PALE_OAK_SIGN;
    public static Block PALE_OAK_SAPLING;
    public static Block PALE_OAK_LEAVES;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        CAMPFIRE = new BlockCampFire(false);
        register(CAMPFIRE, event);

        DEAD_TUBE_CORAL_BLOCK = new BlockCoralBlock("tube", true, null);
        register(DEAD_TUBE_CORAL_BLOCK, event);
        TUBE_CORAL_BLOCK = new BlockCoralBlock("tube", false, DEAD_TUBE_CORAL_BLOCK);
        register(TUBE_CORAL_BLOCK, event);

        DEAD_HORN_CORAL_BLOCK = new BlockCoralBlock("horn", true, null);
        register(DEAD_HORN_CORAL_BLOCK, event);
        HORN_CORAL_BLOCK = new BlockCoralBlock("horn", false, DEAD_HORN_CORAL_BLOCK);
        register(HORN_CORAL_BLOCK, event);

        DEAD_BRAIN_CORAL_BLOCK = new BlockCoralBlock("brain", true, null);
        register(DEAD_BRAIN_CORAL_BLOCK, event);
        BRAIN_CORAL_BLOCK = new BlockCoralBlock("brain", false, DEAD_BRAIN_CORAL_BLOCK);
        register(BRAIN_CORAL_BLOCK, event);

        DEAD_BUBBLE_CORAL_BLOCK = new BlockCoralBlock("bubble", true, null);
        register(DEAD_BUBBLE_CORAL_BLOCK, event);
        BUBBLE_CORAL_BLOCK = new BlockCoralBlock("bubble", false, DEAD_BUBBLE_CORAL_BLOCK);
        register(BUBBLE_CORAL_BLOCK, event);

        DEAD_FIRE_CORAL_BLOCK = new BlockCoralBlock("fire", true, null);
        register(DEAD_FIRE_CORAL_BLOCK, event);
        FIRE_CORAL_BLOCK = new BlockCoralBlock("fire", false, DEAD_FIRE_CORAL_BLOCK);
        register(FIRE_CORAL_BLOCK, event);



        LOOM = new BlockRotated(Material.WOOD)
                .setUnlocalizedName("loom")
                .setRegistryName("loom")
                .setCreativeTab(TB_PILLAGE);
        register(LOOM, event);
        SMOOTH_STONE = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_stone")
                .setRegistryName("smooth_stone")
                .setCreativeTab(TB_PILLAGE);
        register(SMOOTH_STONE, event);

        GRINDSTONE = new BlockGrindstone(Material.ROCK);
        register(GRINDSTONE, event);
        BELL = new BlockBell(Material.ANVIL);
        register(BELL, event);


        SOUL_CAMPFIRE = new BlockCampFire(true)
                .setCreativeTab(TB_NETHER);
        register(SOUL_CAMPFIRE, event);

        FLETCHING_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("fletching_table")
                .setUnlocalizedName("fletching_table")
                .setCreativeTab(TB_PILLAGE);
        register(FLETCHING_TABLE, event);
        SMOKER = new BlockRotated(Material.ROCK)
                .setRegistryName("smoker")
                .setUnlocalizedName("smoker")
                .setCreativeTab(TB_PILLAGE);
        register(SMOKER, event);
        BLAST_FURNACE = new BlockBlastFurnace();
        register(BLAST_FURNACE, event);
        CARTOGRAPHY_TABLE = new BlockRotated(Material.WOOD)
                .setRegistryName("cartography_table")
                .setUnlocalizedName("cartography_table")
                .setCreativeTab(TB_PILLAGE);
        register(CARTOGRAPHY_TABLE, event);

        CRIMSON_BUTTON = new BlockModButton(true, "crimson_button")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_BUTTON, event);


        CRIMSON_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "crimson_pressure_plate")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_PRESSURE_PLATE, event);

        WARPED_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "warped_pressure_plate")
                .setCreativeTab(TB_NETHER);
        register(WARPED_PRESSURE_PLATE, event);
        CRIMSON_FENCE_GATE = new BlockModFenceGate("crimson_fence_gate").setCreativeTab(TB_NETHER);
        register(CRIMSON_FENCE_GATE, event);
        WARPED_FENCE_GATE = new BlockModFenceGate("warped_fence_gate").setCreativeTab(TB_NETHER);
        register(WARPED_FENCE_GATE, event);
        WARPED_DOOR = new BlockModDoor(Material.WOOD, "crimson_door").setCreativeTab(TB_NETHER);
        register(WARPED_DOOR, event);
        CRIMSON_DOOR = new BlockModDoor(Material.WOOD, "warped_door").setCreativeTab(TB_NETHER);
        register(CRIMSON_DOOR, event);
        CRIMSON_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "crimson_trapdoor").setCreativeTab(TB_NETHER);
        register(CRIMSON_TRAPDOOR, event);
        WARPED_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "warped_trapdoor").setCreativeTab(TB_NETHER);
        register(WARPED_TRAPDOOR, event);
        
        CRIMSON_FUNGUS = new BlockModPlant("crimson_fungus").setCreativeTab(TB_NETHER);


        WARPED_BUTTON = new BlockModButton(true, "warped_button")
                .setCreativeTab(TB_NETHER);
        register(WARPED_BUTTON, event);

        PALE_OAK_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("pale_oak_planks")
                .setRegistryName("pale_oak_planks")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_PLANKS, event);

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
        BUDDING_AMETHYST = new BlockMod(Material.ROCK, "budding_amethyst")
                .creativeTab(TB_CAVES);
        register(BUDDING_AMETHYST, event);


        NETHERITE_BLOCK = new BlockNetheriteBlock();
        register(NETHERITE_BLOCK, event);

        TARGET = new Block(Material.PLANTS)
                .setUnlocalizedName("target")
                .setRegistryName("target")
                .setCreativeTab(TB_PILLAGE);
        register(TARGET, event);

        GLOW_LICHEN = new BlockGlowLichen();
        register(GLOW_LICHEN, event);

        ANCIENT_DEBRIS = new Block(Material.ROCK)
                .setUnlocalizedName("ancient_debris")
                .setRegistryName("ancient_debris")
                .setCreativeTab(TB_NETHER);
        register(ANCIENT_DEBRIS, event);

        CRIMSON_STEM = new BlockStrippedStem("crimson", TB_NETHER, false);
        register(CRIMSON_STEM, event);
        CRIMSON_HYPHAE = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_hyphae")
                .setRegistryName("crimson_hyphae")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_HYPHAE, event);
        WARPED_STEM = new BlockStrippedStem("warped", TB_NETHER, false);
        register(WARPED_STEM, event);
        WARPED_HYPHAE = new BlockStrippedWood("warped", TB_NETHER, false, true);
        register(WARPED_HYPHAE, event);

        STRIPPED_CRIMSON_STEM = new BlockModLog("stripped_crimson_stem").setCreativeTab(TB_NETHER);
        register(STRIPPED_CRIMSON_STEM, event);
        STRIPPED_CRIMSON_HYPHAE = new BlockModLog("stripped_crimson_hyphae").setCreativeTab(TB_NETHER);
        register(STRIPPED_CRIMSON_HYPHAE, event);
        STRIPPED_WARPED_STEM = new BlockModLog("stripped_warped_stem").setCreativeTab(TB_NETHER);
        register(STRIPPED_WARPED_STEM, event);
        STRIPPED_WARPED_HYPHAE = new BlockModLog("stripped_warped_hyphae").setCreativeTab(TB_NETHER);
        register(STRIPPED_WARPED_HYPHAE, event);
        BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("blackstone")
                .setRegistryName("blackstone")
                .setCreativeTab(TB_NETHER);
        register(BLACKSTONE, event);
        POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone")
                .setRegistryName("polished_blackstone")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE, event);

        CHISELED_POLISHED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("chiseled_polished_blackstone")
                .setRegistryName("chiseled_polished_blackstone")
                .setCreativeTab(TB_NETHER);
        register(CHISELED_POLISHED_BLACKSTONE, event);

        POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("polished_blackstone_bricks")
                .setRegistryName("polished_blackstone_bricks")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE_BRICKS, event);

        CRACKED_POLISHED_BLACKSTONE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("cracked_polished_blackstone_bricks")
                .setRegistryName("cracked_polished_blackstone_bricks")
                .setCreativeTab(TB_NETHER);
        register(CRACKED_POLISHED_BLACKSTONE_BRICKS, event);

        GILDED_BLACKSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("gilded_blackstone")
                .setRegistryName("gilded_blackstone")
                .setCreativeTab(TB_NETHER);
        register(GILDED_BLACKSTONE, event);

        NETHER_GOLD_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("nether_gold_ore")
                .setRegistryName("nether_gold_ore")
                .setCreativeTab(TB_NETHER);
        register(NETHER_GOLD_ORE, event);

        SOUL_SOIL = new Block(Material.GROUND)
                .setUnlocalizedName("soul_soil")
                .setRegistryName("soul_soil")
                .setCreativeTab(TB_NETHER);
        register(SOUL_SOIL, event);

        WARPED_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("warped_nylium")
                .setRegistryName("warped_nylium")
                .setHardness(2.0F)
                .setResistance(5.0F)
                .setCreativeTab(TB_NETHER);
        register(WARPED_NYLIUM, event);

        CRYING_OBSIDIAN = new Block(Material.ROCK)
                .setUnlocalizedName("crying_obsidian")
                .setRegistryName("crying_obsidian")
                .setCreativeTab(TB_NETHER);
        register(CRYING_OBSIDIAN, event);

        CRIMSON_NYLIUM = new Block(Material.GROUND)
                .setUnlocalizedName("crimson_nylium")
                .setRegistryName("crimson_nylium")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_NYLIUM, event);

        CRIMSON_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("crimson_planks")
                .setRegistryName("crimson_planks")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_PLANKS, event);

        WARPED_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("warped_planks")
                .setRegistryName("warped_planks")
                .setCreativeTab(TB_NETHER);
        register(WARPED_PLANKS, event);

        STRIPPED_OAK_STEM = new BlockStrippedStem("oak", TB_PILLAGE, true);
        register(STRIPPED_OAK_STEM, event);

        STRIPPED_OAK_WOOD = new BlockStrippedWood("oak", TB_PILLAGE, true);
        register(STRIPPED_OAK_WOOD, event);

        STRIPPED_SPRUCE_STEM = new BlockStrippedStem("spruce", TB_PILLAGE, true);
        register(STRIPPED_SPRUCE_STEM, event);
        STRIPPED_SPRUCE_WOOD = new BlockStrippedWood("spruce", TB_PILLAGE, true);
        register(STRIPPED_SPRUCE_WOOD, event);

        STRIPPED_BIRCH_STEM = new BlockStrippedStem("birch", TB_PILLAGE, true);
        register(STRIPPED_BIRCH_STEM, event);

        STRIPPED_BIRCH_WOOD = new BlockStrippedWood("birch", TB_PILLAGE, true);
        register(STRIPPED_BIRCH_WOOD, event);


        STRIPPED_DARK_OAK_STEM = new BlockStrippedStem("dark_oak", TB_PILLAGE, true);
        register(STRIPPED_DARK_OAK_STEM, event);

        STRIPPED_DARK_OAK_WOOD = new BlockStrippedWood("dark_oak", TB_PILLAGE, true);
        register(STRIPPED_DARK_OAK_WOOD, event);

        STRIPPED_ACACIA_STEM = new BlockStrippedStem("acacia", TB_PILLAGE, true);
        register(STRIPPED_ACACIA_STEM, event);

        STRIPPED_ACACIA_WOOD = new BlockStrippedWood("acacia", TB_PILLAGE, true);
        register(STRIPPED_ACACIA_WOOD, event);

        STRIPPED_JUNGLE_STEM = new BlockStrippedStem("jungle", TB_PILLAGE, true);
        register(STRIPPED_JUNGLE_STEM, event);

        STRIPPED_JUNGLE_WOOD = new BlockStrippedWood("jungle", TB_PILLAGE, true);
        register(STRIPPED_JUNGLE_WOOD, event);


        WARPED_STAIRS = new BlockModStairs(WARPED_PLANKS.getDefaultState(), "warped_stairs")
                .setCreativeTab(TB_NETHER);
        register(WARPED_STAIRS, event);
        CRIMSON_STAIRS = new BlockModStairs(CRIMSON_PLANKS.getDefaultState(), "crimson_stairs")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_STAIRS, event);
        WARPED_FENCE = new BlockModFence(Material.WOOD, "warped_fence")
                .setCreativeTab(TB_NETHER);
        register(WARPED_FENCE, event);
        CRIMSON_FENCE = new BlockModFence(Material.WOOD, "crimson_fence")
                .setCreativeTab(TB_NETHER);
        register(CRIMSON_FENCE, event);

        WARPED_WART_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("warped_wart_block")
                .setRegistryName("warped_wart_block")
                .setCreativeTab(TB_NETHER);
        register(WARPED_WART_BLOCK, event);

        PEARLESCENT_FROGLIGHT = new BlockModLog("pearlescent_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(TB_WILD);
        register(PEARLESCENT_FROGLIGHT, event);

        OCHRE_FROGLIGHT = new BlockModLog("ochre_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(TB_WILD);
        register(OCHRE_FROGLIGHT, event);

        VERDANT_FROGLIGHT = new BlockModLog("verdant_froglight")
                .setLightLevel(1.0F)
                .setCreativeTab(TB_WILD);
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

        SWEET_BERRY_BUSH = new BlockSweetBerry().setCreativeTab(TB_PILLAGE);
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

        COPPER_BLOCK = new BlockCopperBlock("copper_block", () -> EXPOSED_COPPER_BLOCK, null, () -> WAXED_COPPER_BLOCK);
        EXPOSED_COPPER_BLOCK = new BlockCopperBlock("exposed_copper_block",
                () -> COPPER_BLOCK, () -> COPPER_BLOCK, () -> WAXED_EXPOSED_COPPER_BLOCK);
        WEATHERED_COPPER_BLOCK = new BlockCopperBlock("weathered_copper_block", () -> EXPOSED_COPPER_BLOCK,
                () -> OXIDIZED_COPPER_BLOCK, () -> WAXED_WEATHERED_COPPER_BLOCK);
        OXIDIZED_COPPER_BLOCK = new BlockCopperBlock("oxidized_copper_block", null, () -> WEATHERED_COPPER_BLOCK,
                () -> WAXED_OXIDIZED_COPPER_BLOCK);


        CHISELED_COPPER = new BlockCopperBlock("chiseled_copper",
                () -> EXPOSED_CHISELED_COPPER, null, () -> WAXED_CHISELED_COPPER);
        EXPOSED_CHISELED_COPPER = new BlockCopperBlock("exposed_chiseled_copper", () -> WEATHERED_CHISELED_COPPER,
                () -> CHISELED_COPPER, () -> WAXED_EXPOSED_CHISELED_COPPER);
        WEATHERED_CHISELED_COPPER = new BlockCopperBlock("weathered_chiseled_copper", () -> OXIDIZED_CHISELED_COPPER,
                () -> EXPOSED_CHISELED_COPPER, () -> WAXED_WEATHERED_CHISELED_COPPER);
        OXIDIZED_CHISELED_COPPER = new BlockCopperBlock("oxidized_chiseled_copper", null,
                () -> WEATHERED_CHISELED_COPPER, () -> WAXED_OXIDIZED_CHISELED_COPPER);

        COPPER_GRATE = new BlockCopperGrate("", false);
        EXPOSED_COPPER_GRATE = new BlockCopperGrate("exposed", false);
        WEATHERED_COPPER_GRATE = new BlockCopperGrate("weathered", false);
        OXIDIZED_COPPER_GRATE = new BlockCopperGrate("oxidized", false);

        CUT_COPPER = new BlockCopperBlock("cut_copper", () -> EXPOSED_CUT_COPPER, null, () -> WAXED_CUT_COPPER);
        EXPOSED_CUT_COPPER = new BlockCopperBlock("exposed_cut_copper",
                () -> WEATHERED_CUT_COPPER, () -> CUT_COPPER, () -> WAXED_EXPOSED_CUT_COPPER);
        WEATHERED_CUT_COPPER = new BlockCopperBlock("weathered_cut_copper",
                () -> OXIDIZED_CUT_COPPER, () -> EXPOSED_CUT_COPPER, () -> WAXED_WEATHERED_CUT_COPPER);
        OXIDIZED_CUT_COPPER = new BlockCopperBlock("oxidized_cut_copper", null,
                () -> WEATHERED_CUT_COPPER, () -> WAXED_OXIDIZED_CUT_COPPER);

        COPPER_BULB = new BlockCopperBulb("", () -> EXPOSED_COPPER_BULB, null, () -> WAXED_COPPER_BULB);
        EXPOSED_COPPER_BULB = new BlockCopperBulb("exposed",
                () -> WEATHERED_COPPER_BULB, () -> COPPER_BULB, () -> WAXED_COPPER_BULB);
        WEATHERED_COPPER_BULB = new BlockCopperBulb("weathered",
                () -> OXIDIZED_COPPER_BULB, () -> EXPOSED_COPPER_BULB, () -> WAXED_WEATHERED_COPPER_BULB);
        OXIDIZED_COPPER_BULB = new BlockCopperBulb("oxidized", null,
                () -> WEATHERED_COPPER_BULB, () -> WAXED_OXIDIZED_COPPER_BULB);
        WAXED_COPPER_BULB = new BlockWaxedCopperBulb("", () -> COPPER_BULB);
        WAXED_EXPOSED_COPPER_BULB = new BlockWaxedCopperBulb("exposed", () -> EXPOSED_COPPER_BULB);
        WAXED_WEATHERED_COPPER_BULB = new BlockWaxedCopperBulb("weathered", () -> WEATHERED_COPPER_BULB);
        WAXED_OXIDIZED_COPPER_BULB = new BlockWaxedCopperBulb("oxidized", () -> OXIDIZED_COPPER_BULB);

        // WAXED
        WAXED_COPPER_BLOCK = new BlockWaxedCopperBlock("waxed_copper_block", () -> COPPER_BLOCK);
        WAXED_EXPOSED_COPPER_BLOCK = new BlockWaxedCopperBlock("waxed_exposed_copper_block", () -> EXPOSED_COPPER_BLOCK);
        WAXED_WEATHERED_COPPER_BLOCK = new BlockWaxedCopperBlock("waxed_weathered_copper_block", () -> WEATHERED_COPPER_BLOCK);
        WAXED_OXIDIZED_COPPER_BLOCK = new BlockWaxedCopperBlock("waxed_oxidized_copper_block", () -> OXIDIZED_COPPER_BLOCK);

        WAXED_CHISELED_COPPER = new BlockWaxedCopperBlock("waxed_chiseled_copper", () -> CHISELED_COPPER);
        WAXED_EXPOSED_CHISELED_COPPER = new BlockWaxedCopperBlock("waxed_exposed_chiseled_copper", () -> EXPOSED_CHISELED_COPPER);
        WAXED_WEATHERED_CHISELED_COPPER = new BlockWaxedCopperBlock("waxed_weathered_chiseled_copper", () -> WEATHERED_CHISELED_COPPER);
        WAXED_OXIDIZED_CHISELED_COPPER = new BlockWaxedCopperBlock("waxed_oxidized_chiseled_copper", () -> OXIDIZED_CHISELED_COPPER);

        WAXED_COPPER_GRATE = new BlockCopperGrate("", true);
        WAXED_EXPOSED_COPPER_GRATE = new BlockCopperGrate("exposed", true);
        WAXED_WEATHERED_COPPER_GRATE = new BlockCopperGrate("weathered", true);
        WAXED_OXIDIZED_COPPER_GRATE = new BlockCopperGrate("oxidized", true);

        WAXED_CUT_COPPER = new BlockWaxedCopperBlock("waxed_cut_copper", () -> CUT_COPPER);
        WAXED_EXPOSED_CUT_COPPER = new BlockWaxedCopperBlock("waxed_exposed_cut_copper", () -> EXPOSED_CUT_COPPER);
        WAXED_WEATHERED_CUT_COPPER = new BlockWaxedCopperBlock("waxed_weathered_cut_copper", () -> WEATHERED_CUT_COPPER);
        WAXED_OXIDIZED_CUT_COPPER = new BlockWaxedCopperBlock("waxed_oxidized_cut_copper", () -> OXIDIZED_CUT_COPPER);


        register(COPPER_BLOCK, event);
        register(EXPOSED_COPPER_BLOCK, event);
        register(WEATHERED_COPPER_BLOCK, event);
        register(OXIDIZED_COPPER_BLOCK, event);
        register(CHISELED_COPPER, event);
        register(COPPER_GRATE, event);
        register(CUT_COPPER, event);
        register(COPPER_BULB, event);
        register(EXPOSED_COPPER_BULB, event);
        register(WEATHERED_COPPER_BULB, event);
        register(OXIDIZED_COPPER_BULB, event);
        register(WAXED_COPPER_BULB, event);
        register(WAXED_EXPOSED_COPPER_BULB, event);
        register(WAXED_WEATHERED_COPPER_BULB, event);
        register(WAXED_OXIDIZED_COPPER_BULB, event);
        register(EXPOSED_CHISELED_COPPER, event);
        register(EXPOSED_COPPER_GRATE, event);
        register(EXPOSED_CUT_COPPER, event);
        register(WEATHERED_CHISELED_COPPER, event);
        register(WEATHERED_COPPER_GRATE, event);
        register(WEATHERED_CUT_COPPER, event);
        register(OXIDIZED_CHISELED_COPPER, event);
        register(OXIDIZED_COPPER_GRATE, event);
        register(OXIDIZED_CUT_COPPER, event);

        register(WAXED_COPPER_BLOCK, event);
        register(WAXED_COPPER_GRATE, event);
        register(WAXED_CHISELED_COPPER, event);
        register(WAXED_CUT_COPPER, event);
        register(WAXED_EXPOSED_COPPER_BLOCK, event);
        register(WAXED_EXPOSED_CHISELED_COPPER, event);
        register(WAXED_EXPOSED_COPPER_GRATE, event);
        register(WAXED_EXPOSED_CUT_COPPER, event);
        register(WAXED_WEATHERED_COPPER_BLOCK, event);
        register(WAXED_WEATHERED_CHISELED_COPPER, event);
        register(WAXED_WEATHERED_COPPER_GRATE, event);
        register(WAXED_WEATHERED_CUT_COPPER, event);
        register(WAXED_OXIDIZED_COPPER_BLOCK, event);
        register(WAXED_OXIDIZED_CHISELED_COPPER, event);
        register(WAXED_OXIDIZED_COPPER_GRATE, event);
        register(WAXED_OXIDIZED_CUT_COPPER, event);

        TINTED_GLASS = new BlockTintedGlass();
        register(TINTED_GLASS, event);

        AMETHYST_BLOCK = new BlockMod(Material.ROCK, "amethyst").creativeTab(TB_CAVES).soundType(SoundType.STONE);
        register(AMETHYST_BLOCK, event);

        CALCITE = new BlockMod(Material.ROCK, "calcite").creativeTab(TB_CAVES).soundType(SoundType.STONE);
        register(CALCITE, event);


        TUFF = new BlockMod(Material.ROCK, "tuff").creativeTab(TB_CAVES).soundType(SoundType.STONE);
        register(TUFF, event);
        TUFF_BRICKS = new BlockMod(Material.ROCK, "tuff_bricks").creativeTab(TB_TRAILS_TALES).soundType(SoundType.STONE);
        register(TUFF_BRICKS, event);
        POLISHED_TUFF = new BlockMod(Material.ROCK, "polished_tuff").creativeTab(TB_TRAILS_TALES).soundType(SoundType.STONE);
        register(POLISHED_TUFF, event);
        CHISELED_TUFF = new BlockModColumn("chiseled_tuff").setCreativeTab(TB_TRAILS_TALES);
        register(CHISELED_TUFF, event);
        CHISELED_TUFF_BRICKS = new BlockModColumn("chiseled_tuff_bricks").setCreativeTab(TB_TRAILS_TALES);
        register(CHISELED_TUFF_BRICKS, event);

        TUFF_STAIRS = new BlockModStairs(TUFF.getDefaultState(), "tuff_stairs")
                .setCreativeTab(TB_TRAILS_TALES);
        register(TUFF_STAIRS, event);

        TUFF_WALL = new BlockModWall(TUFF, "tuff_wall")
                .setCreativeTab(TB_TRAILS_TALES);
        register(TUFF_WALL, event);

        TUFF_BRICK_STAIRS = new BlockModStairs(TUFF_BRICKS.getDefaultState(), "tuff_brick_stairs")
                .setCreativeTab(TB_TRAILS_TALES);
        register(TUFF_BRICK_STAIRS, event);
        TUFF_BRICK_WALL = new BlockModWall(TUFF_BRICKS, "tuff_brick_wall")
                .setCreativeTab(TB_TRAILS_TALES);
        register(TUFF_BRICK_WALL, event);
        POLISHED_TUFF_WALL = new BlockModWall(POLISHED_TUFF, "polished_tuff_wall")
                .setCreativeTab(TB_TRAILS_TALES);
        register(POLISHED_TUFF_WALL, event);

        POLISHED_TUFF_STAIRS = new BlockModStairs(POLISHED_TUFF.getDefaultState(), "polished_tuff_stairs")
                .setCreativeTab(TB_TRAILS_TALES);
        register(POLISHED_TUFF_STAIRS, event);

        COPPER_ORE = new Block(Material.ROCK)
                .setUnlocalizedName("copper_ore")
                .setRegistryName("copper_ore")
                .setCreativeTab(TB_CAVES);
        register(COPPER_ORE, event);

        DEEPSLATE = new BlockMod(Material.ROCK, "deepslate").creativeTab(TB_CAVES).soundType(SoundType.STONE);
        register(DEEPSLATE, event);

        //TODO: Add chiseled deepslate

        DEEPSLATE_ORE_COPPER = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_copper")
                .setRegistryName("deepslate_ore_copper")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_COPPER, event);

        DEEPSLATE_ORE_GOLD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_gold")
                .setRegistryName("deepslate_ore_gold")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_GOLD, event);

        DEEPSLATE_ORE_IRON = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_iron")
                .setRegistryName("deepslate_ore_iron")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_IRON, event);

        DEEPSLATE_ORE_REDSTONE = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_redstone")
                .setRegistryName("deepslate_ore_redstone")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_REDSTONE, event);

        DEEPSLATE_ORE_LAPISLAZULI = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_lapislazuli")
                .setRegistryName("deepslate_ore_lapislazuli")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_LAPISLAZULI, event);

        DEEPSLATE_ORE_EMERALD = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_emerald")
                .setRegistryName("deepslate_ore_emerald")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_EMERALD, event);

        DEEPSLATE_ORE_DIAMOND = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_ore_diamond")
                .setRegistryName("deepslate_ore_diamond")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_ORE_DIAMOND, event);

        DEEPSLATE_COBBLED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_cobbled")
                .setRegistryName("deepslate_cobbled")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_COBBLED, event);

        DEEPSLATE_BRICKS = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks")
                .setRegistryName("deepslate_bricks")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_BRICKS, event);

        DEEPSLATE_TILES = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles")
                .setRegistryName("deepslate_tiles")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_TILES, event);

        DEEPSLATE_POLISHED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_polished")
                .setRegistryName("deepslate_polished")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_POLISHED, event);

        DEEPSLATE_BRICKS_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_bricks_cracked")
                .setRegistryName("deepslate_bricks_cracked")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_BRICKS_CRACKED, event);

        DEEPSLATE_TILES_CRACKED = new Block(Material.ROCK)
                .setUnlocalizedName("deepslate_tiles_cracked")
                .setRegistryName("deepslate_tiles_cracked")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_TILES_CRACKED, event);

        ROOTED_DIRT = new Block(Material.GROUND)
                .setUnlocalizedName("rooted_dirt")
                .setRegistryName("rooted_dirt")
                .setCreativeTab(TB_CAVES);
        register(ROOTED_DIRT, event);

        SMOOTH_BASALT = new Block(Material.ROCK)
                .setUnlocalizedName("smooth_basalt")
                .setRegistryName("smooth_basalt")
                .setCreativeTab(TB_CAVES);
        register(SMOOTH_BASALT, event);

        HONEYCOMB_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("honeycomb_block")
                .setRegistryName("honeycomb_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        register(HONEYCOMB_BLOCK, event);
        HONEY_BLOCK = new BlockHoneyBlock();
        register(HONEY_BLOCK, event);

        MOSS_BLOCK = new Block(Material.PLANTS)
                .setUnlocalizedName("moss_block")
                .setRegistryName("moss_block")
                .setCreativeTab(TB_CAVES);
        register(MOSS_BLOCK, event);

        MOSS_CARPET = new BlockMossCarpet();
        register(MOSS_CARPET, event);

        COPPER_STAIRS = new BlockModStairs(COPPER_BLOCK.getDefaultState(), "copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(COPPER_STAIRS, event);

        EXPOSED_COPPER_STAIRS = new BlockModStairs(EXPOSED_COPPER_BLOCK.getDefaultState(), "exposed_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(EXPOSED_COPPER_STAIRS, event);

        WEATHERED_COPPER_STAIRS = new BlockModStairs(WEATHERED_COPPER_BLOCK.getDefaultState(), "weathered_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WEATHERED_COPPER_STAIRS, event);

        OXIDIZED_COPPER_STAIRS = new BlockModStairs(OXIDIZED_COPPER_BLOCK.getDefaultState(), "oxidized_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(OXIDIZED_COPPER_STAIRS, event);

        CUT_COPPER_STAIRS = new BlockModStairs(CUT_COPPER.getDefaultState(), "cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(CUT_COPPER_STAIRS, event);

        EXPOSED_CUT_COPPER_STAIRS = new BlockModStairs(EXPOSED_CUT_COPPER.getDefaultState(), "exposed_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(EXPOSED_CUT_COPPER_STAIRS, event);

        WEATHERED_CUT_COPPER_STAIRS = new BlockModStairs(WEATHERED_CUT_COPPER.getDefaultState(), "weathered_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WEATHERED_CUT_COPPER_STAIRS, event);

        OXIDIZED_CUT_COPPER_STAIRS = new BlockModStairs(OXIDIZED_CUT_COPPER.getDefaultState(), "oxidized_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(OXIDIZED_CUT_COPPER_STAIRS, event);

        WAXED_COPPER_STAIRS = new BlockModStairs(WAXED_COPPER_BLOCK.getDefaultState(), "waxed_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_COPPER_STAIRS, event);

        WAXED_EXPOSED_COPPER_STAIRS = new BlockModStairs(WAXED_EXPOSED_COPPER_BLOCK.getDefaultState(), "waxed_exposed_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_EXPOSED_COPPER_STAIRS, event);

        WAXED_WEATHERED_COPPER_STAIRS = new BlockModStairs(WAXED_WEATHERED_COPPER_BLOCK.getDefaultState(), "waxed_weathered_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_WEATHERED_COPPER_STAIRS, event);

        WAXED_OXIDIZED_COPPER_STAIRS = new BlockModStairs(WAXED_OXIDIZED_COPPER_BLOCK.getDefaultState(), "waxed_oxidized_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_OXIDIZED_COPPER_STAIRS, event);

        WAXED_CUT_COPPER_STAIRS = new BlockModStairs(WAXED_CUT_COPPER.getDefaultState(), "waxed_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_CUT_COPPER_STAIRS, event);

        WAXED_EXPOSED_CUT_COPPER_STAIRS = new BlockModStairs(WAXED_EXPOSED_CUT_COPPER.getDefaultState(), "waxed_exposed_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_EXPOSED_CUT_COPPER_STAIRS, event);

        WAXED_WEATHERED_CUT_COPPER_STAIRS = new BlockModStairs(WAXED_WEATHERED_CUT_COPPER.getDefaultState(), "waxed_weathered_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_WEATHERED_CUT_COPPER_STAIRS, event);

        WAXED_OXIDIZED_CUT_COPPER_STAIRS = new BlockModStairs(WAXED_OXIDIZED_CUT_COPPER.getDefaultState(), "waxed_oxidized_cut_copper_stairs")
                .setCreativeTab(TB_CAVES);
        register(WAXED_OXIDIZED_CUT_COPPER_STAIRS, event);


        COPPER_DOOR = new BlockModDoor(Material.ROCK, "copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(COPPER_DOOR, event);

        EXPOSED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "exposed_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(EXPOSED_COPPER_DOOR, event);

        WEATHERED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "weathered_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(WEATHERED_COPPER_DOOR, event);

        OXIDIZED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "oxidized_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(OXIDIZED_COPPER_DOOR, event);

        WAXED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "waxed_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_COPPER_DOOR, event);

        WAXED_EXPOSED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "waxed_exposed_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_EXPOSED_COPPER_DOOR, event);

        WAXED_WEATHERED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "waxed_weathered_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_WEATHERED_COPPER_DOOR, event);

        WAXED_OXIDIZED_COPPER_DOOR = new BlockModDoor(Material.ROCK, "waxed_oxidized_copper_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_OXIDIZED_COPPER_DOOR, event);


        COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(COPPER_TRAPDOOR, event);

        EXPOSED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "exposed_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(EXPOSED_COPPER_TRAPDOOR, event);

        WEATHERED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "weathered_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(WEATHERED_COPPER_TRAPDOOR, event);

        OXIDIZED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "oxidized_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(OXIDIZED_COPPER_TRAPDOOR, event);

        WAXED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "waxed_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_COPPER_TRAPDOOR, event);

        WAXED_EXPOSED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "waxed_exposed_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_EXPOSED_COPPER_TRAPDOOR, event);

        WAXED_WEATHERED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "waxed_weathered_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_WEATHERED_COPPER_TRAPDOOR, event);

        WAXED_OXIDIZED_COPPER_TRAPDOOR = new BlockModTrapdoor(Material.ROCK, "waxed_oxidized_copper_trapdoor").setCreativeTab(TB_TRAILS_TALES);
        register(WAXED_OXIDIZED_COPPER_TRAPDOOR, event);
        
        
        DEEPSLATE_COBBLED_STAIRS = new BlockModStairs(DEEPSLATE_COBBLED.getDefaultState(), "deepslate_cobbled_stairs")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_COBBLED_STAIRS, event);
        DEEPSLATE_BRICK_STAIRS = new BlockModStairs(DEEPSLATE_BRICKS.getDefaultState(), "deepslate_brick_stairs")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_BRICK_STAIRS, event);
        DEEPSLATE_POLISHED_STAIRS = new BlockModStairs(DEEPSLATE_POLISHED.getDefaultState(), "deepslate_polished_stairs")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_POLISHED_STAIRS, event);
        DEEPSLATE_TILE_STAIRS = new BlockModStairs(DEEPSLATE_TILES.getDefaultState(), "deepslate_tile_stairs")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_TILE_STAIRS, event);
        BLACKSTONE_STAIRS = new BlockModStairs(BLACKSTONE.getDefaultState(), "blackstone_stairs")
                .setCreativeTab(TB_NETHER);
        register(BLACKSTONE_STAIRS, event);
        POLISHED_BLACKSTONE_BRICK_STAIRS = new BlockModStairs(POLISHED_BLACKSTONE_BRICKS.getDefaultState(), "polished_blackstone_brick_stairs")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE_BRICK_STAIRS, event);
        POLISHED_BLACKSTONE_STAIRS = new BlockModStairs(POLISHED_BLACKSTONE.getDefaultState(), "polished_blackstone_stairs")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE_STAIRS, event);

        DRIED_KELP_BLOCK = new Block(Material.WOOD)
                .setRegistryName("dried_kelp_block")
                .setUnlocalizedName("dried_kelp_block")
                .setCreativeTab(TB_AQUATIC);
        register(DRIED_KELP_BLOCK, event);

        MUD = new Block(Material.GROUND)
                .setRegistryName("mud")
                .setUnlocalizedName("mud")
                .setCreativeTab(TB_WILD);
        register(MUD, event);
        MUD_BRICKS = new Block(Material.GROUND)
                .setRegistryName("mud_bricks")
                .setUnlocalizedName("mud_bricks")
                .setCreativeTab(TB_WILD);
        register(MUD_BRICKS, event);
        PACKED_MUD = new Block(Material.GROUND)
                .setRegistryName("packed_mud")
                .setUnlocalizedName("packed_mud")
                .setCreativeTab(TB_WILD);
        register(PACKED_MUD, event);
        MUD_BRICK_STAIRS = new BlockModStairs(MUD_BRICKS.getDefaultState(), "mud_brick_stairs")
                .setCreativeTab(TB_WILD);
        register(MUD_BRICK_STAIRS, event);
        MUD_BRICK_WALL = new BlockModWall(MUD_BRICKS, "mud_brick_wall")
                .setCreativeTab(TB_WILD);
        register(MUD_BRICK_WALL, event);

        ANDESITE_STAIRS = new BlockModStairs(Blocks.STONE.getDefaultState(), "andesite_stairs")
                .setCreativeTab(TB_AQUATIC);
        register(ANDESITE_STAIRS, event);
        DIORITE_STAIRS = new BlockModStairs(Blocks.STONE.getDefaultState(), "diorite_stairs")
                .setCreativeTab(TB_AQUATIC);
        register(DIORITE_STAIRS, event);
        GRANITE_STAIRS = new BlockModStairs(Blocks.STONE.getDefaultState(), "granite_stairs")
                .setCreativeTab(TB_AQUATIC);
        register(GRANITE_STAIRS, event);

        MANGROVE_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("mangrove_planks")
                .setRegistryName("mangrove_planks")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_PLANKS, event);
        MANGROVE_LOG = new BlockModLog("mangrove_log")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_LOG, event);
        MANGROVE_WOOD = new BlockModLog("mangrove_wood")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_WOOD, event);
        MANGROVE_FENCE = new BlockModFence(Material.WOOD, "mangrove_fence")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_FENCE, event);
        MANGROVE_STAIRS = new BlockModStairs(MANGROVE_PLANKS.getDefaultState(), "mangrove_stairs")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_STAIRS, event);
        MANGROVE_BUTTON = new BlockModButton(true, "mangrove_button")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_BUTTON, event);
        MANGROVE_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "mangrove_pressure_plate")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_PRESSURE_PLATE, event);
        MANGROVE_DOOR = new BlockModDoor(Material.WOOD, "mangrove_door")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_DOOR, event);
        MANGROVE_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "mangrove_trapdoor")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_TRAPDOOR, event);
        MANGROVE_FENCE_GATE = new BlockModFenceGate("mangrove_fence_gate")
                .setCreativeTab(TB_WILD);
        register(MANGROVE_FENCE_GATE, event);

        // Bamboo


        BAMBOO_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("bamboo_planks")
                .setRegistryName("bamboo_planks")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_PLANKS, event);
        BAMBOO_FENCE = new BlockModFence(Material.WOOD, "bamboo_fence")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_FENCE, event);
        BAMBOO_STAIRS = new BlockModStairs(BAMBOO_PLANKS.getDefaultState(), "bamboo_stairs")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_STAIRS, event);
        BAMBOO_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "bamboo_pressure_plate")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_PRESSURE_PLATE, event);
        BAMBOO_DOOR = new BlockModDoor(Material.WOOD, "bamboo_door")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_DOOR, event);
        BAMBOO_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "bamboo_trapdoor")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_TRAPDOOR, event);
        BAMBOO_FENCE_GATE = new BlockModFenceGate("bamboo_fence_gate")
                .setCreativeTab(TB_WILD);
        register(BAMBOO_FENCE_GATE, event);

        // Cherry
        CHERRY_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("cherry_planks")
                .setRegistryName("cherry_planks")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_PLANKS, event);
        CHERRY_LOG = new BlockModLog("cherry_log")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_LOG, event);
        CHERRY_WOOD = new BlockModLog("cherry_wood")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_WOOD, event);
        CHERRY_STAIRS = new BlockModStairs(CHERRY_PLANKS.getDefaultState(), "cherry_stairs")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_STAIRS, event);
        CHERRY_FENCE = new BlockModFence(Material.WOOD, "cherry_fence")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_FENCE, event);
        CHERRY_FENCE_GATE = new BlockModFenceGate("cherry_fence_gate")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_FENCE_GATE, event);
        CHERRY_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "cherry_pressure_plate")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_PRESSURE_PLATE, event);
        CHERRY_DOOR = new BlockModDoor(Material.WOOD, "cherry_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_DOOR, event);
        CHERRY_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "cherry_trapdoor")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_TRAPDOOR, event);
        CHERRY_LEAVES = new Block(Material.LEAVES)
                .setRegistryName("cherry_leaves")
                .setUnlocalizedName("cherry_leaves")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_LEAVES, event);
        CHERRY_SAPLING = new BlockModPlant("cherry_sapling")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_SAPLING, event);

        PALE_OAK_SAPLING = new BlockModPlant("pale_oak_sapling")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_SAPLING, event);

        // Buttons

        CHERRY_BUTTON = new BlockModButton(true, "cherry_button")
                .setCreativeTab(TB_TRAILS_TALES);
        register(CHERRY_BUTTON, event);

        PALE_OAK_BUTTON = new BlockModButton(true, "pale_oak_button")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_BUTTON, event);
        ACACIA_BUTTON = new BlockModButton(true, "acacia_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(ACACIA_BUTTON, event);
        JUNGLE_BUTTON = new BlockModButton(true, "jungle_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(JUNGLE_BUTTON, event);
        DARK_OAK_BUTTON = new BlockModButton(true, "dark_oak_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(DARK_OAK_BUTTON, event);
        BIRCH_BUTTON = new BlockModButton(true, "birch_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(BIRCH_BUTTON, event);
        SPRUCE_BUTTON = new BlockModButton(true, "spruce_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(SPRUCE_BUTTON, event);
        BAMBOO_BUTTON = new BlockModButton(true, "bamboo_button")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(BAMBOO_BUTTON, event);



        SPRUCE_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "spruce_pressure_plate")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(SPRUCE_PRESSURE_PLATE, event);
        BIRCH_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "birch_pressure_plate")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(BIRCH_PRESSURE_PLATE, event);
        JUNGLE_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "jungle_pressure_plate")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(JUNGLE_PRESSURE_PLATE, event);
        ACACIA_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "acacia_pressure_plate")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(ACACIA_PRESSURE_PLATE, event);
        DARK_OAK_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "dark_oak_pressure_plate")
                .setCreativeTab(CreativeTabs.REDSTONE);
        register(DARK_OAK_PRESSURE_PLATE, event);



        PALE_OAK_FENCE = new BlockModFence(Material.WOOD, "pale_oak_fence")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_FENCE, event);
        PALE_OAK_FENCE_GATE = new BlockModFenceGate("pale_oak_fence_gate")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_FENCE_GATE, event);
        PALE_OAK_DOOR = new BlockModDoor(Material.WOOD, "pale_oak_door")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_DOOR, event);
        PALE_OAK_LOG = new BlockModLog("pale_oak_log")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_LOG, event);
        PALE_OAK_WOOD = new BlockModLog("pale_oak_wood")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_WOOD, event);
        PALE_OAK_PRESSURE_PLATE = new BlockModPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Material.WOOD, "pale_oak_pressure_plate")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_PRESSURE_PLATE, event);
        PALE_OAK_STAIRS = new BlockModStairs(PALE_OAK_PLANKS.getDefaultState(), "pale_oak_stairs")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_STAIRS, event);
        PALE_OAK_TRAPDOOR = new BlockModTrapdoor(Material.WOOD, "pale_oak_trapdoor")
                .setCreativeTab(TB_TRAILS_TALES);
        register(PALE_OAK_TRAPDOOR, event);

        SCULK = new Block(Material.ROCK)
                .setCreativeTab(TB_CAVES)
                .setRegistryName("sculk")
                .setUnlocalizedName("sculk");
        register(SCULK, event);
        SCULK_CATALYST = new Block(Material.ROCK)
                .setCreativeTab(TB_CAVES)
                .setRegistryName("sculk_catalyst")
                .setUnlocalizedName("sculk_catalyst");
        register(SCULK_CATALYST, event);
        SCULK_SENSOR = new BlockSculkSensor();
        register(SCULK_SENSOR, event);
        SCULK_SHRIEKER = new BlockMod(Material.ROCK, "sculk_shrieker").creativeTab(TB_CAVES).transparent();
        register(SCULK_SHRIEKER, event);



        BLACKSTONE_WALL = new BlockModWall(BLACKSTONE, "blackstone_wall")
                .setCreativeTab(TB_NETHER);
        register(BLACKSTONE_WALL, event);

        POLISHED_BLACKSTONE_WALL = new BlockModWall(POLISHED_BLACKSTONE, "blackstone_polished_wall")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE_WALL, event);

        POLISHED_BLACKSTONE_BRICK_WALL = new BlockModWall(POLISHED_BLACKSTONE_BRICKS, "blackstone_polished_brick_wall")
                .setCreativeTab(TB_NETHER);
        register(POLISHED_BLACKSTONE_BRICK_WALL, event);


        DEEPSLATE_POLISHED_WALL = new BlockModWall(DEEPSLATE_POLISHED, "deepslate_polished_wall")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_POLISHED_WALL, event);
        DEEPSLATE_COBBLED_WALL = new BlockModWall(DEEPSLATE_COBBLED, "deepslate_cobbled_wall")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_COBBLED_WALL, event);
        DEEPSLATE_TILE_WALL = new BlockModWall(DEEPSLATE_TILES, "deepslate_tiles_wall")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_TILE_WALL, event);
        DEEPSLATE_BRICK_WALL = new BlockModWall(DEEPSLATE_BRICKS, "deepslate_brick_wall")
                .setCreativeTab(TB_CAVES);
        register(DEEPSLATE_BRICK_WALL, event);

        registerSlabs(event);


        ModBiomes.init();
        GameRegistry.registerTileEntity(TileEntityBeeNest.class, new ResourceLocation("ubm", "bee_nest"));
        GameRegistry.registerWorldGenerator(new WorldGenBeeNest(), 0);
        GameRegistry.registerTileEntity(TileEntityCampfire.class, new ResourceLocation("ubm", "campfire"));
    }

    private static void registerSlabs(RegistryEvent.Register<Block> event) {
        MANGROVE_SLAB = (BlockSlab) new BlockModHalfSlab("mangrove_slab", Material.WOOD)
                .setCreativeTab(TB_WILD);
        MANGROVE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("mangrove_slab", Material.WOOD, MANGROVE_SLAB)
                .setCreativeTab(TB_WILD);
        registerSlab(MANGROVE_SLAB, MANGROVE_SLAB_D, event);

        CHERRY_SLAB = (BlockSlab) new BlockModHalfSlab("cherry_slab", Material.WOOD)
                .setCreativeTab(TB_TRAILS_TALES);
        CHERRY_SLAB_D = (BlockSlab) new BlockModDoubleSlab("cherry_slab", Material.WOOD, CHERRY_SLAB)
                .setCreativeTab(TB_TRAILS_TALES);
        registerSlab(CHERRY_SLAB, CHERRY_SLAB_D, event);

        DEEPSLATE_COBBLED_SLAB = (BlockSlab) new BlockModHalfSlab("deepslate_cobbled_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        DEEPSLATE_COBBLED_SLAB_D = (BlockSlab) new BlockModDoubleSlab("deepslate_cobbled_slab", Material.ROCK, DEEPSLATE_COBBLED_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(DEEPSLATE_COBBLED_SLAB, DEEPSLATE_COBBLED_SLAB_D, event);

        BLACKSTONE_SLAB = (BlockSlab) new BlockModHalfSlab("blackstone_slab", Material.ROCK)
                .setCreativeTab(TB_NETHER);
        BLACKSTONE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("blackstone_slab", Material.ROCK, BLACKSTONE_SLAB)
                .setCreativeTab(TB_NETHER);
        registerSlab(BLACKSTONE_SLAB, BLACKSTONE_SLAB_D, event);

        CRIMSON_SLAB = (BlockSlab) new BlockModHalfSlab("crimson_slab", Material.WOOD)
                .setCreativeTab(TB_NETHER);
        CRIMSON_SLAB_D = (BlockSlab) new BlockModDoubleSlab("crimson_slab", Material.WOOD, CRIMSON_SLAB)
                .setCreativeTab(TB_NETHER);
        registerSlab(CRIMSON_SLAB, CRIMSON_SLAB_D, event);

        WARPED_SLAB = (BlockSlab) new BlockModHalfSlab("warped_slab", Material.WOOD)
                .setCreativeTab(TB_NETHER);
        WARPED_SLAB_D = (BlockSlab) new BlockModDoubleSlab("warped_slab", Material.WOOD, WARPED_SLAB)
                .setCreativeTab(TB_NETHER);
        registerSlab(WARPED_SLAB, WARPED_SLAB_D, event);

        PALE_OAK_SLAB = (BlockSlab) new BlockModHalfSlab("pale_oak_slab", Material.WOOD)
                .setCreativeTab(TB_TRAILS_TALES);
        PALE_OAK_SLAB_D = (BlockSlab) new BlockModDoubleSlab("pale_oak_slab", Material.WOOD, PALE_OAK_SLAB)
                .setCreativeTab(TB_TRAILS_TALES);
        registerSlab(PALE_OAK_SLAB, PALE_OAK_SLAB_D, event);

        DEEPSLATE_TILE_SLAB = (BlockSlab) new BlockModHalfSlab("deepslate_tile_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        DEEPSLATE_TILE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("deepslate_tile_slab", Material.ROCK, DEEPSLATE_TILE_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(DEEPSLATE_TILE_SLAB, DEEPSLATE_TILE_SLAB_D, event);

        DEEPSLATE_BRICK_SLAB = (BlockSlab) new BlockModHalfSlab("deepslate_brick_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        DEEPSLATE_BRICK_SLAB_D = (BlockSlab) new BlockModDoubleSlab("deepslate_brick_slab", Material.ROCK, DEEPSLATE_BRICK_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(DEEPSLATE_BRICK_SLAB, DEEPSLATE_BRICK_SLAB_D, event);

        DEEPSLATE_POLISHED_SLAB = (BlockSlab) new BlockModHalfSlab("deepslate_polished_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        DEEPSLATE_POLISHED_SLAB_D = (BlockSlab) new BlockModDoubleSlab("deepslate_polished_slab", Material.ROCK, DEEPSLATE_POLISHED_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(DEEPSLATE_POLISHED_SLAB, DEEPSLATE_POLISHED_SLAB_D, event);


        // Copper
        COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("copper_slab", Material.ROCK, COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(COPPER_SLAB, COPPER_SLAB_D, event);

        EXPOSED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("exposed_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        EXPOSED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("exposed_copper_slab", Material.ROCK, EXPOSED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(EXPOSED_COPPER_SLAB, EXPOSED_COPPER_SLAB_D, event);

        WEATHERED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("weathered_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WEATHERED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("weathered_copper_slab", Material.ROCK, WEATHERED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WEATHERED_COPPER_SLAB, WEATHERED_COPPER_SLAB_D, event);

        OXIDIZED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("oxidized_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        OXIDIZED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("oxidized_copper_slab", Material.ROCK, OXIDIZED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(OXIDIZED_COPPER_SLAB, OXIDIZED_COPPER_SLAB_D, event);


        CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("cut_copper_slab", Material.ROCK, CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(CUT_COPPER_SLAB, CUT_COPPER_SLAB_D, event);

        EXPOSED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("exposed_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        EXPOSED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("exposed_cut_copper_slab", Material.ROCK, EXPOSED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(EXPOSED_CUT_COPPER_SLAB, EXPOSED_CUT_COPPER_SLAB_D, event);

        WEATHERED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("weathered_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WEATHERED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("weathered_cut_copper_slab", Material.ROCK, WEATHERED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WEATHERED_CUT_COPPER_SLAB, WEATHERED_CUT_COPPER_SLAB_D, event);

        OXIDIZED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("oxidized_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        OXIDIZED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("oxidized_cut_copper_slab", Material.ROCK, OXIDIZED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(OXIDIZED_CUT_COPPER_SLAB, OXIDIZED_CUT_COPPER_SLAB_D, event);


        WAXED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_copper_slab", Material.ROCK, WAXED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_COPPER_SLAB, WAXED_COPPER_SLAB_D, event);

        WAXED_EXPOSED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_exposed_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_EXPOSED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_exposed_copper_slab", Material.ROCK, WAXED_EXPOSED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_EXPOSED_COPPER_SLAB, WAXED_EXPOSED_COPPER_SLAB_D, event);

        WAXED_WEATHERED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_weathered_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_WEATHERED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_weathered_copper_slab", Material.ROCK, WAXED_WEATHERED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_WEATHERED_COPPER_SLAB, WAXED_WEATHERED_COPPER_SLAB_D, event);

        WAXED_OXIDIZED_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_oxidized_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_OXIDIZED_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_oxidized_copper_slab", Material.ROCK, WAXED_OXIDIZED_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_OXIDIZED_COPPER_SLAB, WAXED_OXIDIZED_COPPER_SLAB_D, event);


        WAXED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_cut_copper_slab", Material.ROCK, WAXED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_CUT_COPPER_SLAB, WAXED_CUT_COPPER_SLAB_D, event);

        WAXED_EXPOSED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_exposed_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_EXPOSED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_exposed_cut_copper_slab", Material.ROCK, WAXED_EXPOSED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_EXPOSED_CUT_COPPER_SLAB, WAXED_EXPOSED_CUT_COPPER_SLAB_D, event);

        WAXED_WEATHERED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_weathered_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_WEATHERED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_weathered_cut_copper_slab", Material.ROCK, WAXED_WEATHERED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_WEATHERED_CUT_COPPER_SLAB, WAXED_WEATHERED_CUT_COPPER_SLAB_D, event);

        WAXED_OXIDIZED_CUT_COPPER_SLAB = (BlockSlab) new BlockModHalfSlab("waxed_oxidized_cut_copper_slab", Material.ROCK)
                .setCreativeTab(TB_CAVES);
        WAXED_OXIDIZED_CUT_COPPER_SLAB_D = (BlockSlab) new BlockModDoubleSlab("waxed_oxidized_cut_copper_slab", Material.ROCK, WAXED_OXIDIZED_CUT_COPPER_SLAB)
                .setCreativeTab(TB_CAVES);
        registerSlab(WAXED_OXIDIZED_CUT_COPPER_SLAB, WAXED_OXIDIZED_CUT_COPPER_SLAB_D, event);


        POLISHED_BLACKSTONE_SLAB = (BlockSlab) new BlockModHalfSlab("polished_blackstone_slab", Material.ROCK)
                .setCreativeTab(TB_NETHER);
        POLISHED_BLACKSTONE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_blackstone_slab", Material.ROCK, POLISHED_BLACKSTONE_SLAB)
                .setCreativeTab(TB_NETHER);
        registerSlab(POLISHED_BLACKSTONE_SLAB, POLISHED_BLACKSTONE_SLAB_D, event);

        POLISHED_BLACKSTONE_BRICK_SLAB = (BlockSlab) new BlockModHalfSlab("polished_blackstone_bricks_slab", Material.ROCK)
                .setCreativeTab(TB_NETHER);
        POLISHED_BLACKSTONE_BRICK_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_blackstone_bricks_slab", Material.ROCK, POLISHED_BLACKSTONE_BRICK_SLAB)
                .setCreativeTab(TB_NETHER);
        registerSlab(POLISHED_BLACKSTONE_BRICK_SLAB, POLISHED_BLACKSTONE_BRICK_SLAB_D, event);

        POLISHED_DIORITE_SLAB = (BlockSlab) new BlockModHalfSlab("polished_diorite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        POLISHED_DIORITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_diorite_slab", Material.ROCK, POLISHED_DIORITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(POLISHED_DIORITE_SLAB, POLISHED_DIORITE_SLAB_D, event);

        POLISHED_ANDESITE_SLAB = (BlockSlab) new BlockModHalfSlab("polished_andesite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        POLISHED_ANDESITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_andesite_slab", Material.ROCK, POLISHED_ANDESITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(POLISHED_ANDESITE_SLAB, POLISHED_ANDESITE_SLAB_D, event);

        POLISHED_GRANITE_SLAB = (BlockSlab) new BlockModHalfSlab("polished_granite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        POLISHED_GRANITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_granite_slab", Material.ROCK, POLISHED_GRANITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(POLISHED_GRANITE_SLAB, POLISHED_GRANITE_SLAB_D, event);

        DIORITE_SLAB = (BlockSlab) new BlockModHalfSlab("diorite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        DIORITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("diorite_slab", Material.ROCK, DIORITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(DIORITE_SLAB, DIORITE_SLAB_D, event);

        ANDESITE_SLAB = (BlockSlab) new BlockModHalfSlab("andesite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        ANDESITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("andesite_slab", Material.ROCK, ANDESITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(ANDESITE_SLAB, ANDESITE_SLAB_D, event);

        GRANITE_SLAB = (BlockSlab) new BlockModHalfSlab("granite_slab", Material.ROCK)
                .setCreativeTab(TB_AQUATIC);
        GRANITE_SLAB_D = (BlockSlab) new BlockModDoubleSlab("granite_slab", Material.ROCK, GRANITE_SLAB)
                .setCreativeTab(TB_AQUATIC);
        registerSlab(GRANITE_SLAB, GRANITE_SLAB_D, event);

        TUFF_SLAB = (BlockSlab) new BlockModHalfSlab("tuff_slab", Material.ROCK)
                .setCreativeTab(TB_TRAILS_TALES);
        TUFF_SLAB_D = (BlockSlab) new BlockModDoubleSlab("tuff_slab", Material.ROCK, TUFF_SLAB)
                .setCreativeTab(TB_TRAILS_TALES);
        registerSlab(TUFF_SLAB, TUFF_SLAB_D, event);

        TUFF_BRICK_SLAB = (BlockSlab) new BlockModHalfSlab("tuff_brick_slab", Material.ROCK)
                .setCreativeTab(TB_TRAILS_TALES);
        TUFF_BRICK_SLAB_D = (BlockSlab) new BlockModDoubleSlab("tuff_brick_slab", Material.ROCK, TUFF_BRICK_SLAB)
                .setCreativeTab(TB_TRAILS_TALES);
        registerSlab(TUFF_BRICK_SLAB, TUFF_BRICK_SLAB_D, event);

        POLISHED_TUFF_SLAB = (BlockSlab) new BlockModHalfSlab("polished_tuff_slab", Material.ROCK)
                .setCreativeTab(TB_TRAILS_TALES);
        POLISHED_TUFF_SLAB_D = (BlockSlab) new BlockModDoubleSlab("polished_tuff_slab", Material.ROCK, POLISHED_TUFF_SLAB)
                .setCreativeTab(TB_TRAILS_TALES);
        registerSlab(POLISHED_TUFF_SLAB, POLISHED_TUFF_SLAB_D, event);
    }

    @SideOnly(Side.SERVER)
    @SubscribeEvent
    public static void registerItemServerBlocks(RegistryEvent.Register<Item> event) {
        for (Block b : blocks) {
            registerItemServer(b, event);
        }
        registerSlabItemsServer(event);

    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for (Block b : blocks) {
            UBM.getLogger().info("Registering item blocks for {}", b.getRegistryName().toString());
            registerItem(b, event);
        }
        registerSlabItems(event);
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
                BlockPos downPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                if (!world.getBlockState(downPos).isFullBlock()) return;
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
    }

    @SubscribeEvent
    public static void onBlockDrop(BlockEvent.HarvestDropsEvent event) {
        if (event.getState().getBlock() == Blocks.GOLD_ORE || event.getState().getBlock() == DEEPSLATE_ORE_GOLD) {
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
        if (event.getState().getBlock() == Blocks.IRON_ORE || event.getState().getBlock() == DEEPSLATE_ORE_IRON) {
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

    private static Item createItem(Block block) {
        Item item;
        if (block instanceof BlockDoor) {
            item = new ItemDoor(block)
                    .setRegistryName(block.getRegistryName())
                    .setUnlocalizedName(block.getUnlocalizedName().replace("tile.", ""))
                    .setCreativeTab(block.getCreativeTabToDisplayOn());
        } //TODO: Add handling for signs
        else if (block instanceof BlockCopperTorch) return null;
        else item = new ItemBlock(block).setRegistryName(block.getRegistryName());
        return item;
    }

    public static void registerItem(Block block, RegistryEvent.Register<Item> event) {
        Item item = createItem(block);
        if (item == null) return;
        event.getRegistry().register(item);
        registerItemModel(item);
    }


    public static void registerItemServer(Block block, RegistryEvent.Register<Item> event) {
        Item item = createItem(block);
        if (item == null) return;
        event.getRegistry().register(item);
    }
    
    private static void register(Block block, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
        blocks.add(block);
    }

    private static void registerSlab(BlockSlab half, BlockSlab doubleSlab, RegistryEvent.Register<Block> event) {
        event.getRegistry().register(half);
        event.getRegistry().register(doubleSlab);
        slabs.put(half, doubleSlab);
    }

    private static void registerSlabItems(RegistryEvent.Register<Item> event) {
        for (BlockSlab h : slabs.keySet()) {
            BlockSlab d = slabs.get(h);
            Item item = new ItemSlab(h, h, d).setRegistryName(h.getRegistryName());
            event.getRegistry().register(item);
            registerItemModel(item);
        }
    }

    private static void registerSlabItemsServer(RegistryEvent.Register<Item> event) {
        for (BlockSlab h : slabs.keySet()) {
            BlockSlab d = slabs.get(h);
            Item item = new ItemSlab(h, h, d).setRegistryName(h.getRegistryName());
            event.getRegistry().register(item);
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