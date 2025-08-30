package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class EntityFox extends EntityAnimal {

    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityFox.class, DataSerializers.VARINT);

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
            new ResourceLocation("ubm:textures/entity/fox/fox.png"),
            new ResourceLocation("ubm:textures/entity/fox/snow_fox.png")
    };

    public EntityFox(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 0.7F);
        this.experienceValue = 1;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(VARIANT, 0);
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
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        super.onInitialSpawn(difficulty, livingdata);

        int variant = 0;
        if (getEntityWorld().getBiome(getPosition()).isSnowyBiome()) variant = 1;
        this.setVariant(variant);
        return livingdata;
    }

    public int getVariant() {
        return this.dataManager.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.dataManager.set(VARIANT, variant);
    }

    public ResourceLocation getTexture() {
        int variant = this.getVariant();
        if (variant < 0 || variant >= TEXTURES.length) {
            variant = 0;
        }
        return TEXTURES[variant];
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getVariant());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("Variant")) {
            this.setVariant(compound.getInteger("Variant"));
        }
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
