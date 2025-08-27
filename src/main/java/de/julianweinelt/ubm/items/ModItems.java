package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.*;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.misc.ModMaterials;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModItems {

    // Netherite Items
    public static Item NETHERITE_SCRAP;
    public static Item NETHERITE_INGOT;
    public static Item NETHERITE_SWORD;
    public static Item NETHERITE_AXE;
    public static Item NETHERITE_PICKAXE;
    public static Item NETHERITE_SHOVEL;
    public static Item NETHERITE_HOE;
    public static Item NETHERITE_HELMET;
    public static Item NETHERITE_CHESTPLATE;
    public static Item NETHERITE_LEGGINGS;
    public static Item NETHERITE_BOOTS;

    public static Item COPPER_NUGGET;
    public static Item COPPER_HELMET;
    public static Item COPPER_CHESTPLATE;
    public static Item COPPER_LEGGINGS;
    public static Item COPPER_BOOTS;
    public static Item COPPER_SWORD;
    public static Item COPPER_AXE;
    public static Item COPPER_PICKAXE;
    public static Item COPPER_SHOVEL;
    public static Item COPPER_HOE;
    public static Item WARPED_FUNGUS_ON_A_STICK;
    public static Item SNOUT_BANNER_PATTERN;

    // Pottery Sherds
    public static Item ANGLER_POTTERY_SHERD;
    public static Item ARCHER_POTTERY_SHERD;
    public static Item ARMS_UP_POTTERY_SHERD;
    public static Item BLADE_POTTERY_SHERD;
    public static Item BURN_POTTERY_SHERD;
    public static Item BREWER_POTTERY_SHERD;
    public static Item DANGER_POTTERY_SHERD;
    public static Item EXPLORER_POTTERY_SHERD;
    public static Item FRIEND_POTTERY_SHERD;
    public static Item HEART_POTTERY_SHERD;
    public static Item HEARTBREAK_POTTERY_SHERD;
    public static Item HOWL_POTTERY_SHERD;
    public static Item MINER_POTTERY_SHERD;
    public static Item MOURNER_POTTERY_SHERD;
    public static Item PLENTY_POTTERY_SHERD;
    public static Item PRIZE_POTTERY_SHERD;
    public static Item SHEAF_POTTERY_SHERD;
    public static Item SHELTER_POTTERY_SHERD;
    public static Item SKULL_POTTERY_SHERD;
    public static Item SNORT_POTTERY_SHERD;

    // Cave Items
    public static Item POWDER_SNOW_BUCKET;
    public static Item AMETHYST_SHARD;
    public static Item RAW_IRON;
    public static Item RAW_COPPER;
    public static Item RAW_GOLD;

    // Wild Items
    public static Item SWEET_BERRY;
    public static Item BAMBOO_RAFT;

    // Spawn Eggs
    public static Item SPAWN_EGG_BEE;
    public static Item SPAWN_EGG_TURTLE;
    public static Item SPAWN_EGG_GOAT;
    public static Item SPAWN_EGG_DOLPHIN;
    public static Item SPAWN_EGG_FROG;
    public static Item SPAWN_EGG_WARDEN;
    public static Item SPAWN_EGG_VILLAGER;

    // Candles
    public static Item CANDLE;
    public static Item BLACK_CANDLE;
    public static Item RED_CANDLE;
    public static Item GREEN_CANDLE;
    public static Item BROWN_CANDLE;
    public static Item BLUE_CANDLE;
    public static Item PURPLE_CANDLE;
    public static Item CYAN_CANDLE;
    public static Item LIGHT_GRAY_CANDLE;
    public static Item GRAY_CANDLE;
    public static Item PINK_CANDLE;
    public static Item LIME_CANDLE;
    public static Item YELLOW_CANDLE;
    public static Item LIGHT_BLUE_CANDLE;
    public static Item MAGENTA_CANDLE;
    public static Item ORANGE_CANDLE;
    public static Item WHITE_CANDLE;

    // Bee Items
    public static Item HONEYCOMB;
    public static Item HONEY_BOTTLE;

    // Constants
    private static final String[] COLORS = {
            "black", "red", "green", "brown", "blue", "purple", "cyan",
            "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta",
            "orange", "white"
    };

    private static final String[] POTTERY_SHERD_NAMES = {
            "angler", "archer", "arms_up", "blade", "burn", "brewer", "danger", "explorer",
            "friend", "heart", "heartbreak", "howl", "miner", "mourner", "plenty", "prize",
            "sheaf", "shelter", "skull", "snort"
    };

    // Helper Methods
    private static Item createBasicItem(String name, CreativeTabs tab) {
        return new Item()
                .setUnlocalizedName(name)
                .setRegistryName(name)
                .setCreativeTab(tab);
    }

    private static void registerItem(RegistryEvent.Register<Item> event, Item item) {
        event.getRegistry().register(item);
    }


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        // Netherite Items
        NETHERITE_SCRAP = createBasicItem("netherite_scrap", ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_SCRAP);

        NETHERITE_INGOT = createBasicItem("netherite_ingot", ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_INGOT);

        // Netherite Tools
        NETHERITE_SWORD = new NetheriteSword(ModMaterials.NETHERITE);
        registerItem(event, NETHERITE_SWORD);

        NETHERITE_AXE = new NetheriteAxe(Item.ToolMaterial.DIAMOND);
        registerItem(event, NETHERITE_AXE);

        NETHERITE_PICKAXE = new NetheritePickAxe(Item.ToolMaterial.DIAMOND);
        registerItem(event, NETHERITE_PICKAXE);

        NETHERITE_SHOVEL = new NetheriteShovel(Item.ToolMaterial.DIAMOND);
        registerItem(event, NETHERITE_SHOVEL);

        NETHERITE_HOE = new NetheriteHoe(Item.ToolMaterial.DIAMOND);
        registerItem(event, NETHERITE_HOE);

        // Netherite Armor
        NETHERITE_HELMET = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.HEAD)
                .setUnlocalizedName("netherite_helmet")
                .setRegistryName("netherite_helmet")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_HELMET);

        NETHERITE_CHESTPLATE = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.CHEST)
                .setUnlocalizedName("netherite_chestplate")
                .setRegistryName("netherite_chestplate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_CHESTPLATE);

        NETHERITE_LEGGINGS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.LEGS)
                .setUnlocalizedName("netherite_leggings")
                .setRegistryName("netherite_leggings")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_LEGGINGS);

        NETHERITE_BOOTS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.FEET)
                .setUnlocalizedName("netherite_boots")
                .setRegistryName("netherite_boots")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        registerItem(event, NETHERITE_BOOTS);

        // Pottery Sherds
        ANGLER_POTTERY_SHERD = createBasicItem("angler_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, ANGLER_POTTERY_SHERD);

        ARCHER_POTTERY_SHERD = createBasicItem("archer_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, ARCHER_POTTERY_SHERD);

        ARMS_UP_POTTERY_SHERD = createBasicItem("arms_up_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, ARMS_UP_POTTERY_SHERD);

        BLADE_POTTERY_SHERD = createBasicItem("blade_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, BLADE_POTTERY_SHERD);

        BURN_POTTERY_SHERD = createBasicItem("burn_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, BURN_POTTERY_SHERD);

        BREWER_POTTERY_SHERD = createBasicItem("brewer_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, BREWER_POTTERY_SHERD);

        DANGER_POTTERY_SHERD = createBasicItem("danger_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, DANGER_POTTERY_SHERD);

        EXPLORER_POTTERY_SHERD = createBasicItem("explorer_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, EXPLORER_POTTERY_SHERD);

        FRIEND_POTTERY_SHERD = createBasicItem("friend_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, FRIEND_POTTERY_SHERD);

        HEART_POTTERY_SHERD = createBasicItem("heart_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, HEART_POTTERY_SHERD);

        HEARTBREAK_POTTERY_SHERD = createBasicItem("heartbreak_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, HEARTBREAK_POTTERY_SHERD);

        HOWL_POTTERY_SHERD = createBasicItem("howl_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, HOWL_POTTERY_SHERD);

        MINER_POTTERY_SHERD = createBasicItem("miner_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, MINER_POTTERY_SHERD);

        MOURNER_POTTERY_SHERD = createBasicItem("mourner_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, MOURNER_POTTERY_SHERD);

        PLENTY_POTTERY_SHERD = createBasicItem("plenty_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, PLENTY_POTTERY_SHERD);

        PRIZE_POTTERY_SHERD = createBasicItem("prize_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, PRIZE_POTTERY_SHERD);

        SHEAF_POTTERY_SHERD = createBasicItem("sheaf_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, SHEAF_POTTERY_SHERD);

        SHELTER_POTTERY_SHERD = createBasicItem("shelter_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, SHELTER_POTTERY_SHERD);

        SKULL_POTTERY_SHERD = createBasicItem("skull_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, SKULL_POTTERY_SHERD);

        SNORT_POTTERY_SHERD = createBasicItem("snort_pottery_sherd", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, SNORT_POTTERY_SHERD);

        // Cave Items
        POWDER_SNOW_BUCKET = new Item()
                .setUnlocalizedName("powder_snow_bucket")
                .setRegistryName("powder_snow_bucket")
                .setMaxStackSize(1)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        registerItem(event, POWDER_SNOW_BUCKET);

        AMETHYST_SHARD = createBasicItem("amethyst_shard", ModCreativeTabs.UBM_TAB_CAVES);
        registerItem(event, AMETHYST_SHARD);

        RAW_GOLD = createBasicItem("raw_gold", ModCreativeTabs.UBM_TAB_CAVES);
        registerItem(event, RAW_GOLD);

        RAW_IRON = createBasicItem("raw_iron", ModCreativeTabs.UBM_TAB_CAVES);
        registerItem(event, RAW_IRON);

        RAW_COPPER = createBasicItem("raw_copper", ModCreativeTabs.UBM_TAB_CAVES);
        registerItem(event, RAW_COPPER);

        // Candles
        CANDLE = createBasicItem("candle", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        registerItem(event, CANDLE);

        // Colored Candles - use loop for efficiency
        for (String color : COLORS) {
            Item item = createBasicItem(color + "_candle", ModCreativeTabs.UBM_TAB_TRAILS_TALES);
            registerItem(event, item);

            try {
                ModItems.class.getField(color.toUpperCase() + "_CANDLE").set(null, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Bee Items
        HONEYCOMB = createBasicItem("honeycomb", ModCreativeTabs.UBM_TAB_BEES);
        registerItem(event, HONEYCOMB);

        HONEY_BOTTLE = createBasicItem("honey_bottle", ModCreativeTabs.UBM_TAB_BEES);
        registerItem(event, HONEY_BOTTLE);

        // Wild Items
        SWEET_BERRY = new ItemSweetBerry();
        registerItem(event, SWEET_BERRY);

        BAMBOO_RAFT = new ItemBambooRaft();
        registerItem(event, BAMBOO_RAFT);

        // Spawn Eggs
        SPAWN_EGG_FROG = new ItemSpawnEggCustom(EntityFrog.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "frog");
        registerItem(event, SPAWN_EGG_FROG);

        SPAWN_EGG_TURTLE = new ItemSpawnEggCustom(EntityTurtle.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "turtle");
        registerItem(event, SPAWN_EGG_TURTLE);

        SPAWN_EGG_GOAT = new ItemSpawnEggCustom(EntityGoat.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "goat");
        registerItem(event, SPAWN_EGG_GOAT);

        SPAWN_EGG_BEE = new ItemSpawnEggCustom(EntityBee.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "bee");
        registerItem(event, SPAWN_EGG_BEE);

        SPAWN_EGG_WARDEN = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "warden");
        registerItem(event, SPAWN_EGG_WARDEN);

        SPAWN_EGG_DOLPHIN = new ItemSpawnEggCustom(EntityBee.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "dolphin"); //TODO: Add Entity
        registerItem(event, SPAWN_EGG_DOLPHIN);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelEvent(final ModelRegistryEvent event) {
        // Netherite Items
        registerItemModel(NETHERITE_SCRAP);
        registerItemModel(NETHERITE_INGOT);
        registerItemModel(NETHERITE_SWORD);
        registerItemModel(NETHERITE_AXE);
        registerItemModel(NETHERITE_PICKAXE);
        registerItemModel(NETHERITE_SHOVEL);
        registerItemModel(NETHERITE_HOE);
        registerItemModel(NETHERITE_HELMET);
        registerItemModel(NETHERITE_CHESTPLATE);
        registerItemModel(NETHERITE_LEGGINGS);
        registerItemModel(NETHERITE_BOOTS);

        // Pottery Sherds
        registerItemModel(ANGLER_POTTERY_SHERD);
        registerItemModel(ARCHER_POTTERY_SHERD);
        registerItemModel(ARMS_UP_POTTERY_SHERD);
        registerItemModel(BLADE_POTTERY_SHERD);
        registerItemModel(BURN_POTTERY_SHERD);
        registerItemModel(BREWER_POTTERY_SHERD);
        registerItemModel(DANGER_POTTERY_SHERD);
        registerItemModel(EXPLORER_POTTERY_SHERD);
        registerItemModel(FRIEND_POTTERY_SHERD);
        registerItemModel(HEART_POTTERY_SHERD);
        registerItemModel(HEARTBREAK_POTTERY_SHERD);
        registerItemModel(HOWL_POTTERY_SHERD);
        registerItemModel(MINER_POTTERY_SHERD);
        registerItemModel(MOURNER_POTTERY_SHERD);
        registerItemModel(PLENTY_POTTERY_SHERD);
        registerItemModel(PRIZE_POTTERY_SHERD);
        registerItemModel(SHEAF_POTTERY_SHERD);
        registerItemModel(SHELTER_POTTERY_SHERD);
        registerItemModel(SKULL_POTTERY_SHERD);
        registerItemModel(SNORT_POTTERY_SHERD);

        // Cave Items
        registerItemModel(POWDER_SNOW_BUCKET);
        registerItemModel(AMETHYST_SHARD);
        registerItemModel(RAW_IRON);
        registerItemModel(RAW_GOLD);
        registerItemModel(RAW_COPPER);

        // Wild Items
        registerItemModel(SWEET_BERRY);
        registerItemModel(BAMBOO_RAFT);

        // Spawn Eggs
        registerItemModel(SPAWN_EGG_FROG);
        registerItemModel(SPAWN_EGG_TURTLE);
        registerItemModel(SPAWN_EGG_BEE);
        registerItemModel(SPAWN_EGG_GOAT);
        registerItemModel(SPAWN_EGG_DOLPHIN);
        registerItemModel(SPAWN_EGG_WARDEN);

        // Candles
        registerItemModel(CANDLE);
        registerItemModel(BLACK_CANDLE);
        registerItemModel(RED_CANDLE);
        registerItemModel(GREEN_CANDLE);
        registerItemModel(BROWN_CANDLE);
        registerItemModel(BLUE_CANDLE);
        registerItemModel(PURPLE_CANDLE);
        registerItemModel(CYAN_CANDLE);
        registerItemModel(LIGHT_GRAY_CANDLE);
        registerItemModel(GRAY_CANDLE);
        registerItemModel(PINK_CANDLE);
        registerItemModel(LIME_CANDLE);
        registerItemModel(YELLOW_CANDLE);
        registerItemModel(LIGHT_BLUE_CANDLE);
        registerItemModel(MAGENTA_CANDLE);
        registerItemModel(ORANGE_CANDLE);
        registerItemModel(WHITE_CANDLE);

        // Bee Items
        registerItemModel(HONEYCOMB);
        registerItemModel(HONEY_BOTTLE);
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