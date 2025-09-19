package de.julianweinelt.ubm.blocks.interactable.smithing;

import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class ContainerSmithingTable extends Container {
    private final IInventory craftMatrix = new InventoryBasic("Smithing", false, 3);
    private final IInventory craftResult = new InventoryBasic("Result", false, 1);

    public ContainerSmithingTable(InventoryPlayer playerInv) {
        this.addSlotToContainer(new SlotArmorTrim(craftMatrix, 0, 8, 48));
        this.addSlotToContainer(new Slot(craftMatrix, 1, 26, 48));
        this.addSlotToContainer(new Slot(craftMatrix, 2, 44, 48));

        this.addSlotToContainer(new SlotSmithingResult(craftResult, craftMatrix, 1, 98, 48));

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
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(@Nonnull IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        System.out.println("Matrix");
        updateOutput();
    }

    private void updateOutput() {
        ItemStack trim = craftMatrix.getStackInSlot(0);
        ItemStack piece = craftMatrix.getStackInSlot(1);
        ItemStack material = craftMatrix.getStackInSlot(2);

        ItemStack result = ItemStack.EMPTY;

        if (!trim.isEmpty() && !piece.isEmpty() && !material.isEmpty()) {
            if (trim.getItem() == ModItems.TRIM_BOLT && material.getItem() == Items.GOLD_INGOT && piece.getItem() == ModItems.NETHERITE_CHESTPLATE) {
                result = new ItemStack(ModItems.NETHERITE_CHESTPLATE);
                if (!result.hasTagCompound()) {
                    result.setTagCompound(new NBTTagCompound());
                }
                result.getTagCompound().setString("trim", "bolt");
                result.getTagCompound().setString("trimMaterial", "GOLD");
                result = ItemStackHelper.setLore(result, "§5Armor Trim: Bolt", "§6Material: GOLD");
            }
        }

        if (result == ItemStack.EMPTY) return;
        craftResult.setInventorySlotContents(0, result);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        ItemStack trim = craftMatrix.getStackInSlot(0);
        ItemStack piece = craftMatrix.getStackInSlot(1);
        ItemStack material = craftMatrix.getStackInSlot(2);

        super.onContainerClosed(playerIn);
        if (!playerIn.inventory.addItemStackToInventory(material)) {
            playerIn.dropItem(material, false);
        }
        if (!playerIn.inventory.addItemStackToInventory(piece)) {
            playerIn.dropItem(piece, false);
        }
        if (!playerIn.inventory.addItemStackToInventory(trim)) {
            playerIn.dropItem(trim, false);
        }
    }
}