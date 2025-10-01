package de.julianweinelt.ubm.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityAllay extends EntityFlying {
    public EntityAllay(World worldIn) {
        super(worldIn);
    }
}
