package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityFox extends EntityAnimal {
    public EntityFox(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.7F);
        this.experienceValue = 1;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new EntityAIWander(this, 0.5F));
        this.tasks.addTask(0, new EntityAIPanic(this, 0.5F));
        this.tasks.addTask(0, new EntityAIFollowParent(this, 0.5F));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
    }

    @Override
    public boolean canBeLeashedTo(@Nonnull EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.SWEET_BERRY;
    }

    @Override
    public EntityAgeable createChild(@Nullable EntityAgeable ageable) {
        EntityFox child = new EntityFox(this.world);
        child.setGrowingAge(-24000);
        return child;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }
}
