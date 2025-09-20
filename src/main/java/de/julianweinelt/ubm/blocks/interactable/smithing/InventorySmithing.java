package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;

public class InventorySmithing extends InventoryBasic {
    private final Container eventHandler;

    public InventorySmithing(Container eventHandler, String title, boolean customName, int slotCount) {
        super(title, customName, slotCount);
        this.eventHandler = eventHandler;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        eventHandler.onCraftMatrixChanged(this);
    }
}
