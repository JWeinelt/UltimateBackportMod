package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.text.ITextComponent;

public class TileEntityModSign extends TileEntitySign {
    private ITextComponent[] backText = new ITextComponent[4];
    private boolean editingBack = false;


    public ITextComponent getBackText(int index) {
        return backText[index];
    }

    public ITextComponent[] getBackText() {
        return backText;
    }

    public void setBackText(int index, ITextComponent text) {
        backText[index] = text;
        markDirty();
    }

    public void setBackText(ITextComponent[] text) {
        backText = text;
        markDirty();
    }

    public boolean isEditingBack() {
        return editingBack;
    }

    public void setEditingBack(boolean editingBack) {
        this.editingBack = editingBack;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        for (int i = 0; i < 4; i++) {
            if (backText[i] != null) {
                compound.setString("Back" + i,
                        ITextComponent.Serializer.componentToJson(backText[i]));
            }
        }

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        for (int i = 0; i < 4; i++) {
            if (compound.hasKey("Back" + i)) {
                backText[i] =
                        ITextComponent.Serializer.jsonToComponent(compound.getString("Back" + i));
            }
        }
    }
}
