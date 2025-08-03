package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.plant.BlockSweetBerry;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBeeNest;
import de.julianweinelt.ubm.worldgen.ModBiomes;
import de.julianweinelt.ubm.worldgen.WorldGenBeeNest;
import de.julianweinelt.ubm.worldgen.WorldTypeSelectableBiome;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModBlocks {
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
        NETHERITE_BLOCK = new Block(Material.ROCK)
                .setUnlocalizedName("netherite_block")
                .setRegistryName("netherite_block")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(NETHERITE_BLOCK);
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

        WARPED_PLANKS = new Block(Material.WOOD)
                .setUnlocalizedName("warped_planks")
                .setRegistryName("warped_planks")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(WARPED_PLANKS);

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


        BEE_NEST = new Block(Material.WOOD)
                .setUnlocalizedName("bee_nest")
                .setRegistryName("bee_nest")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        event.getRegistry().register(BEE_NEST);
        BEE_HIVE = new Block(Material.WOOD)
                .setUnlocalizedName("bee_hive")
                .setRegistryName("bee_hive")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        event.getRegistry().register(BEE_HIVE);

        SWEET_BERRY_BUSH = new BlockSweetBerry().setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        event.getRegistry().register(SWEET_BERRY_BUSH);

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

        COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("copper_grate")
                .setRegistryName("copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
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

        EXPOSED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("exposed_copper_grate")
                .setRegistryName("exposed_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
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

        WEATHERED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("weathered_copper_grate")
                .setRegistryName("weathered_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
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

        OXIDIZED_COPPER_GRATE = new Block(Material.ROCK)
                .setUnlocalizedName("oxidized_copper_grate")
                .setRegistryName("oxidized_copper_grate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
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




        ModBiomes.init();
        new WorldTypeSelectableBiome("selectable_biome");
        GameRegistry.registerTileEntity(TileEntityBeeNest.class, new ResourceLocation("ubm", "bee_nest"));
        GameRegistry.registerWorldGenerator(new WorldGenBeeNest(), 0);
    }
    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        Item netheriteBlock = new ItemBlock(NETHERITE_BLOCK).setRegistryName(NETHERITE_BLOCK.getRegistryName());
        event.getRegistry().register(netheriteBlock);
        registerItemModel(netheriteBlock);
        Item ancientDebris = new ItemBlock(ANCIENT_DEBRIS).setRegistryName(ANCIENT_DEBRIS.getRegistryName());
        event.getRegistry().register(ancientDebris);
        registerItemModel(ancientDebris);
        Item crimsonStem = new ItemBlock(CRIMSON_STEM).setRegistryName(CRIMSON_STEM.getRegistryName());
        event.getRegistry().register(crimsonStem);
        registerItemModel(crimsonStem);
        Item crimsonHyphae = new ItemBlock(CRIMSON_HYPHAE).setRegistryName(CRIMSON_HYPHAE.getRegistryName());
        event.getRegistry().register(crimsonHyphae);
        registerItemModel(crimsonHyphae);
        Item warpedStem = new ItemBlock(WARPED_STEM).setRegistryName(WARPED_STEM.getRegistryName());
        event.getRegistry().register(warpedStem);
        registerItemModel(warpedStem);
        Item warpedHyphae = new ItemBlock(WARPED_HYPHAE).setRegistryName(WARPED_HYPHAE.getRegistryName());
        event.getRegistry().register(warpedHyphae);
        registerItemModel(warpedHyphae);
        Item warpedStairs = new ItemBlock(WARPED_STAIRS).setRegistryName(WARPED_STAIRS.getRegistryName());
        event.getRegistry().register(warpedStairs);
        registerItemModel(warpedStairs);
        Item warpedFence = new ItemBlock(WARPED_FENCE).setRegistryName(WARPED_FENCE.getRegistryName());
        event.getRegistry().register(warpedFence);
        registerItemModel(warpedFence);
        Item crimsonStairs = new ItemBlock(CRIMSON_STAIRS).setRegistryName(CRIMSON_STAIRS.getRegistryName());
        event.getRegistry().register(crimsonStairs);
        registerItemModel(crimsonStairs);

        Item blackStone = new ItemBlock(BLACKSTONE).setRegistryName(BLACKSTONE.getRegistryName());
        event.getRegistry().register(blackStone);
        registerItemModel(blackStone);
        Item polishedBlackstone = new ItemBlock(POLISHED_BLACKSTONE).setRegistryName(POLISHED_BLACKSTONE.getRegistryName());
        event.getRegistry().register(polishedBlackstone);
        registerItemModel(polishedBlackstone);

        Item chiseledPolishedBlackstone = new ItemBlock(CHISELED_POLISHED_BLACKSTONE).setRegistryName(CHISELED_POLISHED_BLACKSTONE.getRegistryName());
        event.getRegistry().register(chiseledPolishedBlackstone);
        registerItemModel(chiseledPolishedBlackstone);

        Item polishedBlackstoneBricks = new ItemBlock(POLISHED_BLACKSTONE_BRICKS).setRegistryName(POLISHED_BLACKSTONE_BRICKS.getRegistryName());
        event.getRegistry().register(polishedBlackstoneBricks);
        registerItemModel(polishedBlackstoneBricks);

        Item crackedPolishedBlackstoneBricks = new ItemBlock(CRACKED_POLISHED_BLACKSTONE_BRICKS).setRegistryName(CRACKED_POLISHED_BLACKSTONE_BRICKS.getRegistryName());
        event.getRegistry().register(crackedPolishedBlackstoneBricks);
        registerItemModel(crackedPolishedBlackstoneBricks);

        Item gildedBlackstone = new ItemBlock(GILDED_BLACKSTONE).setRegistryName(GILDED_BLACKSTONE.getRegistryName());
        event.getRegistry().register(gildedBlackstone);
        registerItemModel(gildedBlackstone);

        Item netherGoldOre = new ItemBlock(NETHER_GOLD_ORE).setRegistryName(NETHER_GOLD_ORE.getRegistryName());
        event.getRegistry().register(netherGoldOre);
        registerItemModel(netherGoldOre);

        Item soulSoil = new ItemBlock(SOUL_SOIL).setRegistryName(SOUL_SOIL.getRegistryName());
        event.getRegistry().register(soulSoil);
        registerItemModel(soulSoil);

        Item warpedNylium = new ItemBlock(WARPED_NYLIUM).setRegistryName(WARPED_NYLIUM.getRegistryName());
        event.getRegistry().register(warpedNylium);
        registerItemModel(warpedNylium);

        Item cryingObsidian = new ItemBlock(CRYING_OBSIDIAN).setRegistryName(CRYING_OBSIDIAN.getRegistryName());
        event.getRegistry().register(cryingObsidian);
        registerItemModel(cryingObsidian);

        Item crimsonNylium = new ItemBlock(CRIMSON_NYLIUM).setRegistryName(CRIMSON_NYLIUM.getRegistryName());
        event.getRegistry().register(crimsonNylium);
        registerItemModel(crimsonNylium);

        Item crimsonPlanks = new ItemBlock(CRIMSON_PLANKS).setRegistryName(CRIMSON_PLANKS.getRegistryName());
        event.getRegistry().register(crimsonPlanks);
        registerItemModel(crimsonPlanks);

        Item warpedPlanks = new ItemBlock(WARPED_PLANKS).setRegistryName(WARPED_PLANKS.getRegistryName());
        event.getRegistry().register(warpedPlanks);
        registerItemModel(warpedPlanks);

        Item warpedWartBlock = new ItemBlock(WARPED_WART_BLOCK).setRegistryName(WARPED_WART_BLOCK.getRegistryName());
        event.getRegistry().register(warpedWartBlock);
        registerItemModel(warpedWartBlock);

        Item blackstoneWall = new ItemBlock(BLACKSTONE_WALL).setRegistryName(BLACKSTONE_WALL.getRegistryName());
        event.getRegistry().register(blackstoneWall);
        registerItemModel(blackstoneWall);

        Item ochreFrogLight = new ItemBlock(OCHRE_FROGLIGHT).setRegistryName(OCHRE_FROGLIGHT.getRegistryName());
        event.getRegistry().register(ochreFrogLight);
        registerItemModel(ochreFrogLight);

        Item verdantFrogLight = new ItemBlock(VERDANT_FROGLIGHT).setRegistryName(VERDANT_FROGLIGHT.getRegistryName());
        event.getRegistry().register(verdantFrogLight);
        registerItemModel(verdantFrogLight);

        Item pearlescentFrogLight = new ItemBlock(PEARLESCENT_FROGLIGHT).setRegistryName(PEARLESCENT_FROGLIGHT.getRegistryName());
        event.getRegistry().register(pearlescentFrogLight);
        registerItemModel(pearlescentFrogLight);

        Item beeNest = new ItemBlock(BEE_NEST).setRegistryName(BEE_NEST.getRegistryName());
        event.getRegistry().register(beeNest);
        registerItemModel(beeNest);
        Item beeHive = new ItemBlock(BEE_HIVE).setRegistryName(BEE_HIVE.getRegistryName());
        event.getRegistry().register(beeHive);
        registerItemModel(beeHive);

        Item sweetBerry = new ItemBlock(SWEET_BERRY_BUSH).setRegistryName(SWEET_BERRY_BUSH.getRegistryName());
        event.getRegistry().register(sweetBerry);
        registerItemModel(sweetBerry);

        // --- Normal Copper ---
        Item copperBlockItem = new ItemBlock(COPPER_BLOCK).setRegistryName(COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(copperBlockItem);
        registerItemModel(copperBlockItem);

        Item chiseledCopperItem = new ItemBlock(CHISELED_COPPER).setRegistryName(CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(chiseledCopperItem);
        registerItemModel(chiseledCopperItem);

        Item copperGrateItem = new ItemBlock(COPPER_GRATE).setRegistryName(COPPER_GRATE.getRegistryName());
        event.getRegistry().register(copperGrateItem);
        registerItemModel(copperGrateItem);

        Item cutCopperItem = new ItemBlock(CUT_COPPER).setRegistryName(CUT_COPPER.getRegistryName());
        event.getRegistry().register(cutCopperItem);
        registerItemModel(cutCopperItem);

        Item copperBulbItem = new ItemBlock(COPPER_BULB).setRegistryName(COPPER_BULB.getRegistryName());
        event.getRegistry().register(copperBulbItem);
        registerItemModel(copperBulbItem);

// --- Exposed Copper ---
        Item exposedCopperBlockItem = new ItemBlock(EXPOSED_COPPER_BLOCK).setRegistryName(EXPOSED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(exposedCopperBlockItem);
        registerItemModel(exposedCopperBlockItem);

        Item exposedChiseledCopperItem = new ItemBlock(EXPOSED_CHISELED_COPPER).setRegistryName(EXPOSED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(exposedChiseledCopperItem);
        registerItemModel(exposedChiseledCopperItem);

        Item exposedCopperGrateItem = new ItemBlock(EXPOSED_COPPER_GRATE).setRegistryName(EXPOSED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(exposedCopperGrateItem);
        registerItemModel(exposedCopperGrateItem);

        Item exposedCutCopperItem = new ItemBlock(EXPOSED_CUT_COPPER).setRegistryName(EXPOSED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(exposedCutCopperItem);
        registerItemModel(exposedCutCopperItem);

        Item exposedCopperBulbItem = new ItemBlock(EXPOSED_COPPER_BULB).setRegistryName(EXPOSED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(exposedCopperBulbItem);
        registerItemModel(exposedCopperBulbItem);

// --- Weathered Copper ---
        Item weatheredCopperBlockItem = new ItemBlock(WEATHERED_COPPER_BLOCK).setRegistryName(WEATHERED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(weatheredCopperBlockItem);
        registerItemModel(weatheredCopperBlockItem);

        Item weatheredChiseledCopperItem = new ItemBlock(WEATHERED_CHISELED_COPPER).setRegistryName(WEATHERED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(weatheredChiseledCopperItem);
        registerItemModel(weatheredChiseledCopperItem);

        Item weatheredCopperGrateItem = new ItemBlock(WEATHERED_COPPER_GRATE).setRegistryName(WEATHERED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(weatheredCopperGrateItem);
        registerItemModel(weatheredCopperGrateItem);

        Item weatheredCutCopperItem = new ItemBlock(WEATHERED_CUT_COPPER).setRegistryName(WEATHERED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(weatheredCutCopperItem);
        registerItemModel(weatheredCutCopperItem);

        Item weatheredCopperBulbItem = new ItemBlock(WEATHERED_COPPER_BULB).setRegistryName(WEATHERED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(weatheredCopperBulbItem);
        registerItemModel(weatheredCopperBulbItem);

// --- Oxidized Copper ---
        Item oxidizedCopperBlockItem = new ItemBlock(OXIDIZED_COPPER_BLOCK).setRegistryName(OXIDIZED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(oxidizedCopperBlockItem);
        registerItemModel(oxidizedCopperBlockItem);

        Item oxidizedChiseledCopperItem = new ItemBlock(OXIDIZED_CHISELED_COPPER).setRegistryName(OXIDIZED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(oxidizedChiseledCopperItem);
        registerItemModel(oxidizedChiseledCopperItem);

        Item oxidizedCopperGrateItem = new ItemBlock(OXIDIZED_COPPER_GRATE).setRegistryName(OXIDIZED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(oxidizedCopperGrateItem);
        registerItemModel(oxidizedCopperGrateItem);

        Item oxidizedCutCopperItem = new ItemBlock(OXIDIZED_CUT_COPPER).setRegistryName(OXIDIZED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(oxidizedCutCopperItem);
        registerItemModel(oxidizedCutCopperItem);

        Item oxidizedCopperBulbItem = new ItemBlock(OXIDIZED_COPPER_BULB).setRegistryName(OXIDIZED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(oxidizedCopperBulbItem);
        registerItemModel(oxidizedCopperBulbItem);

// --- Waxed Copper ---
        Item waxedCopperBlockItem = new ItemBlock(WAXED_COPPER_BLOCK).setRegistryName(WAXED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(waxedCopperBlockItem);
        registerItemModel(waxedCopperBlockItem);

        Item waxedChiseledCopperItem = new ItemBlock(WAXED_CHISELED_COPPER).setRegistryName(WAXED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(waxedChiseledCopperItem);
        registerItemModel(waxedChiseledCopperItem);

        Item waxedCopperGrateItem = new ItemBlock(WAXED_COPPER_GRATE).setRegistryName(WAXED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(waxedCopperGrateItem);
        registerItemModel(waxedCopperGrateItem);

        Item waxedCutCopperItem = new ItemBlock(WAXED_CUT_COPPER).setRegistryName(WAXED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(waxedCutCopperItem);
        registerItemModel(waxedCutCopperItem);

        Item waxedCopperBulbItem = new ItemBlock(WAXED_COPPER_BULB).setRegistryName(WAXED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(waxedCopperBulbItem);
        registerItemModel(waxedCopperBulbItem);

// --- Waxed Exposed Copper ---
        Item waxedExposedCopperBlockItem = new ItemBlock(WAXED_EXPOSED_COPPER_BLOCK).setRegistryName(WAXED_EXPOSED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(waxedExposedCopperBlockItem);
        registerItemModel(waxedExposedCopperBlockItem);

        Item waxedExposedChiseledCopperItem = new ItemBlock(WAXED_EXPOSED_CHISELED_COPPER).setRegistryName(WAXED_EXPOSED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(waxedExposedChiseledCopperItem);
        registerItemModel(waxedExposedChiseledCopperItem);

        Item waxedExposedCopperGrateItem = new ItemBlock(WAXED_EXPOSED_COPPER_GRATE).setRegistryName(WAXED_EXPOSED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(waxedExposedCopperGrateItem);
        registerItemModel(waxedExposedCopperGrateItem);

        Item waxedExposedCutCopperItem = new ItemBlock(WAXED_EXPOSED_CUT_COPPER).setRegistryName(WAXED_EXPOSED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(waxedExposedCutCopperItem);
        registerItemModel(waxedExposedCutCopperItem);

        Item waxedExposedCopperBulbItem = new ItemBlock(WAXED_EXPOSED_COPPER_BULB).setRegistryName(WAXED_EXPOSED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(waxedExposedCopperBulbItem);
        registerItemModel(waxedExposedCopperBulbItem);

// --- Waxed Weathered Copper ---
        Item waxedWeatheredCopperBlockItem = new ItemBlock(WAXED_WEATHERED_COPPER_BLOCK).setRegistryName(WAXED_WEATHERED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(waxedWeatheredCopperBlockItem);
        registerItemModel(waxedWeatheredCopperBlockItem);

        Item waxedWeatheredChiseledCopperItem = new ItemBlock(WAXED_WEATHERED_CHISELED_COPPER).setRegistryName(WAXED_WEATHERED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(waxedWeatheredChiseledCopperItem);
        registerItemModel(waxedWeatheredChiseledCopperItem);

        Item waxedWeatheredCopperGrateItem = new ItemBlock(WAXED_WEATHERED_COPPER_GRATE).setRegistryName(WAXED_WEATHERED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(waxedWeatheredCopperGrateItem);
        registerItemModel(waxedWeatheredCopperGrateItem);

        Item waxedWeatheredCutCopperItem = new ItemBlock(WAXED_WEATHERED_CUT_COPPER).setRegistryName(WAXED_WEATHERED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(waxedWeatheredCutCopperItem);
        registerItemModel(waxedWeatheredCutCopperItem);

        Item waxedWeatheredCopperBulbItem = new ItemBlock(WAXED_WEATHERED_COPPER_BULB).setRegistryName(WAXED_WEATHERED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(waxedWeatheredCopperBulbItem);
        registerItemModel(waxedWeatheredCopperBulbItem);

// --- Waxed Oxidized Copper ---
        Item waxedOxidizedCopperBlockItem = new ItemBlock(WAXED_OXIDIZED_COPPER_BLOCK).setRegistryName(WAXED_OXIDIZED_COPPER_BLOCK.getRegistryName());
        event.getRegistry().register(waxedOxidizedCopperBlockItem);
        registerItemModel(waxedOxidizedCopperBlockItem);

        Item waxedOxidizedChiseledCopperItem = new ItemBlock(WAXED_OXIDIZED_CHISELED_COPPER).setRegistryName(WAXED_OXIDIZED_CHISELED_COPPER.getRegistryName());
        event.getRegistry().register(waxedOxidizedChiseledCopperItem);
        registerItemModel(waxedOxidizedChiseledCopperItem);

        Item waxedOxidizedCopperGrateItem = new ItemBlock(WAXED_OXIDIZED_COPPER_GRATE).setRegistryName(WAXED_OXIDIZED_COPPER_GRATE.getRegistryName());
        event.getRegistry().register(waxedOxidizedCopperGrateItem);
        registerItemModel(waxedOxidizedCopperGrateItem);

        Item waxedOxidizedCutCopperItem = new ItemBlock(WAXED_OXIDIZED_CUT_COPPER).setRegistryName(WAXED_OXIDIZED_CUT_COPPER.getRegistryName());
        event.getRegistry().register(waxedOxidizedCutCopperItem);
        registerItemModel(waxedOxidizedCutCopperItem);

        Item waxedOxidizedCopperBulbItem = new ItemBlock(WAXED_OXIDIZED_COPPER_BULB).setRegistryName(WAXED_OXIDIZED_COPPER_BULB.getRegistryName());
        event.getRegistry().register(waxedOxidizedCopperBulbItem);
        registerItemModel(waxedOxidizedCopperBulbItem);

    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EnumHand hand = event.getHand();

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
                    Block block = (Block) ModBlocks.class.getField(blockName).get(null);
                    world.setBlockState(targetPos, block.getDefaultState());
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