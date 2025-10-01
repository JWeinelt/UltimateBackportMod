package de.julianweinelt.ubm.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityArmadillo extends EntityAnimal {
    public EntityArmadillo(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable ageAble) {
        EntityArmadillo child = new EntityArmadillo(this.world);
        child.setGrowingAge(-24000);
        return child;
    }
}
