package de.julianweinelt.ubm.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.INpc;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class EntityWanderingTrader extends EntityCreature implements INpc {
    public EntityWanderingTrader(World worldIn) {
        super(worldIn);
    }

}
