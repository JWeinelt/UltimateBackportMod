package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModYamlConfig;
import de.julianweinelt.ubm.entities.ai.EntityAIRamTarget;
import de.julianweinelt.ubm.entities.ai.EntityAISpontaneousJumpGoat;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityGoat extends EntityAnimal {

    private int jumpCooldown = 0;
    private int ramCooldown = 0;
    public boolean screaming = false;

    public Random rand = new Random();

    public EntityGoat(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.3F);
        this.experienceValue = 2;
        screaming = rand.nextBoolean();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.2D, Items.WHEAT, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.tasks.addTask(8, new EntityAIRamTarget(this));
        this.tasks.addTask(9, new EntityAISpontaneousJumpGoat(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(
                ModYamlConfig.entityHealth("goat") / 2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.WHEAT;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new EntityGoat(this.world);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (jumpCooldown > 0) jumpCooldown--;
        if (ramCooldown > 0) ramCooldown--;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (screaming && rand.nextBoolean()) return ModSounds.GOAT_SCREAM;
        else return ModSounds.GOAT_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (screaming) return ModSounds.GOAT_SCREAM_HURT;
        return ModSounds.GOAT_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        if (screaming) return ModSounds.GOAT_SCREAM_DEATH;
        return ModSounds.GOAT_DEATH;
    }



    public boolean canJump() {
        return jumpCooldown <= 0;
    }

    public void resetJumpCooldown() {
        jumpCooldown = 60 + rand.nextInt(100);
    }

    public boolean canRam() {
        return ramCooldown <= 0;
    }

    public void resetRamCooldown() {
        ramCooldown = 100 + rand.nextInt(200);
    }
}
