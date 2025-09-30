package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.entities.ai.EntityAIHoglinFleeBlock;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityHoglin extends EntityAnimal {
    public EntityHoglin(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable ageAble) {
        EntityHoglin child = new EntityHoglin(this.world);
        child.setGrowingAge(-24000);
        return child;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHoglinFleeBlock(this, 1, 10));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPigZombie.class, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("hoglin").getHealth() / 2.0D);
    }
}
