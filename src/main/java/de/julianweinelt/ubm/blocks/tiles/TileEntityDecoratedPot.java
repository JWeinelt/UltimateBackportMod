package de.julianweinelt.ubm.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import java.util.Arrays;

public class TileEntityDecoratedPot extends TileEntity {

    private final int[] sherds = new int[4]; // N,E,S,W
    // -1 = empty

    public TileEntityDecoratedPot() {
        Arrays.fill(sherds, -1);
    }

    public void setSherdForSide(EnumFacing side, int id) {
        if (side.getHorizontalIndex() >= 0)
            sherds[side.getHorizontalIndex()] = id;
        markDirty();
    }

    public int getSherdForSide(EnumFacing side) {
        return side.getHorizontalIndex() >= 0 ? sherds[side.getHorizontalIndex()] : -1;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        for (int i = 0; i < 4; i++)
            compound.setInteger("Sherd" + i, sherds[i]);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        for (int i = 0; i < 4; i++)
            sherds[i] = compound.getInteger("Sherd" + i);
    }
}
