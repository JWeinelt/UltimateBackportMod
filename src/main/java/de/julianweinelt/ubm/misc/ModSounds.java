package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModSounds {

    public static SoundEvent BEE_AGGRESSIVE;
    public static SoundEvent BEE_HURT;
    public static SoundEvent BEE_DEATH;
    public static SoundEvent BEE_POLLINATE;

    public static SoundEvent BEE_HIVE_ENTER;
    public static SoundEvent BEE_HIVE_EXIT;



    public static SoundEvent FROG_DEATH;
    public static SoundEvent FROG_EAT;
    public static SoundEvent FROG_HURT;
    public static SoundEvent FROG_IDLE;
    public static SoundEvent FROG_LAY_SPAWN;
    public static SoundEvent FROG_LONG_JUMP;
    public static SoundEvent FROG_STEP;
    public static SoundEvent FROG_TONGUE;

    public static SoundEvent GOAT_DEATH;
    public static SoundEvent GOAT_EAT;
    public static SoundEvent GOAT_HORN_BREAK;
    public static SoundEvent GOAT_HURT;
    public static SoundEvent GOAT_IDLE;
    public static SoundEvent GOAT_IMPACT;
    public static SoundEvent GOAT_JUMP;
    public static SoundEvent GOAT_PRE_RAM;
    public static SoundEvent GOAT_SCREAM_DEATH;
    public static SoundEvent GOAT_SCREAM_HURT;
    public static SoundEvent GOAT_SCREAM_MILK;
    public static SoundEvent GOAT_SCREAM_PRE_RAM;
    public static SoundEvent GOAT_SCREAM;
    public static SoundEvent GOAT_STEP;

    public static SoundEvent DOLPHIN_ATTACK;
    public static SoundEvent DOLPHIN_BLOWHOLE;
    public static SoundEvent DOLPHIN_DEATH;
    public static SoundEvent DOLPHIN_EAT;
    public static SoundEvent DOLPHIN_HURT;
    public static SoundEvent DOLPHIN_IDLE_WATER;
    public static SoundEvent DOLPHIN_IDLE;
    public static SoundEvent DOLPHIN_JUMP;
    public static SoundEvent DOLPHIN_PLAY;
    public static SoundEvent DOLPHIN_SPLASH;
    public static SoundEvent DOLPHIN_SWIM;

    public static SoundEvent ITEM_CROSSBOW_LOADING_START;
    public static SoundEvent ITEM_CROSSBOW_LOADING_MIDDLE;
    public static SoundEvent ITEM_CROSSBOW_LOADING_END;
    public static SoundEvent ITEM_CROSSBOW_SHOOT;

    public static SoundEvent BLOCK_SMITHING_TABLE;
    public static SoundEvent BLOCK_NETHERITE_BLOCK_PLACE;
    public static SoundEvent BLOCK_NETHERITE_BLOCK_BREAK;
    public static SoundEvent BLOCK_NETHERITE_BLOCK_STEP;

    public static SoundEvent BLOCK_AMETHYST_BREAK;
    public static SoundEvent BLOCK_AMETHYST_STEP;
    public static SoundEvent BLOCK_AMETHYST_PLACE;
    public static SoundEvent BLOCK_AMETHYST_SHIMMER;
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_BREAK;
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_PLACE;
    public static SoundEvent BLOCK_ANCIENT_DEBRIS_BREAK;
    public static SoundEvent BLOCK_AZALEA_BREAK;
    public static SoundEvent BLOCK_AZALEA_STEP;
    public static SoundEvent BLOCK_AZALEA_LEAVES_BREAK;
    public static SoundEvent BLOCK_AZALEA_LEAVES_STEP;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_PLACE;
    public static SoundEvent BLOCK_BAMBOO_SAPLING_HIT;
    public static SoundEvent BLOCK_BAMBOO_STEP;
    public static SoundEvent BLOCK_BAMBOO_PLACE;
    public static SoundEvent BLOCK_BARREL_CLOSE;
    public static SoundEvent BLOCK_BARREL_OPEN;
    public static SoundEvent BLOCK_BASALT_BREAK;
    public static SoundEvent BLOCK_BASALT_STEP;
    public static SoundEvent BLOCK_BEEHIVE_SHEAR;
    public static SoundEvent BLOCK_BEEHIVE_WORK;
    public static SoundEvent BLOCK_BEEHIVE_DRIP;
    public static SoundEvent BLOCK_BELL_BELL_USE;
    public static SoundEvent BLOCK_BELL_RESONATE;
    public static SoundEvent BLOCK_BIG_DRIPLEAF_BREAK;
    public static SoundEvent BLOCK_BIG_DRIPLEAF_TILT_UP;
    public static SoundEvent BLOCK_BIG_DRIPLEAF_STEP;
    public static SoundEvent BLOCK_BIG_DRIPLEAF_TILT_DOWN;
    public static SoundEvent BLOCK_BLASTFURNACE_BLASTFURNACE;
    public static SoundEvent BLOCK_CALCITE_BREAK;
    public static SoundEvent BLOCK_CALCITE_STEP;
    public static SoundEvent BLOCK_CALCITE_PLACE;
    public static SoundEvent BLOCK_CAMPFIRE_CRACKLE;
    public static SoundEvent BLOCK_CANDLE_EXTINGUISH;
    public static SoundEvent BLOCK_CANDLE_BREAK;
    public static SoundEvent BLOCK_CANDLE_STEP;
    public static SoundEvent BLOCK_CANDLE_AMBIENT;
    public static SoundEvent BLOCK_CHAIN_BREAK;
    public static SoundEvent BLOCK_CHAIN_STEP;
    public static SoundEvent BLOCK_COMPOSTER_FILL_SUCCESS;
    public static SoundEvent BLOCK_COMPOSTER_READY;
    public static SoundEvent BLOCK_COMPOSTER_FILL;
    public static SoundEvent BLOCK_COMPOSTER_EMPTY;
    public static SoundEvent BLOCK_CONDUIT_ATTACK;
    public static SoundEvent BLOCK_CONDUIT_ACTIVATE;
    public static SoundEvent BLOCK_CONDUIT_SHORT;
    public static SoundEvent BLOCK_CONDUIT_AMBIENT;
    public static SoundEvent BLOCK_CONDUIT_DEACTIVATE;
    public static SoundEvent BLOCK_COPPER_BREAK;
    public static SoundEvent BLOCK_COPPER_STEP;
    public static SoundEvent BLOCK_DEEPSLATE_BREAK;
    public static SoundEvent BLOCK_DEEPSLATE_STEP;
    public static SoundEvent BLOCK_DEEPSLATE_PLACE;
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_STEP;
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_PLACE;
    public static SoundEvent BLOCK_DRIPSTONE_BREAK;
    public static SoundEvent BLOCK_DRIPSTONE_STEP;
    public static SoundEvent BLOCK_FLETCHING_TABLE_FLETCHING_TABLE;
    public static SoundEvent BLOCK_GRINDSTONE_GRINDSTONE;
    public static SoundEvent BLOCK_HONEYBLOCK_BREAK;
    public static SoundEvent BLOCK_HONEYBLOCK_SLIDE;
    public static SoundEvent BLOCK_HONEYBLOCK_STEP;
    public static SoundEvent BLOCK_LANTERN_BREAK;
    public static SoundEvent BLOCK_LANTERN_PLACE;
    public static SoundEvent BLOCK_LODESTONE_LOCK;
    public static SoundEvent BLOCK_LODESTONE_PLACE;
    public static SoundEvent BLOCK_MOSS_BREAK;
    public static SoundEvent BLOCK_MOSS_STEP;
    public static SoundEvent BLOCK_NETHERITE_BREAK;
    public static SoundEvent BLOCK_NETHERITE_STEP;
    public static SoundEvent BLOCK_NYLIUM_BREAK;
    public static SoundEvent BLOCK_NYLIUM_STEP;
    public static SoundEvent BLOCK_POINTED_DRIPSTONE_DRIP_LAVA;
    public static SoundEvent BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_CAULDRON;
    public static SoundEvent BLOCK_POINTED_DRIPSTONE_LAND;
    public static SoundEvent BLOCK_POINTED_DRIPSTONE_DRIP_WATER;
    public static SoundEvent BLOCK_POINTED_DRIPSTONE_DRIP_WATER_CAULDRON;
    public static SoundEvent BLOCK_POWDER_SNOW_BREAK;
    public static SoundEvent BLOCK_POWDER_SNOW_STEP;
    public static SoundEvent BLOCK_RESPAWN_ANCHOR_SET_SPAWN;
    public static SoundEvent BLOCK_RESPAWN_ANCHOR_CHARGE;
    public static SoundEvent BLOCK_RESPAWN_ANCHOR_AMBIENT;
    public static SoundEvent BLOCK_RESPAWN_ANCHOR_DEPLETE;
    public static SoundEvent BLOCK_ROOTED_DIRT_BREAK;
    public static SoundEvent BLOCK_ROOTED_DIRT_STEP;
    public static SoundEvent BLOCK_SCAFFOLD_PLACE;
    public static SoundEvent BLOCK_SCULK_STEP;
    public static SoundEvent BLOCK_SCULK_SENSOR_SCULK_CLICKING_STOP;
    public static SoundEvent BLOCK_SCULK_SENSOR_BREAK;
    public static SoundEvent BLOCK_SCULK_SENSOR_PLACE;
    public static SoundEvent BLOCK_SCULK_SENSOR_SCULK_CLICKING;
    public static SoundEvent BLOCK_SHROOMLIGHT_BREAK;
    public static SoundEvent BLOCK_SHROOMLIGHT_STEP;
    public static SoundEvent BLOCK_SMOKER_SMOKER;
    public static SoundEvent BLOCK_SOUL_SOIL_BREAK;
    public static SoundEvent BLOCK_SOUL_SOIL_STEP;
    public static SoundEvent BLOCK_SPORE_BLOSSOM_BREAK;
    public static SoundEvent BLOCK_SPORE_BLOSSOM_STEP;
    public static SoundEvent BLOCK_SWEET_BERRY_BUSH_BREAK;
    public static SoundEvent BLOCK_SWEET_BERRY_BUSH_PLACE;
    public static SoundEvent BLOCK_TUFF_BREAK;
    public static SoundEvent BLOCK_TUFF_STEP;

    public static SoundEvent ITEM_AXE_SCRAPE;
    public static SoundEvent ITEM_AXE_STRIP;
    public static SoundEvent ITEM_AXE_WAX_OFF;
    public static SoundEvent ITEM_HONEYCOMB_WAX_ON;


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        ITEM_AXE_SCRAPE = register(event, "item.axe.scrape");
        ITEM_AXE_STRIP = register(event, "item.axe.strip");
        ITEM_AXE_WAX_OFF = register(event, "item.axe.wax_off");
        ITEM_HONEYCOMB_WAX_ON = register(event, "item.honeycomb.wax_on");

        BEE_HURT = register(event, "mob.bee.hurt");
        BEE_AGGRESSIVE = register(event, "mob.bee.aggressive");
        BEE_DEATH = register(event, "mob.bee.death");
        BEE_POLLINATE = register(event, "mob.bee.pollinate");

        FROG_DEATH = register(event, "mob.frog.death");
        FROG_EAT = register(event, "mob.frog.eat");
        FROG_HURT = register(event, "mob.frog.hurt");
        FROG_IDLE = register(event, "mob.frog.idle");
        FROG_LAY_SPAWN = register(event, "mob.frog.lay_spawn");
        FROG_LONG_JUMP = register(event, "mob.frog.long_jump");
        FROG_STEP = register(event, "mob.frog.step");
        FROG_TONGUE = register(event, "mob.frog.tongue");

        GOAT_DEATH = register(event, "mob.goat.death");
        GOAT_EAT = register(event, "mob.goat.eat");
        GOAT_HORN_BREAK = register(event, "mob.goat.horn_break");
        GOAT_HURT = register(event, "mob.goat.hurt");
        GOAT_IDLE = register(event, "mob.goat.idle");
        GOAT_IMPACT = register(event, "mob.goat.impact");
        GOAT_JUMP = register(event, "mob.goat.jump");
        GOAT_PRE_RAM = register(event, "mob.goat.pre_ram");
        GOAT_SCREAM_DEATH = register(event, "mob.goat.scream.death");
        GOAT_SCREAM_HURT = register(event, "mob.goat.scream.hurt");
        GOAT_SCREAM_MILK = register(event, "mob.goat.scream.milk");
        GOAT_SCREAM_PRE_RAM = register(event, "mob.goat.scream.pre_ram");
        GOAT_SCREAM = register(event, "mob.goat.scream");
        GOAT_STEP = register(event, "mob.goat.step");

        BEE_HIVE_ENTER = register(event, "block.beehive.enter");
        BEE_HIVE_EXIT = register(event, "block.beehive.exit");

        DOLPHIN_ATTACK = register(event, "mob.dolphin.attack");
        DOLPHIN_BLOWHOLE = register(event, "mob.dolphin.blowhole");
        DOLPHIN_DEATH = register(event, "mob.dolphin.death");
        DOLPHIN_EAT = register(event, "mob.dolphin.eat");
        DOLPHIN_HURT = register(event, "mob.dolphin.hurt");
        DOLPHIN_IDLE_WATER = register(event, "mob.dolphin.idle_water");
        DOLPHIN_IDLE = register(event, "mob.dolphin.idle");
        DOLPHIN_JUMP = register(event, "mob.dolphin.jump");
        DOLPHIN_PLAY = register(event, "mob.dolphin.play");
        DOLPHIN_SPLASH = register(event, "mob.dolphin.splash");
        DOLPHIN_SWIM = register(event, "mob.dolphin.swim");

        ITEM_CROSSBOW_LOADING_START = register(event,"item.crossbow.loading.start");
        ITEM_CROSSBOW_LOADING_MIDDLE = register(event, "item.crossbow.loading.middle");
        ITEM_CROSSBOW_LOADING_END = register(event,"item.crossbow.loading.end");
        ITEM_CROSSBOW_SHOOT = register(event, "item.crossbow.shoot");

        BLOCK_SMITHING_TABLE = register(event, "block.smithing_table");

        BLOCK_NETHERITE_BLOCK_PLACE = register(event, "block.netherite_block.place");
        BLOCK_NETHERITE_BLOCK_BREAK = register(event, "block.netherite_block.break");
        BLOCK_NETHERITE_BLOCK_STEP = register(event, "block.netherite_block.step");

        BLOCK_AMETHYST_BREAK = register(event, "block.amethyst.break");
        BLOCK_AMETHYST_STEP = register(event, "block.amethyst.step");
        BLOCK_AMETHYST_PLACE = register(event, "block.amethyst.place");
        BLOCK_AMETHYST_SHIMMER = register(event, "block.amethyst.shimmer");
        BLOCK_AMETHYST_CLUSTER_BREAK = register(event, "block.amethyst_cluster.break");
        BLOCK_AMETHYST_CLUSTER_PLACE = register(event, "block.amethyst_cluster.place");
        BLOCK_ANCIENT_DEBRIS_BREAK = register(event, "block.ancient_debris.break");
        BLOCK_AZALEA_BREAK = register(event, "block.azalea.break");
        BLOCK_AZALEA_STEP = register(event, "block.azalea.step");
        BLOCK_AZALEA_LEAVES_BREAK = register(event, "block.azalea_leaves.break");
        BLOCK_AZALEA_LEAVES_STEP = register(event, "block.azalea_leaves.step");
        BLOCK_BAMBOO_SAPLING_PLACE = register(event, "block.bamboo.sapling_place");
        BLOCK_BAMBOO_SAPLING_HIT = register(event, "block.bamboo.sapling_hit");
        BLOCK_BAMBOO_STEP = register(event, "block.bamboo.step");
        BLOCK_BAMBOO_PLACE = register(event, "block.bamboo.place");
        BLOCK_BARREL_CLOSE = register(event, "block.barrel.close");
        BLOCK_BARREL_OPEN = register(event, "block.barrel.open");
        BLOCK_BASALT_BREAK = register(event, "block.basalt.break");
        BLOCK_BASALT_STEP = register(event, "block.basalt.step");
        BLOCK_BEEHIVE_SHEAR = register(event, "block.beehive.shear");
        BLOCK_BEEHIVE_WORK = register(event, "block.beehive.work");
        BLOCK_BEEHIVE_DRIP = register(event, "block.beehive.drip");
        BLOCK_BELL_BELL_USE = register(event, "block.bell.bell_use");
        BLOCK_BELL_RESONATE = register(event, "block.bell.resonate");
        BLOCK_BIG_DRIPLEAF_BREAK = register(event, "block.big_dripleaf.break");
        BLOCK_BIG_DRIPLEAF_TILT_UP = register(event, "block.big_dripleaf.tilt_up");
        BLOCK_BIG_DRIPLEAF_STEP = register(event, "block.big_dripleaf.step");
        BLOCK_BIG_DRIPLEAF_TILT_DOWN = register(event, "block.big_dripleaf.tilt_down");
        BLOCK_BLASTFURNACE_BLASTFURNACE = register(event, "block.blastfurnace.blastfurnace");
        BLOCK_CALCITE_BREAK = register(event, "block.calcite.break");
        BLOCK_CALCITE_STEP = register(event, "block.calcite.step");
        BLOCK_CALCITE_PLACE = register(event, "block.calcite.place");
        BLOCK_CAMPFIRE_CRACKLE = register(event, "block.campfire.crackle");
        BLOCK_CANDLE_EXTINGUISH = register(event, "block.candle.extinguish");
        BLOCK_CANDLE_BREAK = register(event, "block.candle.break");
        BLOCK_CANDLE_STEP = register(event, "block.candle.step");
        BLOCK_CANDLE_AMBIENT = register(event, "block.candle.ambient");
        BLOCK_CHAIN_BREAK = register(event, "block.chain.break");
        BLOCK_CHAIN_STEP = register(event, "block.chain.step");
        BLOCK_COMPOSTER_FILL_SUCCESS = register(event, "block.composter.fill_success");
        BLOCK_COMPOSTER_READY = register(event, "block.composter.ready");
        BLOCK_COMPOSTER_FILL = register(event, "block.composter.fill");
        BLOCK_COMPOSTER_EMPTY = register(event, "block.composter.empty");
        BLOCK_CONDUIT_ATTACK = register(event, "block.conduit.attack");
        BLOCK_CONDUIT_ACTIVATE = register(event, "block.conduit.activate");
        BLOCK_CONDUIT_SHORT = register(event, "block.conduit.short");
        BLOCK_CONDUIT_AMBIENT = register(event, "block.conduit.ambient");
        BLOCK_CONDUIT_DEACTIVATE = register(event, "block.conduit.deactivate");
        BLOCK_COPPER_BREAK = register(event, "block.copper.break");
        BLOCK_COPPER_STEP = register(event, "block.copper.step");
        BLOCK_DEEPSLATE_BREAK = register(event, "block.deepslate.break");
        BLOCK_DEEPSLATE_STEP = register(event, "block.deepslate.step");
        BLOCK_DEEPSLATE_PLACE = register(event, "block.deepslate.place");
        BLOCK_DEEPSLATE_BRICKS_STEP = register(event, "block.deepslate_bricks.step");
        BLOCK_DEEPSLATE_BRICKS_PLACE = register(event, "block.deepslate_bricks.place");
        BLOCK_DRIPSTONE_BREAK = register(event, "block.dripstone.break");
        BLOCK_DRIPSTONE_STEP = register(event, "block.dripstone.step");
        BLOCK_FLETCHING_TABLE_FLETCHING_TABLE = register(event, "block.fletching_table.fletching_table");
        BLOCK_GRINDSTONE_GRINDSTONE = register(event, "block.grindstone.grindstone");
        BLOCK_HONEYBLOCK_BREAK = register(event, "block.honeyblock.break");
        BLOCK_HONEYBLOCK_SLIDE = register(event, "block.honeyblock.slide");
        BLOCK_HONEYBLOCK_STEP = register(event, "block.honeyblock.step");
        BLOCK_LANTERN_BREAK = register(event, "block.lantern.break");
        BLOCK_LANTERN_PLACE = register(event, "block.lantern.place");
        BLOCK_LODESTONE_LOCK = register(event, "block.lodestone.lock");
        BLOCK_LODESTONE_PLACE = register(event, "block.lodestone.place");
        BLOCK_MOSS_BREAK = register(event, "block.moss.break");
        BLOCK_MOSS_STEP = register(event, "block.moss.step");
        BLOCK_NETHERITE_BREAK = register(event, "block.netherite.break");
        BLOCK_NETHERITE_STEP = register(event, "block.netherite.step");
        BLOCK_NYLIUM_BREAK = register(event, "block.nylium.break");
        BLOCK_NYLIUM_STEP = register(event, "block.nylium.step");
        BLOCK_POINTED_DRIPSTONE_DRIP_LAVA = register(event, "block.pointed_dripstone.drip_lava");
        BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_CAULDRON = register(event, "block.pointed_dripstone.drip_lava_cauldron");
        BLOCK_POINTED_DRIPSTONE_LAND = register(event, "block.pointed_dripstone.land");
        BLOCK_POINTED_DRIPSTONE_DRIP_WATER = register(event, "block.pointed_dripstone.drip_water");
        BLOCK_POINTED_DRIPSTONE_DRIP_WATER_CAULDRON = register(event, "block.pointed_dripstone.drip_water_cauldron");
        BLOCK_POWDER_SNOW_BREAK = register(event, "block.powder_snow.break");
        BLOCK_POWDER_SNOW_STEP = register(event, "block.powder_snow.step");
        BLOCK_RESPAWN_ANCHOR_SET_SPAWN = register(event, "block.respawn_anchor.set_spawn");
        BLOCK_RESPAWN_ANCHOR_CHARGE = register(event, "block.respawn_anchor.charge");
        BLOCK_RESPAWN_ANCHOR_AMBIENT = register(event, "block.respawn_anchor.ambient");
        BLOCK_RESPAWN_ANCHOR_DEPLETE = register(event, "block.respawn_anchor.deplete");
        BLOCK_ROOTED_DIRT_BREAK = register(event, "block.rooted_dirt.break");
        BLOCK_ROOTED_DIRT_STEP = register(event, "block.rooted_dirt.step");
        BLOCK_SCAFFOLD_PLACE = register(event, "block.scaffold.place");
        BLOCK_SCULK_STEP = register(event, "block.sculk.step");
        BLOCK_SCULK_SENSOR_SCULK_CLICKING_STOP = register(event, "block.sculk_sensor.sculk_clicking_stop");
        BLOCK_SCULK_SENSOR_BREAK = register(event, "block.sculk_sensor.break");
        BLOCK_SCULK_SENSOR_PLACE = register(event, "block.sculk_sensor.place");
        BLOCK_SCULK_SENSOR_SCULK_CLICKING = register(event, "block.sculk_sensor.sculk_clicking");
        BLOCK_SHROOMLIGHT_BREAK = register(event, "block.shroomlight.break");
        BLOCK_SHROOMLIGHT_STEP = register(event, "block.shroomlight.step");
        BLOCK_SMOKER_SMOKER = register(event, "block.smoker.smoker");
        BLOCK_SOUL_SOIL_BREAK = register(event, "block.soul_soil.break");
        BLOCK_SOUL_SOIL_STEP = register(event, "block.soul_soil.step");
        BLOCK_SPORE_BLOSSOM_BREAK = register(event, "block.spore_blossom.break");
        BLOCK_SPORE_BLOSSOM_STEP = register(event, "block.spore_blossom.step");
        BLOCK_SWEET_BERRY_BUSH_BREAK = register(event, "block.sweet_berry_bush.break");
        BLOCK_SWEET_BERRY_BUSH_PLACE = register(event, "block.sweet_berry_bush.place");
        BLOCK_TUFF_BREAK = register(event, "block.tuff.break");
        BLOCK_TUFF_STEP = register(event, "block.tuff.step");
    }

    private static SoundEvent register(RegistryEvent.Register<SoundEvent> event, String name) {
        ResourceLocation rl = new ResourceLocation(UBM.MODID, name);
        SoundEvent sound = new SoundEvent(rl).setRegistryName(rl);
        event.getRegistry().register(sound);
        return sound;
    }

    public static class SoundTypes {
        public static SoundType NETHERITE_BLOCK;
        public static SoundType SWEET_BERRY_BUSH;
        public static SoundType AMETHYST_BLOCK;
        public static SoundType CALCITE;
        public static SoundType TUFF;
        public static SoundType DEEPSLATE;

        public static void init() {
            NETHERITE_BLOCK = new SoundType(
                    1.0F, 1.0F,
                    BLOCK_NETHERITE_BLOCK_BREAK,
                    BLOCK_NETHERITE_BLOCK_STEP,
                    BLOCK_NETHERITE_BLOCK_PLACE,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
            SWEET_BERRY_BUSH = new SoundType(
                    1, 1,
                    BLOCK_SWEET_BERRY_BUSH_BREAK,
                    BLOCK_SWEET_BERRY_BUSH_PLACE,
                    BLOCK_SWEET_BERRY_BUSH_PLACE,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
            CALCITE = new SoundType(
                    1,1,
                    BLOCK_CALCITE_BREAK,
                    BLOCK_CALCITE_STEP,
                    BLOCK_CALCITE_PLACE,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
            AMETHYST_BLOCK = new SoundType(
                    1, 1,
                    BLOCK_AMETHYST_BREAK,
                    BLOCK_AMETHYST_STEP,
                    BLOCK_AMETHYST_PLACE,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
            TUFF = new SoundType(
                    1,1,
                    BLOCK_TUFF_BREAK,
                    BLOCK_TUFF_STEP,
                    BLOCK_TUFF_BREAK,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
            DEEPSLATE = new SoundType(
                    1,1,
                    BLOCK_DEEPSLATE_BREAK,
                    BLOCK_DEEPSLATE_STEP,
                    BLOCK_DEEPSLATE_PLACE,
                    SoundEvents.BLOCK_STONE_HIT,
                    SoundEvents.BLOCK_STONE_FALL
            );
        }
    }
}
