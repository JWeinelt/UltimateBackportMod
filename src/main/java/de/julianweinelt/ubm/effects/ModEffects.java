package de.julianweinelt.ubm.effects;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModEffects {
    public static ModEffectTrialOmen TRIAL_OMEN = new ModEffectTrialOmen();
    public static ModEffectBadOmen BAD_OMEN = new ModEffectBadOmen();
    public static ModEffectRaidOmen RAID_OMEN = new ModEffectRaidOmen();
    public static ModEffectOozing OOZING = new ModEffectOozing();
    public static ModEffectHeroOfTheVillage HERO_OF_THE_VILLAGE = new ModEffectHeroOfTheVillage();
    public static ModEffectInfested INFESTED = new ModEffectInfested();

    public static void init() {
        TRIAL_OMEN.setRegistryName("trial_omen").setPotionName("effect.ubm.trial_omen");
        BAD_OMEN.setRegistryName("bad_omen").setPotionName("effect.ubm.bad_omen");
        RAID_OMEN.setRegistryName("raid_omen").setPotionName("effect.ubm.raid_omen");
        OOZING.setRegistryName("oozing").setPotionName("effect.ubm.oozing");
        HERO_OF_THE_VILLAGE.setRegistryName("hero_of_the_village")
                .setPotionName("effect.ubm.hero_of_the_village");
        INFESTED.setRegistryName("infested").setPotionName("effect.ubm.infested");

        ForgeRegistries.POTIONS.register(TRIAL_OMEN);
        ForgeRegistries.POTIONS.register(BAD_OMEN);
        ForgeRegistries.POTIONS.register(RAID_OMEN);
        ForgeRegistries.POTIONS.register(OOZING);
        ForgeRegistries.POTIONS.register(HERO_OF_THE_VILLAGE);
        ForgeRegistries.POTIONS.register(INFESTED);
    }
}
