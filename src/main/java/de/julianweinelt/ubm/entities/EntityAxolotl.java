package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.entities.ai.EntityAISwimAround;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityAxolotl extends EntityWaterMob {

    private static final DataParameter<Integer> VARIANT = EntityDataManager.createKey(EntityAxolotl.class, DataSerializers.VARINT);

    // Deine Texturen – hier als Beispiel
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
            new ResourceLocation("ubm:textures/entity/axolotl/axolotl_lucy.png"),
            new ResourceLocation("ubm:textures/entity/axolotl/axolotl_wild.png"),
            new ResourceLocation("ubm:textures/entity/axolotl/axolotl_gold.png"),
            new ResourceLocation("ubm:textures/entity/axolotl/axolotl_cyan.png"),
            new ResourceLocation("ubm:textures/entity/axolotl/axolotl_blue.png")
    };

    public EntityAxolotl(World worldIn) {
        super(worldIn);
        this.setSize(0.7F, 0.5F);
    }


    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimAround(this, 1.0D));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(VARIANT, 0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        super.onInitialSpawn(difficulty, livingdata);

        int variant = new Random().nextInt(TEXTURES.length);
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
    public boolean getCanSpawnHere() {
        return this.isInWater() && super.getCanSpawnHere();
    }
}
