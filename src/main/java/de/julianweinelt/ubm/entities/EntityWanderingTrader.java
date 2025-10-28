package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.entities.ai.EntityAIDolphinSwimAround;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.World;

public class EntityWanderingTrader extends EntityCreature implements INpc {
    public EntityWanderingTrader(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAIWander(this, 0.7));
        this.tasks.addTask(1, new EntityAILookIdle(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.5F));
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
