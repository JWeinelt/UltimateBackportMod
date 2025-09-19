package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotSmithingResult extends Slot {
    private final IInventory craftMatrix;

    public SlotSmithingResult(IInventory resultInventory, IInventory craftMatrix, int index, int x, int y) {
        super(resultInventory, index, x, y);
        this.craftMatrix = craftMatrix;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack onTake(@Nonnull EntityPlayer player, @Nonnull ItemStack stack) {
        craftMatrix.decrStackSize(0, 1);
        craftMatrix.decrStackSize(1, 1);
        craftMatrix.decrStackSize(2, 1);
        return super.onTake(player, stack);
    }
}
