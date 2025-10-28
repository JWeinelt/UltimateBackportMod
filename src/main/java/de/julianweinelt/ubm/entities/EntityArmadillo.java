package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityArmadillo extends EntityAnimal {
    public EntityArmadillo(World worldIn) {
        super(worldIn);
        this.setSize(0.7F, 0.65F);
    }

    @Nullable
    @Override
    public EntityAgeable createChild(@Nonnull EntityAgeable ageAble) {
        EntityArmadillo child = new EntityArmadillo(this.world);
        child.setGrowingAge(-24000);
        return child;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.14);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    @Override
    public boolean canBeLeashedTo(@Nonnull EntityPlayer player) {
        return true;
    }

    @Nullable
    @Override
    protected Item getDropItem() {
        return Items.SPIDER_EYE; //TODO: Add armadillo scute as drop
    }


}
