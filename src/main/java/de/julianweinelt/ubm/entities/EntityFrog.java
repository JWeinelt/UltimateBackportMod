package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.entities.ai.EntityAIEatSlime;
import de.julianweinelt.ubm.entities.ai.EntityAISpontaneousJump;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import java.util.Random;


public class EntityFrog extends EntityAnimal {
    public static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntityFrog.class, DataSerializers.VARINT);

    private FrogType frogType;
    private int jumpCooldown = 0;
    public Random rand = new Random();

    public EntityFrog(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.experienceValue = 1;
        updateType(worldIn);
    }

    private void updateType(World world) {
        Biome biome = world.getBiome(this.getPosition());

        if (biome.getTempCategory().equals(Biome.TempCategory.COLD)) {
            setType(FrogType.COOL);
            System.out.println("Frog cool");
        }
        else if (biome.getTempCategory().equals(Biome.TempCategory.WARM))
            setType(FrogType.WARM);
        else
            setType(FrogType.TEMPERATE);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE, 0);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(2, new EntityAITempt(this, 1.2D, Items.SLIME_BALL, false));
        this.tasks.addTask(3, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(4, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.tasks.addTask(7, new EntityAISpontaneousJump(this));
        this.tasks.addTask(8, new EntityAIEatSlime(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
    }

    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.SLIME_BALL;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new EntityFrog(this.world);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (jumpCooldown > 0) jumpCooldown--;
    }

    public boolean canJump() {
        return jumpCooldown <= 0;
    }

    public void resetJumpCooldown() {
        jumpCooldown = 40 + rand.nextInt(40);
    }


    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.FROG_IDLE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.FROG_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.FROG_DEATH;
    }

    public void setType(FrogType type) {
        this.dataManager.set(STATE, type.ordinal());
    }

    public FrogType getFrogType() {
        return FrogType.values()[this.dataManager.get(STATE)];
    }

    public enum FrogType {
        TEMPERATE,
        WARM,
        COOL
    }
}