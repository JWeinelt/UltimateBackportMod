package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.entities.ai.EntityAIPhantomAttack;
import de.julianweinelt.ubm.entities.ai.EntityAIPhantomOrbit;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPhantom extends EntityMob implements IMob {
    public EntityPhantom(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 0.5F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("phantom").getHealth() / 2.0D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIPhantomAttack(this));
        this.tasks.addTask(2, new EntityAIPhantomOrbit(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public boolean getCanSpawnHere() {
        if (this.world.isDaytime()) return false;
        BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
        if (!this.world.canSeeSky(pos)) return false;
        return super.getCanSpawnHere();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.world.isDaytime() && this.world.canSeeSky(new BlockPos(this))) {
            this.setFire(8);
        }
    }
}