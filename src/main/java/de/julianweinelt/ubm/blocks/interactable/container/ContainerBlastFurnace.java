package de.julianweinelt.ubm.blocks.interactable.container;

import de.julianweinelt.ubm.blocks.tiles.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBlastFurnace extends Container {

    private final TileEntityBlastFurnace te;

    private int lastBurnTime;
    private int lastCurrentBurnTime;
    private int lastCookTime;
    private int lastTotalCookTime;

    public ContainerBlastFurnace(InventoryPlayer playerInv, TileEntityBlastFurnace te) {
        this.te = te;

        addSlotToContainer(new Slot(te, TileEntityBlastFurnace.SLOT_INPUT, 56, 17));
        addSlotToContainer(new SlotFurnaceFuel(te, TileEntityBlastFurnace.SLOT_FUEL, 56, 53));
        addSlotToContainer(new SlotFurnaceOutput(playerInv.player, te,
                TileEntityBlastFurnace.SLOT_OUTPUT, 116, 35));

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlotToContainer(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));

        for (int col = 0; col < 9; col++)
            addSlotToContainer(new Slot(playerInv, col, 8 + col * 18, 142));
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (IContainerListener listener : listeners) {
            if (lastBurnTime != te.getBurnTime()) listener.sendWindowProperty(this, 0, te.getBurnTime());
            if (lastCurrentBurnTime != te.getCurrentBurnTime())
                listener.sendWindowProperty(this, 1, te.getCurrentBurnTime());
            if (lastCookTime != te.getCookTime()) listener.sendWindowProperty(this, 2, te.getCookTime());
            if (lastTotalCookTime != te.getTotalCookTime()) listener.sendWindowProperty(this, 3, te.getTotalCookTime());
        }
        lastBurnTime = te.getBurnTime();
        lastCurrentBurnTime = te.getCurrentBurnTime();
        lastCookTime = te.getCookTime();
        lastTotalCookTime = te.getTotalCookTime();
    }

    @Override
    public void updateProgressBar(int id, int value) {
        switch (id) {
            case 0:
                te.setBurnTime(value);
                break;
            case 1:
                te.setCurrentBurnTime(value);
                break;
            case 2:
                te.setCookTime(value);
                break;
            case 3:
                te.setTotalCookTime(value);
                break;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return te.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot == null || !slot.getHasStack()) return result;

        ItemStack stack = slot.getStack();
        result = stack.copy();

        if (index < 3) {
            if (!mergeItemStack(stack, 3, 39, true)) return ItemStack.EMPTY;
        } else {
            if (TileEntityFurnace.isItemFuel(stack)) {
                if (!mergeItemStack(stack, 1, 2, false)) return ItemStack.EMPTY;
            } else if (!mergeItemStack(stack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) slot.putStack(ItemStack.EMPTY);
        else slot.onSlotChanged();

        return result;
    }

    public boolean isBurning() {
        return lastBurnTime > 0;
    }

    public int getBurnTimeScaled(int pixels) {
        if (lastCurrentBurnTime == 0) return 0;
        return lastBurnTime * pixels / lastCurrentBurnTime;
    }

    public int getCookProgressScaled(int pixels) {
        if (lastTotalCookTime == 0) return 0;
        return lastCookTime * pixels / lastTotalCookTime;
    }
}