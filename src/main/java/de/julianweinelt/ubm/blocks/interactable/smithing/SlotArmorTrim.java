package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotArmorTrim extends Slot {
    public SlotArmorTrim(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof IArmorTrim;
    }
}