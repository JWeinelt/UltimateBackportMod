package de.julianweinelt.ubm.blocks.interactable.smithing;

import de.julianweinelt.ubm.items.ItemArmorTrim;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ContainerSmithingTable extends Container {
    private final IInventory craftMatrix = new InventorySmithing(this, "Smithing", false, 3);
    private final IInventory craftResult = new InventoryBasic("Result", false, 1);

    public ContainerSmithingTable(InventoryPlayer playerInv) {
        this.addSlotToContainer(new SlotArmorTrim(craftMatrix, 0, 8, 48));
        this.addSlotToContainer(new Slot(craftMatrix, 1, 26, 48));
        this.addSlotToContainer(new Slot(craftMatrix, 2, 44, 48));

        this.addSlotToContainer(new SlotSmithingResult(craftResult, craftMatrix, 0, 98, 48));

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
        updateOutput();
    }

    private void updateOutput() {
        ItemStack trim = craftMatrix.getStackInSlot(0);
        ItemStack piece = craftMatrix.getStackInSlot(1);
        ItemStack material = craftMatrix.getStackInSlot(2);

        ItemStack result = ItemStack.EMPTY;

        Map<Item, String> materials = new HashMap<>();
        materials.put(Items.GOLD_INGOT, "gold");
        materials.put(Items.IRON_INGOT, "iron");
        materials.put(Items.EMERALD, "emerald");
        materials.put(Items.DYE, "lapis");
        materials.put(ModItems.NETHERITE_INGOT, "netherite");
        materials.put(Items.QUARTZ, "quartz");
        materials.put(Items.DIAMOND, "diamond");
        materials.put(ModItems.AMETHYST_SHARD, "amethyst");
        materials.put(Items.REDSTONE, "redstone");
        materials.put(ModItems.COPPER_INGOT, "copper");

        if (trim.getItem() instanceof ItemArmorTrim) {
            ItemArmorTrim t = (ItemArmorTrim) trim.getItem();
            if (piece.getItem().getRegistryName() != null
                    && piece.getItem().getRegistryName().getResourcePath().contains("diamond")
                    && t.getArmorTrim().equals("netherite_upgrade")
                    && material.getItem().equals(ModItems.NETHERITE_INGOT)) {
                Item rs;
                if (piece.getItem().equals(Items.DIAMOND_HELMET)) {
                    rs = ModItems.NETHERITE_HELMET;
                } else if (piece.getItem().equals(Items.DIAMOND_CHESTPLATE)) {
                    rs = ModItems.NETHERITE_CHESTPLATE;
                } else if (piece.getItem().equals(Items.DIAMOND_LEGGINGS)) {
                    rs = ModItems.NETHERITE_LEGGINGS;
                } else if (piece.getItem().equals(Items.DIAMOND_BOOTS)) {
                    rs = ModItems.NETHERITE_BOOTS;
                } else if (piece.getItem().equals(Items.DIAMOND_SWORD)) {
                    rs = ModItems.NETHERITE_SWORD;
                } else if (piece.getItem().equals(Items.DIAMOND_PICKAXE)) {
                    rs = ModItems.NETHERITE_PICKAXE;
                } else if (piece.getItem().equals(Items.DIAMOND_SHOVEL)) {
                    rs = ModItems.NETHERITE_SHOVEL;
                } else if (piece.getItem().equals(Items.DIAMOND_AXE)) {
                    rs = ModItems.NETHERITE_AXE;
                } else if (piece.getItem().equals(Items.DIAMOND_HOE)) {
                    rs = ModItems.NETHERITE_HOE;
                } else rs = null;

                if (rs != null) {
                    result = new ItemStack(rs);
                    result.setItemDamage(piece.getItemDamage());
                    result.setStackDisplayName(piece.getDisplayName());
                    result.setTagCompound(piece.getTagCompound());
                }
            }
        }

        if (trim.getItem() instanceof ItemArmorTrim
                && materials.containsKey(material.getItem()) && piece.getItem() instanceof ItemArmor) {
            ItemArmorTrim t = (ItemArmorTrim) trim.getItem();
            if (!t.getArmorTrim().equals("netherite_upgrade")) {
                result = new ItemStack(piece.getItem());
                if (!result.hasTagCompound()) {
                    result.setTagCompound(new NBTTagCompound());
                }
                String mat = materials.get(material.getItem());
                NBTTagCompound compound = result.getTagCompound();
                compound.setString("trim", ((ItemArmorTrim) trim.getItem()).getArmorTrim());
                compound.setString("trimMaterial", mat.toUpperCase());
                result.setTagCompound(compound);
                ItemStackHelper.setLore(result, "§5Armor Trim: " + ((ItemArmorTrim) trim.getItem()).getArmorTrim(), "§6Material: " + mat);
            }
            String mat = materials.get(material.getItem());
            NBTTagCompound compound = result.getTagCompound();
            compound.setString("ubm:trim", ((ItemArmorTrim) trim.getItem()).getArmorTrim());
            compound.setString("ubm:trimMaterial", mat.toUpperCase());
            result.setTagCompound(compound);
            ItemStackHelper.setLore(result, "§5Armor Trim: " + ((ItemArmorTrim) trim.getItem()).getArmorTrim(), "§6Material: " + mat);
        }

        /*if (result == ItemStack.EMPTY) {
            return;
        }*/
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

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            if (index == 3) {
                if (!this.mergeItemStack(stackInSlot, 4, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stackInSlot, stack);
            } else if (index >= 0 && index <= 2) {
                if (!this.mergeItemStack(stackInSlot, 4, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (stackInSlot.getItem() instanceof ItemArmorTrim) {
                    if (!this.mergeItemStack(stackInSlot, 0, 1, false)) return ItemStack.EMPTY;
                } else if (stackInSlot.getItem() instanceof ItemArmor
                        || ((stackInSlot.getItem() instanceof ItemTool
                        || stackInSlot.getItem() instanceof ItemSword) && stackInSlot.getItem().getRegistryName().getResourcePath().contains("diamond"))) {
                    if (!this.mergeItemStack(stackInSlot, 1, 2, false)) return ItemStack.EMPTY;
                } else if (isValidMaterial(stackInSlot.getItem())) {
                    if (!this.mergeItemStack(stackInSlot, 2, 3, false)) return ItemStack.EMPTY;
                } else {
                    if (index >= 4 && index <= 31) {
                        if (!this.mergeItemStack(stackInSlot, 31, this.inventorySlots.size(), false)) return ItemStack.EMPTY;
                    } else if (index >= 31 && index < this.inventorySlots.size()) {
                        if (!this.mergeItemStack(stackInSlot, 4, 31, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stackInSlot);
        }
        return stack;
    }

    private boolean isValidMaterial(Item item) {
        return item == Items.GOLD_INGOT
                || item == Items.IRON_INGOT
                || item == Items.EMERALD
                || item == Items.QUARTZ
                || item == Items.DIAMOND
                || item == Items.REDSTONE
                || item == ModItems.AMETHYST_SHARD
                || item == ModItems.NETHERITE_INGOT;
    }
}