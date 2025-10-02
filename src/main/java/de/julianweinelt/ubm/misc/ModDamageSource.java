package de.julianweinelt.ubm.misc;

import net.minecraft.util.DamageSource;

public class ModDamageSource extends DamageSource {
    public ModDamageSource(String damageType) {
        super(damageType);
    }

    public static DamageSource frostbite() {
        return new ModDamageSource("frostbite").setDamageBypassesArmor();
    }
}
