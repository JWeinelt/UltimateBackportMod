package de.julianweinelt.ubm.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBeeNest extends TileEntity {
    private int bees = 0;
    private int honeyLevel = 0;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("Bees", bees);
        compound.setInteger("HoneyLevel", honeyLevel);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        bees = compound.getInteger("Bees");
        honeyLevel = compound.getInteger("HoneyLevel");
    }

    public void setBees(int bees) {
        this.bees = bees;
        markDirty();
    }

    public int getBees() {
        return bees;
    }

    public void setHoneyLevel(int honeyLevel) {
        this.honeyLevel = honeyLevel;
        markDirty();
    }

    public int getHoneyLevel() {
        return honeyLevel;
    }
}
