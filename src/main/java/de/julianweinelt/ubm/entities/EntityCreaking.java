package de.julianweinelt.ubm.entities;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityCreaking extends EntityMob {
    public EntityCreaking(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.5);
    }

    @Override
    protected void damageEntity(@Nonnull DamageSource damageSrc, float damageAmount) {

    }
}