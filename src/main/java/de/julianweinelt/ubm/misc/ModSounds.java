package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
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


    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
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
    }

    private static SoundEvent register(RegistryEvent.Register<SoundEvent> event, String name) {
        ResourceLocation rl = new ResourceLocation(UBM.MODID, name);
        SoundEvent sound = new SoundEvent(rl).setRegistryName(rl);
        event.getRegistry().register(sound);
        return sound;
    }
}
