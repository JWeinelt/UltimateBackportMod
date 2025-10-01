package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPillager extends EntityMob implements IRangedAttackMob {

    public EntityPillager(World world) {
        super(world);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND,
                new ItemStack(ModItems.CROSSBOW));
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ModItems.getOminousBanner());
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("pillager").getHealth() / 2.0D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 40, 15.0F));
        this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityVillager.class, true));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityArrow arrow = new EntityTippedArrow(this.world, this);
        double dx = target.posX - this.posX;
        double dy = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - arrow.posY;
        double dz = target.posZ - this.posZ;
        double dist = MathHelper.sqrt(dx * dx + dz * dz);

        arrow.shoot(dx, dy + dist * 0.2D, dz, 1.6F, 14 - this.world.getDifficulty().getDifficultyId() * 4);
        this.world.spawnEntity(arrow);

        this.playSound(ModSounds.ITEM_CROSSBOW_SHOOT, 1.0F, 1.0F);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {}
}
