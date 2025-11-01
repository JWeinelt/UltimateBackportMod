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
import net.minecraft.creativetab.CreativeTabs;
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

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModItems {
    private static final List<Item> items = new ArrayList<>();

    private static final CreativeTabs TB_NETHER = ModCreativeTabs.UBM_TAB_NETHER;
    private static final CreativeTabs TB_CAVES = ModCreativeTabs.UBM_TAB_CAVES;
    private static final CreativeTabs TB_WILD = ModCreativeTabs.UBM_TAB_WILD;
    private static final CreativeTabs TB_AQUATIC = ModCreativeTabs.UBM_TAB_AQUATIC;
    private static final CreativeTabs TB_PILLAGE = ModCreativeTabs.UBM_TAB_PILLAGE;
    private static final CreativeTabs TB_TRAILS_TALES = ModCreativeTabs.UBM_TAB_TRAILS_TALES;

    public static Item TRIM_NETHERITE_UPGRADE;
    // Do not touch. They are referenced via Reflection
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

    public static Item TRIAL_KEY;
    public static Item TRIAL_KEY_OMINOUS;

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
        register(OMINOUS_BOTTLE, event);


        //Armor trims
        TRIM_NETHERITE_UPGRADE = new ItemArmorTrim("netherite_upgrade")
                .setRegistryName("netherite_upgrade_template")
                .setUnlocalizedName("netherite_upgrade_template")
                .setCreativeTab(TB_WILD);
        register(TRIM_NETHERITE_UPGRADE, event);


        for (String s : TRIMS) {
            Item item = new ItemArmorTrim(s.toLowerCase())
                    .setRegistryName(s.toLowerCase() + "_armor_trim")
                    .setUnlocalizedName(s.toLowerCase() + "_armor_trim")
                    .setCreativeTab(TB_WILD);
            register(item, event);


            try {
                ModItems.class.getField("TRIM_" + s).set(null, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TRIAL_KEY = new ModItem("trial_key", TB_TRAILS_TALES);
        register(TRIAL_KEY, event);
        TRIAL_KEY_OMINOUS = new ModItem("trial_key_ominous", TB_TRAILS_TALES);
        register(TRIAL_KEY_OMINOUS, event);

        POTTERY_SHERD = new ItemPotterySherd();
        event.getRegistry().register(POTTERY_SHERD);

        NETHERITE_SCRAP = new ModItem("netherite_scrap", TB_NETHER);
        register(NETHERITE_SCRAP, event);

        COPPER_INGOT = new ModItem("copper_ingot", TB_CAVES);
        register(COPPER_INGOT, event);

        COPPER_TORCH = new ModItem("copper_torch", ModCreativeTabs.UBM_TAB_COPPER_AGE);
        register(COPPER_TORCH, event);

        NETHERITE_INGOT = new ItemNetheriteIngot();
        register(NETHERITE_INGOT, event);

        WOLF_ARMOR = new Item()
                .setUnlocalizedName("wolf_armor")
                .setRegistryName("wolf_armor")
                .setCreativeTab(TB_TRAILS_TALES)
                .setMaxDamage(200)
                .setMaxStackSize(1);
        register(WOLF_ARMOR, event);

        NETHERITE_SWORD = new NetheriteSword(ModMaterials.NETHERITE);
        register(NETHERITE_SWORD, event);

        NETHERITE_AXE = new NetheriteAxe(Item.ToolMaterial.DIAMOND);
        register(NETHERITE_AXE, event);

        NETHERITE_PICKAXE = new NetheritePickAxe(Item.ToolMaterial.DIAMOND);
        register(NETHERITE_PICKAXE, event);

        NETHERITE_SHOVEL = new NetheriteShovel(Item.ToolMaterial.DIAMOND);
        register(NETHERITE_SHOVEL, event);

        NETHERITE_HOE = new NetheriteHoe(Item.ToolMaterial.DIAMOND);
        register(NETHERITE_HOE, event);


        COPPER_SWORD = new CopperSword(ModMaterials.COPPER);
        register(COPPER_SWORD, event);

        COPPER_AXE = new CopperAxe(Item.ToolMaterial.DIAMOND);
        register(COPPER_AXE, event);

        COPPER_PICKAXE = new CopperPickAxe(Item.ToolMaterial.DIAMOND);
        register(COPPER_PICKAXE, event);

        COPPER_SHOVEL = new CopperShovel(Item.ToolMaterial.DIAMOND);
        register(COPPER_SHOVEL, event);

        COPPER_HOE = new CopperHoe(Item.ToolMaterial.DIAMOND);
        register(COPPER_HOE, event);


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


        register(COPPER_HELMET, event);

        register(COPPER_CHESTPLATE, event);

        register(COPPER_LEGGINGS, event);

        register(COPPER_BOOTS, event);



        NETHERITE_HELMET = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.HEAD)
                .setUnlocalizedName("netherite_helmet")
                .setRegistryName("netherite_helmet")
                .setCreativeTab(TB_NETHER);

        NETHERITE_CHESTPLATE = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.CHEST)
                .setUnlocalizedName("netherite_chestplate")
                .setRegistryName("netherite_chestplate")
                .setCreativeTab(TB_NETHER);

        NETHERITE_LEGGINGS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.LEGS)
                .setUnlocalizedName("netherite_leggings")
                .setRegistryName("netherite_leggings")
                .setCreativeTab(TB_NETHER);

        NETHERITE_BOOTS = new ItemArmor(ModMaterials.NETHERITE_ARMOR, 0, EntityEquipmentSlot.FEET)
                .setUnlocalizedName("netherite_boots")
                .setRegistryName("netherite_boots")
                .setCreativeTab(TB_NETHER);
        register(NETHERITE_HELMET, event);

        register(NETHERITE_CHESTPLATE, event);

        register(NETHERITE_LEGGINGS, event);

        register(NETHERITE_BOOTS, event);

        POWDER_SNOW_BUCKET = new Item()
                .setUnlocalizedName("powder_snow_bucket")
                .setRegistryName("powder_snow_bucket")
                .setMaxStackSize(1)
                .setCreativeTab(TB_CAVES);

        COPPER_NUGGET = new ModItem("copper_nugget", ModCreativeTabs.UBM_TAB_COPPER_AGE);

        CANDLE = new ModItem("candle", TB_TRAILS_TALES);

        HONEYCOMB = new ModItem("honeycomb", ModCreativeTabs.UBM_TAB_BEES);

        HONEY_BOTTLE = new ItemHoneyBottle();
        
        for (String color : COLORS) {
            Item item = new Item()
                    .setRegistryName(color + "_candle")
                    .setUnlocalizedName(color + "_candle")
                    .setCreativeTab(TB_TRAILS_TALES);

            register(item, event);


            try {
                ModItems.class.getField(color.toUpperCase() + "_CANDLE").set(null, item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        SWEET_BERRY = new ItemSweetBerry();
        GLOW_BERRIES = new ItemGlowBerry();
        BAMBOO_RAFT = new ItemBambooRaft();

        register(POWDER_SNOW_BUCKET, event);


        register(SWEET_BERRY, event);

        register(GLOW_BERRIES, event);

        register(BAMBOO_RAFT, event);


        register(CANDLE, event);


        register(HONEYCOMB, event);

        register(HONEY_BOTTLE, event);

        register(COPPER_NUGGET, event);



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

        register(SPAWN_EGG_FROG, event);
        register(SPAWN_EGG_TURTLE, event);
        register(SPAWN_EGG_GOAT, event);
        register(SPAWN_EGG_BEE, event);
        register(SPAWN_EGG_DOLPHIN, event);
        register(SPAWN_EGG_WARDEN, event);
        register(SPAWN_EGG_WOLF, event);
        register(SPAWN_EGG_VILLAGER, event);
        register(SPAWN_EGG_ALLAY, event);
        register(SPAWN_EGG_WANDERING_TRADER, event);
        register(SPAWN_EGG_STRIDER, event);
        register(SPAWN_EGG_SNIFFER, event);
        register(SPAWN_EGG_ARMADILLO, event);
        register(SPAWN_EGG_BREEZE, event);
        register(SPAWN_EGG_CAMEL, event);
        register(SPAWN_EGG_CAT, event);
        register(SPAWN_EGG_HOGLIN, event);
        register(SPAWN_EGG_PIGLIN, event);
        register(SPAWN_EGG_FOX, event);
        register(SPAWN_EGG_CREAKING, event);
        register(SPAWN_EGG_PHANTOM, event);
        register(SPAWN_EGG_AXOLOTL, event);
        register(SPAWN_EGG_ZOGLIN, event);
        register(SPAWN_EGG_COD, event);
        register(SPAWN_EGG_SALMON, event);
        register(SPAWN_EGG_PUFFERFISH, event);
        register(SPAWN_EGG_TROPICAL_FISH, event);
        register(SPAWN_EGG_GLOW_SQUID, event);
        register(SPAWN_EGG_PILLAGER, event);
        register(SPAWN_EGG_RAVAGER, event);

        AMETHYST_SHARD = new ModItem("amethyst_shard", TB_CAVES);
        register(AMETHYST_SHARD, event);


        TADPOLE_BUCKET = new ModItem("bucket_tadpole", TB_CAVES);
        register(TADPOLE_BUCKET, event);


        TRIDENT = new ModItem("trident", TB_AQUATIC);
        register(TRIDENT, event);


        CROSSBOW = new ItemCrossbow()
                .setUnlocalizedName("crossbow")
                .setRegistryName("crossbow");
        register(CROSSBOW, event);

        RAW_GOLD = new ModItem("raw_gold", TB_CAVES);
        register(RAW_GOLD, event);

        RAW_IRON = new ModItem("raw_iron", TB_CAVES);
        register(RAW_IRON, event);

        RAW_COPPER = new ModItem("raw_copper", TB_CAVES);
        register(RAW_COPPER, event);


        SPYGLASS = new ItemSpyglass()
                .setUnlocalizedName("spyglass")
                .setRegistryName("spyglass")
                .setCreativeTab(TB_CAVES);
        register(SPYGLASS, event);

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelEvent(final ModelRegistryEvent event) {
        for (Item m : items) registerItemModel(m);
    }

    public static void register(Item itm, RegistryEvent.Register<Item> ev) {
        ev.getRegistry() .register(itm);
        items.add(itm);
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