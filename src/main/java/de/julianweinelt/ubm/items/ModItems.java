package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.*;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import de.julianweinelt.ubm.entities.EntitySalmon;
import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.items.tools.*;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.misc.ModMaterials;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModItems {
    public static Item TRIM_NETHERITE_UPGRADE;
    public static Item TRIM_SENTRY;
    public static Item TRIM_DUNE;
    public static Item TRIM_WARD;
    public static Item TRIM_EYE;
    public static Item TRIM_VEX;
    public static Item TRIM_WAYFINDER;
    public static Item TRIM_SILENCE;
    public static Item TRIM_SPIRE;
    public static Item TRIM_WILD;
    public static Item TRIM_RAISER;
    public static Item TRIM_TIDE;
    public static Item TRIM_FLOW;
    public static Item TRIM_COAST;
    public static Item TRIM_SHAPER;
    public static Item TRIM_SNOUT;
    public static Item TRIM_BOLT;
    public static Item TRIM_HOST;
    public static Item TRIM_RIB;

    public static Item COPPER_TORCH;

    public static Item COPPER_INGOT;

    public static Item OMINOUS_BOTTLE;

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

    public static Item WOLF_ARMOR;

    public static Item TRIDENT;
    public static Item CROSSBOW;

    public static Item TADPOLE_BUCKET;
    public static Item AXOLOTL_BUCKET;

    public static Item WARPED_FUNGUS_ON_A_STICK;

    public static Item GLOW_BERRIES;


    public static Item SNOUT_BANNER_PATTERN;

    public static Item SPYGLASS;

    public static Item POTTERY_SHERD;

    public static Item POWDER_SNOW_BUCKET;

    public static Item SWEET_BERRY;
    public static Item BAMBOO_RAFT;

    public static Item SPAWN_EGG_BEE;
    public static Item SPAWN_EGG_COD;
    public static Item SPAWN_EGG_SALMON;
    public static Item SPAWN_EGG_PUFFERFISH;
    public static Item SPAWN_EGG_TROPICAL_FISH;
    public static Item SPAWN_EGG_TURTLE;
    public static Item SPAWN_EGG_GOAT;
    public static Item SPAWN_EGG_DOLPHIN;
    public static Item SPAWN_EGG_FROG;
    public static Item SPAWN_EGG_WARDEN;
    public static Item SPAWN_EGG_VILLAGER;
    public static Item SPAWN_EGG_WOLF;
    public static Item SPAWN_EGG_WANDERING_TRADER;
    public static Item SPAWN_EGG_PHANTOM;
    public static Item SPAWN_EGG_ARMADILLO;
    public static Item SPAWN_EGG_STRIDER;
    public static Item SPAWN_EGG_SNIFFER;
    public static Item SPAWN_EGG_HOGLIN;
    public static Item SPAWN_EGG_CAT;
    public static Item SPAWN_EGG_CREAKING;
    public static Item SPAWN_EGG_AXOLOTL;
    public static Item SPAWN_EGG_BREEZE;
    public static Item SPAWN_EGG_CAMEL;
    public static Item SPAWN_EGG_ALLAY;
    public static Item SPAWN_EGG_FOX;
    public static Item SPAWN_EGG_PIGLIN;
    public static Item SPAWN_EGG_ZOGLIN;
    public static Item SPAWN_EGG_GLOW_SQUID;
    public static Item SPAWN_EGG_PILLAGER;
    public static Item SPAWN_EGG_RAVAGER;


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

    public static Item HONEYCOMB;
    public static Item HONEY_BOTTLE;

    public static Item AMETHYST_SHARD;

    public static Item RAW_IRON;
    public static Item RAW_COPPER;
    public static Item RAW_GOLD;

    private static final String[] COLORS = {
            "black", "red", "green", "brown", "blue", "purple", "cyan",
            "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta",
            "orange", "white"
    };

    private static final String[] TRIMS = {
            "SENTRY", "DUNE", "WARD", "EYE", "VEX", "WAYFINDER", "SILENCE",
            "SPIRE", "WILD", "RAISER", "TIDE", "FLOW", "COAST", "SHAPER",
            "SNOUT", "BOLT", "HOST", "RIB"
    };

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        OMINOUS_BOTTLE = new ItemOminousBottle();
        event.getRegistry().register(OMINOUS_BOTTLE);

        //Armor trims
        TRIM_NETHERITE_UPGRADE = new ItemArmorTrim("netherite_upgrade")
                .setRegistryName("netherite_upgrade_template")
                .setUnlocalizedName("netherite_upgrade_template")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
        event.getRegistry().register(TRIM_NETHERITE_UPGRADE);

        for (String s : TRIMS) {
            Item item = new ItemArmorTrim(s.toLowerCase())
                    .setRegistryName(s.toLowerCase() + "_armor_trim")
                    .setUnlocalizedName(s.toLowerCase() + "_armor_trim")
                    .setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
            event.getRegistry().register(item);

            try {
                ModItems.class.getField("TRIM_" + s).set(null, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        POTTERY_SHERD = new ItemPotterySherd();
        event.getRegistry().register(POTTERY_SHERD);

        NETHERITE_SCRAP = new Item()
                .setUnlocalizedName("netherite_scrap")
                .setRegistryName("netherite_scrap")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(NETHERITE_SCRAP);
        COPPER_INGOT = new Item()
                .setUnlocalizedName("copper_ingot")
                .setRegistryName("copper_ingot")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(COPPER_INGOT);
        COPPER_TORCH = new Item()
                .setUnlocalizedName("copper_torch")
                .setRegistryName("copper_torch")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);
        event.getRegistry().register(COPPER_TORCH);
        NETHERITE_INGOT = new ItemNetheriteIngot();
        event.getRegistry().register(NETHERITE_INGOT);
        WOLF_ARMOR = new Item()
                .setUnlocalizedName("wolf_armor")
                .setRegistryName("wolf_armor")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES)
                .setMaxDamage(200)
                .setMaxStackSize(1);
        event.getRegistry().register(WOLF_ARMOR);
        NETHERITE_SWORD = new NetheriteSword(ModMaterials.NETHERITE);
        event.getRegistry().register(NETHERITE_SWORD);
        NETHERITE_AXE = new NetheriteAxe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(NETHERITE_AXE);
        NETHERITE_PICKAXE = new NetheritePickAxe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(NETHERITE_PICKAXE);
        NETHERITE_SHOVEL = new NetheriteShovel(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(NETHERITE_SHOVEL);
        NETHERITE_HOE = new NetheriteHoe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(NETHERITE_HOE);

        COPPER_SWORD = new CopperSword(ModMaterials.COPPER);
        event.getRegistry().register(COPPER_SWORD);
        COPPER_AXE = new CopperAxe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(COPPER_AXE);
        COPPER_PICKAXE = new CopperPickAxe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(COPPER_PICKAXE);
        COPPER_SHOVEL = new CopperShovel(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(COPPER_SHOVEL);
        COPPER_HOE = new CopperHoe(Item.ToolMaterial.DIAMOND);
        event.getRegistry().register(COPPER_HOE);

        COPPER_HELMET = new ItemArmor(ModMaterials.COPPER_ARMOR, 0, EntityEquipmentSlot.HEAD)
                .setUnlocalizedName("copper_helmet")
                .setRegistryName("copper_helmet")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);

        COPPER_CHESTPLATE = new ItemArmor(ModMaterials.COPPER_ARMOR, 0, EntityEquipmentSlot.CHEST)
                .setUnlocalizedName("copper_chestplate")
                .setRegistryName("copper_chestplate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);

        COPPER_LEGGINGS = new ItemArmor(ModMaterials.COPPER_ARMOR, 0, EntityEquipmentSlot.LEGS)
                .setUnlocalizedName("copper_leggings")
                .setRegistryName("copper_leggings")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);

        COPPER_BOOTS = new ItemArmor(ModMaterials.COPPER_ARMOR, 0, EntityEquipmentSlot.FEET)
                .setUnlocalizedName("copper_boots")
                .setRegistryName("copper_boots")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);


        event.getRegistry().register(COPPER_HELMET);
        event.getRegistry().register(COPPER_CHESTPLATE);
        event.getRegistry().register(COPPER_LEGGINGS);
        event.getRegistry().register(COPPER_BOOTS);


        NETHERITE_HELMET = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.HEAD)
                .setUnlocalizedName("netherite_helmet")
                .setRegistryName("netherite_helmet")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);

        NETHERITE_CHESTPLATE = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.CHEST)
                .setUnlocalizedName("netherite_chestplate")
                .setRegistryName("netherite_chestplate")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);

        NETHERITE_LEGGINGS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.LEGS)
                .setUnlocalizedName("netherite_leggings")
                .setRegistryName("netherite_leggings")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);

        NETHERITE_BOOTS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.FEET)
                .setUnlocalizedName("netherite_boots")
                .setRegistryName("netherite_boots")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        event.getRegistry().register(NETHERITE_HELMET);
        event.getRegistry().register(NETHERITE_CHESTPLATE);
        event.getRegistry().register(NETHERITE_LEGGINGS);
        event.getRegistry().register(NETHERITE_BOOTS);

        POWDER_SNOW_BUCKET = new Item()
                .setUnlocalizedName("powder_snow_bucket")
                .setRegistryName("powder_snow_bucket")
                .setMaxStackSize(1)
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);

        COPPER_NUGGET = new Item()
                .setUnlocalizedName("copper_nugget")
                .setRegistryName("copper_nugget")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_COPPER_AGE);

        CANDLE = new Item()
                .setUnlocalizedName("candle")
                .setRegistryName("candle")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);

        HONEYCOMB = new Item()
                .setUnlocalizedName("honeycomb")
                .setRegistryName("honeycomb")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);

        HONEY_BOTTLE = new ItemFood(2, 2, false) //TODO: Move to dedicated class
                .setUnlocalizedName("honey_bottle")
                .setRegistryName("honey_bottle")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        
        for (String color : COLORS) {
            Item item = new Item()
                    .setRegistryName(color + "_candle")
                    .setUnlocalizedName(color + "_candle")
                    .setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);

            event.getRegistry().register(item);

            try {
                ModItems.class.getField(color.toUpperCase() + "_CANDLE").set(null, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        SWEET_BERRY = new ItemSweetBerry();
        GLOW_BERRIES = new ItemGlowBerry();
        BAMBOO_RAFT = new ItemBambooRaft();

        event.getRegistry().register(POWDER_SNOW_BUCKET);

        event.getRegistry().register(SWEET_BERRY);
        event.getRegistry().register(GLOW_BERRIES);
        event.getRegistry().register(BAMBOO_RAFT);

        event.getRegistry().register(CANDLE);

        event.getRegistry().register(HONEYCOMB);
        event.getRegistry().register(HONEY_BOTTLE);
        event.getRegistry().register(COPPER_NUGGET);


        // Spawn Eggs
        SPAWN_EGG_FROG = new ItemSpawnEggCustom(EntityFrog.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "frog");
        SPAWN_EGG_TURTLE = new ItemSpawnEggCustom(EntityTurtle.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "turtle");
        SPAWN_EGG_GOAT = new ItemSpawnEggCustom(EntityGoat.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "goat");
        SPAWN_EGG_BEE = new ItemSpawnEggCustom(EntityBee.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "bee");
        SPAWN_EGG_WARDEN = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "warden");
        SPAWN_EGG_DOLPHIN = new ItemSpawnEggCustom(EntityDolphin.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "dolphin");
        SPAWN_EGG_WOLF = new ItemSpawnEggCustom(EntityCustomWolf.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "custom_wolf");
        SPAWN_EGG_VILLAGER = new ItemSpawnEggCustom(EntityNewVillager.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "villager");
        SPAWN_EGG_ALLAY = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "allay");//TODO: Add Entity
        SPAWN_EGG_WANDERING_TRADER = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "wandering_trader");//TODO: Add Entity
        SPAWN_EGG_STRIDER = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "strider");//TODO: Add Entity
        SPAWN_EGG_SNIFFER = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "sniffer");//TODO: Add Entity
        SPAWN_EGG_ARMADILLO = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "armadillo");//TODO: Add Entity
        SPAWN_EGG_BREEZE = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "breeze");//TODO: Add Entity
        SPAWN_EGG_CAMEL = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "camel");//TODO: Add Entity
        SPAWN_EGG_CAT = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "cat");//TODO: Add Entity
        SPAWN_EGG_HOGLIN = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "hoglin");//TODO: Add Entity
        SPAWN_EGG_PIGLIN = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "piglin");//TODO: Add Entity
        SPAWN_EGG_FOX = new ItemSpawnEggCustom(EntityFox.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "fox");
        SPAWN_EGG_CREAKING = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "creaking");//TODO: Add Entity
        SPAWN_EGG_PHANTOM = new ItemSpawnEggCustom(EntityPhantom.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "phantom");
        SPAWN_EGG_AXOLOTL = new ItemSpawnEggCustom(EntityAxolotl.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "axolotl");
        SPAWN_EGG_ZOGLIN = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "zoglin");//TODO: Add Entity
        SPAWN_EGG_COD = new ItemSpawnEggCustom(EntityCod.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "cod");
        SPAWN_EGG_SALMON = new ItemSpawnEggCustom(EntitySalmon.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "salmon");
        SPAWN_EGG_PUFFERFISH = new ItemSpawnEggCustom(EntityWarden.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "pufferfish");//TODO: Add Entity
        SPAWN_EGG_TROPICAL_FISH = new ItemSpawnEggCustom(EntityTropicalFish.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "tropical_fish");
        SPAWN_EGG_GLOW_SQUID = new ItemSpawnEggCustom(EntityGlowSquid.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "glow_squid");
        SPAWN_EGG_PILLAGER = new ItemSpawnEggCustom(EntityPillager.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "pillager");
        SPAWN_EGG_RAVAGER = new ItemSpawnEggCustom(EntityGlowSquid.class, ModCreativeTabs.UBM_TAB_SPAWN_EGGS, "ravager");//TODO: Add Entity

        event.getRegistry().register(SPAWN_EGG_FROG);
        event.getRegistry().register(SPAWN_EGG_TURTLE);
        event.getRegistry().register(SPAWN_EGG_GOAT);
        event.getRegistry().register(SPAWN_EGG_BEE);
        event.getRegistry().register(SPAWN_EGG_DOLPHIN);
        event.getRegistry().register(SPAWN_EGG_WARDEN);
        event.getRegistry().register(SPAWN_EGG_WOLF);
        event.getRegistry().register(SPAWN_EGG_VILLAGER);
        //event.getRegistry().register(SPAWN_EGG_ALLAY);
        //event.getRegistry().register(SPAWN_EGG_WANDERING_TRADER);
        //event.getRegistry().register(SPAWN_EGG_STRIDER);
        //event.getRegistry().register(SPAWN_EGG_SNIFFER);
        //event.getRegistry().register(SPAWN_EGG_ARMADILLO);
        //event.getRegistry().register(SPAWN_EGG_BREEZE);
        //event.getRegistry().register(SPAWN_EGG_CAMEL);
        //event.getRegistry().register(SPAWN_EGG_CAT);
        //event.getRegistry().register(SPAWN_EGG_HOGLIN);
        //event.getRegistry().register(SPAWN_EGG_PIGLIN);
        event.getRegistry().register(SPAWN_EGG_FOX);
        //event.getRegistry().register(SPAWN_EGG_CREAKING);
        event.getRegistry().register(SPAWN_EGG_PHANTOM);
        event.getRegistry().register(SPAWN_EGG_AXOLOTL);
        //event.getRegistry().register(SPAWN_EGG_ZOGLIN);

        event.getRegistry().register(SPAWN_EGG_COD);
        event.getRegistry().register(SPAWN_EGG_SALMON);
        //event.getRegistry().register(SPAWN_EGG_PUFFERFISH);
        event.getRegistry().register(SPAWN_EGG_TROPICAL_FISH);
        event.getRegistry().register(SPAWN_EGG_GLOW_SQUID);

        event.getRegistry().register(SPAWN_EGG_PILLAGER);
        //event.getRegistry().register(SPAWN_EGG_RAVAGER);

        AMETHYST_SHARD = new Item()
                .setUnlocalizedName("amethyst_shard")
                .setRegistryName("amethyst_shard")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(AMETHYST_SHARD);

        TADPOLE_BUCKET = new Item()
                .setUnlocalizedName("bucket_tadpole")
                .setRegistryName("bucket_tadpole")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(TADPOLE_BUCKET);

        TRIDENT = new Item()
                .setUnlocalizedName("trident")
                .setRegistryName("trident")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_AQUATIC);
        event.getRegistry().register(TRIDENT);

        CROSSBOW = new ItemCrossbow()
                .setUnlocalizedName("crossbow")
                .setRegistryName("crossbow");
        event.getRegistry().register(CROSSBOW);
        RAW_GOLD = new Item()
                .setUnlocalizedName("raw_gold")
                .setRegistryName("raw_gold")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(RAW_GOLD);
        RAW_IRON = new Item()
                .setUnlocalizedName("raw_iron")
                .setRegistryName("raw_iron")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(RAW_IRON);
        RAW_COPPER = new Item()
                .setUnlocalizedName("raw_copper")
                .setRegistryName("raw_copper")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(RAW_COPPER);

        SPYGLASS = new ItemSpyglass()
                .setUnlocalizedName("spyglass")
                .setRegistryName("spyglass")
                .setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        event.getRegistry().register(SPYGLASS);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelEvent(final ModelRegistryEvent event) {
        registerItemModel(OMINOUS_BOTTLE);
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

        registerItemModel(SPYGLASS);

        registerItemModel(COPPER_SWORD);
        registerItemModel(COPPER_PICKAXE);
        registerItemModel(COPPER_SHOVEL);
        registerItemModel(COPPER_AXE);
        registerItemModel(COPPER_HOE);
        registerItemModel(COPPER_HELMET);
        registerItemModel(COPPER_CHESTPLATE);
        registerItemModel(COPPER_LEGGINGS);
        registerItemModel(COPPER_BOOTS);

        registerItemModel(COPPER_TORCH);

        registerItemModel(POWDER_SNOW_BUCKET);
        registerItemModel(SWEET_BERRY);
        registerItemModel(GLOW_BERRIES);

        registerItemModel(BAMBOO_RAFT);

        registerItemModel(SPAWN_EGG_FROG);
        registerItemModel(SPAWN_EGG_TURTLE);
        registerItemModel(SPAWN_EGG_BEE);
        registerItemModel(SPAWN_EGG_GOAT);
        registerItemModel(SPAWN_EGG_DOLPHIN);
        registerItemModel(SPAWN_EGG_WARDEN);
        registerItemModel(SPAWN_EGG_WOLF);
        registerItemModel(SPAWN_EGG_VILLAGER);

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

        registerItemModel(HONEYCOMB);
        registerItemModel(HONEY_BOTTLE);
        registerItemModel(AMETHYST_SHARD);
        registerItemModel(TADPOLE_BUCKET);

        registerItemModel(RAW_IRON);
        registerItemModel(RAW_GOLD);
        registerItemModel(RAW_COPPER);

        registerItemModel(COPPER_NUGGET);

        registerItemModel(SPAWN_EGG_ALLAY);
        registerItemModel(SPAWN_EGG_SNIFFER);
        registerItemModel(SPAWN_EGG_FOX);
        registerItemModel(SPAWN_EGG_CAT);
        registerItemModel(SPAWN_EGG_PHANTOM);
        registerItemModel(SPAWN_EGG_AXOLOTL);
        registerItemModel(SPAWN_EGG_STRIDER);
        registerItemModel(SPAWN_EGG_WANDERING_TRADER);
        registerItemModel(SPAWN_EGG_ZOGLIN);
        registerItemModel(SPAWN_EGG_HOGLIN);
        registerItemModel(SPAWN_EGG_PIGLIN);
        registerItemModel(SPAWN_EGG_CAMEL);
        registerItemModel(SPAWN_EGG_ARMADILLO);
        registerItemModel(SPAWN_EGG_BREEZE);
        registerItemModel(SPAWN_EGG_CREAKING);
        registerItemModel(SPAWN_EGG_COD);
        registerItemModel(SPAWN_EGG_SALMON);
        registerItemModel(SPAWN_EGG_PUFFERFISH);
        registerItemModel(SPAWN_EGG_TROPICAL_FISH);
        registerItemModel(SPAWN_EGG_GLOW_SQUID);
        registerItemModel(SPAWN_EGG_PILLAGER);
        registerItemModel(SPAWN_EGG_RAVAGER);
        registerItemModel(COPPER_INGOT);

        registerItemModel(WOLF_ARMOR);
        registerItemModel(TRIDENT);
        registerItemModel(CROSSBOW);
        registerItemModel(TRIM_NETHERITE_UPGRADE);

        for (String s : TRIMS) {
            try {
                registerItemModel((Item) ModItems.class.getField("TRIM_" + s).get(null));
            } catch (Exception e) {
                e.printStackTrace();
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


    public static ItemStack getOminousBanner() {
        ItemStack banner = new ItemStack(Items.BANNER, 1, 15);

        NBTTagCompound blockEntityTag = new NBTTagCompound();
        NBTTagList patterns = new NBTTagList();

        NBTTagCompound pattern1 = new NBTTagCompound();
        pattern1.setInteger("Color", 6);
        pattern1.setString("Pattern", "mr");
        patterns.appendTag(pattern1);

        NBTTagCompound pattern2 = new NBTTagCompound();
        pattern2.setInteger("Color", 7);
        pattern2.setString("Pattern", "bs");
        patterns.appendTag(pattern2);

        NBTTagCompound pattern3 = new NBTTagCompound();
        pattern3.setInteger("Color", 0);
        pattern3.setString("Pattern", "cs");
        patterns.appendTag(pattern3);

        NBTTagCompound pattern4 = new NBTTagCompound();
        pattern4.setInteger("Color", 0);
        pattern4.setString("Pattern", "ms");
        patterns.appendTag(pattern4);

        NBTTagCompound pattern5 = new NBTTagCompound();
        pattern5.setInteger("Color", 7);
        pattern5.setString("Pattern", "hh");
        patterns.appendTag(pattern5);

        NBTTagCompound pattern6 = new NBTTagCompound();
        pattern6.setInteger("Color", 0);
        pattern6.setString("Pattern", "bo");
        patterns.appendTag(pattern6);

        blockEntityTag.setTag("Patterns", patterns);

        banner.setTagInfo("BlockEntityTag", blockEntityTag);

        return banner;
    }
}