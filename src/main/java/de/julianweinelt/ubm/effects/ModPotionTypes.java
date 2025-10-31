package de.julianweinelt.ubm.effects;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotionTypes {
    public static PotionType OOZING;
    public static PotionType INFESTED;

    public static void init() {
        OOZING = new PotionType("oozing", new PotionEffect(ModEffects.OOZING, 3600))
                .setRegistryName("oozing");
        ForgeRegistries.POTION_TYPES.register(OOZING);
        INFESTED = new PotionType("infested", new PotionEffect(ModEffects.INFESTED, 3600))
                .setRegistryName("infested");
        ForgeRegistries.POTION_TYPES.register(INFESTED);
    }
}
