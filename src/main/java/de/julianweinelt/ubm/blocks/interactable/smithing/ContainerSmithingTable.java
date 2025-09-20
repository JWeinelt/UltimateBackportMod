package de.julianweinelt.ubm.blocks.interactable.smithing;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.items.ItemArmorTrim;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.util.ItemStackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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
        UBM.getLogger().info("Matrix");
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

        if (trim.getItem() instanceof ItemArmorTrim && materials.containsKey(material.getItem()) && piece.getItem() instanceof ItemArmor) {
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