package de.julianweinelt.ubm.effects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public class ModEffectOozing extends Potion {
    protected ModEffectOozing() {
        super(true, 0x99FFA3);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
    }


}
