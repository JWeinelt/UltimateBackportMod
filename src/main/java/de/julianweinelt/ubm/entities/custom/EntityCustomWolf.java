package de.julianweinelt.ubm.entities.custom;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityCustomWolf extends EntityWolf {
    private int textureVariant;

    public EntityCustomWolf(World worldIn) {
        super(worldIn);
        this.textureVariant = this.rand.nextInt(7);
    }

    public int getTextureVariant() {
        return textureVariant;
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("TextureVariant", textureVariant);
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        textureVariant = compound.getInteger("TextureVariant");
    }
}