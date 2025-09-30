package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.entities.ai.EntityAIFlyToFlower;
import de.julianweinelt.ubm.entities.ai.EntityAIHurtByTargetFlying;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBee extends EntityCreature {
    public static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityBee.class, DataSerializers.VARINT);

    private boolean hasNectar = false;
    private boolean aggressive = false;

    private BlockPos nestPos = null;

    public EntityBee(World worldIn) {
        super(worldIn);
        this.setSize(0.5f, 0.5f);
        setNoGravity(true);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("bee").getHealth() / 2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE, 0);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        return new PathNavigateFlying(this, worldIn);
    }


    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAIFlyToFlower(this, 1.0D));
        this.targetTasks.addTask(0, new EntityAIHurtByTargetFlying(this));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BEE_POLLINATE;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return ModSounds.BEE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BEE_DEATH;
    }

    @Override
    public boolean canBeLeashedTo(@Nonnull EntityPlayer player) {
        return true;
    }

    public boolean isHasNectar() {
        return hasNectar;
    }
    public void setHasNectar(boolean hasNectar) {
        this.hasNectar = hasNectar;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
    public void setAggressive(EntityPlayer toAttackIn) {
        this.aggressive = true;
        setAttackTarget(toAttackIn);
    }

    public BlockPos getNestPos() {
        return nestPos;
    }

    public void setNestPos(BlockPos nestPos) {
        this.nestPos = nestPos;
    }

    public void setState(BeeState bState) {
        this.dataManager.set(STATE, bState.ordinal());
    }

    public BeeState getBeeState() {
        return BeeState.values()[this.dataManager.get(STATE)];
    }


    public enum BeeState {
        SEARCHING_FLOWER,
        RETURN_TO_NEST
    }
}