package de.julianweinelt.ubm.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityTrident extends EntityThrowable {

    public EntityTrident(World worldIn) {
        super(worldIn);
    }

    public EntityTrident(World worldIn, EntityLivingBase thrower) {
        super(worldIn, thrower);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {
            result.entityHit.attackEntityFrom(
                DamageSource.causeThrownDamage(this, this.getThrower()),
                10.0F
            );
        }

        this.setDead();
    }
}