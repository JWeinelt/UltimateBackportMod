package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.entities.EntityFrog;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

import java.util.List;

public class EntityAIEatSlime extends EntityAIBase {
    private final EntityFrog frog;
    private EntityLivingBase target;

    public EntityAIEatSlime(EntityFrog frog) {
        this.frog = frog;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        List<EntityLivingBase> list = frog.world.getEntitiesWithinAABB(EntityLivingBase.class,
            frog.getEntityBoundingBox().grow(3.0D), e ->
                (e instanceof EntitySlime || e.getClass().getSimpleName().equalsIgnoreCase("EntityMagmaCube")) &&
                e.isEntityAlive() &&
                e.width <= 0.6F);

        if (!list.isEmpty()) {
            target = list.get(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return target != null && target.isEntityAlive() && frog.getDistanceSq(target) > 3.0D;
    }

    @Override
    public void startExecuting() {
        frog.getNavigator().tryMoveToEntityLiving(target, 1.0D);
    }

    @Override
    public void updateTask() {
        if (frog.getDistance(target) < 1.0D) {
            if (target.getClass().getSimpleName().equalsIgnoreCase("EntitySlime")) {
                target.setDead();
                frog.playSound(ModSounds.FROG_TONGUE, 1, 1);
                frog.world.playSound(null, frog.getPosition(), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                ItemStack stack = new ItemStack(Items.SLIME_BALL, 3);
                EntityItem entityItem = new EntityItem(frog.world, target.posX, target.posY, target.posZ, stack);
                frog.getEntityWorld().spawnEntity(entityItem);
                target = null;
            } else if (target.getClass().getSimpleName().equalsIgnoreCase("EntityMagmaCube")) {
                target.setDead();
                frog.playSound(ModSounds.FROG_TONGUE, 1, 1);
                frog.world.playSound(null, frog.getPosition(), SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                ItemStack stack = new ItemStack(ModBlocks.PEARLESCENT_FROGLIGHT, 1);
                EntityItem entityItem = new EntityItem(frog.world, target.posX, target.posY, target.posZ, stack);
                frog.getEntityWorld().spawnEntity(entityItem);
                target = null;
            }
        }
    }
}
