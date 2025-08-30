package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerSmithingTable extends Container {
    private final IInventory tileEntity;

    public ContainerSmithingTable(IInventory inventory, InventoryPlayer playerInv) {
        this.tileEntity = inventory;

        this.addSlotToContainer(new SlotArmorTrim(tileEntity, 0, 44, 35));

        this.addSlotToContainer(new Slot(tileEntity, 1, 80, 35));

        this.addSlotToContainer(new Slot(tileEntity, 2, 134, 35));

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }
}
