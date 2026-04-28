package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.blocks.interactable.BlockBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityBlastFurnace extends TileEntity implements ITickable, IInventory {
    private NonNullList<ItemStack> inventory = NonNullList.withSize(3, ItemStack.EMPTY);
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_OUTPUT = 2;

    private int burnTime;
    private int currentBurnTime;
    private int cookTime;
    private int totalCookTime;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, inventory);
        compound.setInteger("BurnTime", burnTime);
        compound.setInteger("CookTime", cookTime);
        compound.setInteger("TotalCookTime", totalCookTime);
        compound.setInteger("CurrentBurnTime", currentBurnTime);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        inventory = NonNullList.withSize(3, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, inventory);
        burnTime = compound.getInteger("BurnTime");
        cookTime = compound.getInteger("CookTime");
        totalCookTime = compound.getInteger("TotalCookTime");
        currentBurnTime = compound.getInteger("CurrentBurnTime");
    }

    @Override
    public void update() {
        boolean wasBurning = isBurning();
        boolean dirty = false;

        if (isBurning()) burnTime--;

        if (!world.isRemote) {
            ItemStack fuel = inventory.get(SLOT_FUEL);
            ItemStack input = inventory.get(SLOT_INPUT);

            if (isBurning() || !fuel.isEmpty() && !input.isEmpty()) {
                ItemStack result = getSmeltingResult(input);

                if (!isBurning() && canSmelt(result)) {
                    burnTime = getItemBurnTime(fuel);
                    currentBurnTime = burnTime;
                    if (isBurning()) {
                        dirty = true;
                        if (fuel.getItem().hasContainerItem(fuel))
                            inventory.set(SLOT_FUEL, new ItemStack(fuel.getItem().getContainerItem()));
                        else {
                            fuel.shrink(1);
                            if (fuel.isEmpty()) inventory.set(SLOT_FUEL, ItemStack.EMPTY);
                        }
                    }
                }

                if (isBurning() && canSmelt(result)) {
                    cookTime++;
                    if (cookTime >= totalCookTime) {
                        cookTime = 0;
                        totalCookTime = getCookTime();
                        smeltItem(result);
                        dirty = true;
                    }
                } else {
                    cookTime = 0;
                }
            } else if (!isBurning() && cookTime > 0) {
                cookTime = MathHelper.clamp(cookTime - 2, 0, totalCookTime);
            }

            if (wasBurning != isBurning()) {
                dirty = true;
                BlockBlastFurnace.setState(isBurning(), world, pos);
            }
        }

        if (dirty) markDirty();
    }

    public boolean isBurning() {
        return burnTime > 0;
    }

    private int getItemBurnTime(ItemStack stack) {
        return TileEntityFurnace.getItemBurnTime(stack); // Vanilla-Brennwerte wiederverwenden
    }

    private ItemStack getSmeltingResult(ItemStack input) {
        return FurnaceRecipes.instance().getSmeltingResult(input);
    }

    private boolean canSmelt(ItemStack result) {
        if (inventory.get(SLOT_INPUT).isEmpty() || result.isEmpty()) return false;
        ItemStack output = inventory.get(SLOT_OUTPUT);
        if (output.isEmpty()) return true;
        if (!output.isItemEqual(result)) return false;
        int combined = output.getCount() + result.getCount();
        return combined <= getInventoryStackLimit() && combined <= output.getMaxStackSize();
    }

    private void smeltItem(ItemStack result) {
        ItemStack output = inventory.get(SLOT_OUTPUT);
        if (output.isEmpty()) {
            inventory.set(SLOT_OUTPUT, result.copy());
        } else if (output.isItemEqual(result)) {
            output.grow(result.getCount());
        }
        inventory.get(SLOT_INPUT).shrink(1);
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getCurrentBurnTime() {
        return currentBurnTime;
    }

    @Override
    public int getSizeInventory() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        return inventory.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return inventory.get(i);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(inventory, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(inventory, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        boolean needsUpdate = stack.isItemEqual(inventory.get(index));
        inventory.set(index, stack);
        if (stack.getCount() > getInventoryStackLimit())
            stack.setCount(getInventoryStackLimit());
        if (index == SLOT_INPUT && !needsUpdate)
            totalCookTime = calcCookTime();
        markDirty();
    }

    @Override
    public String getName() {
        return "container.blast_furnace";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer p) {
        return world.getTileEntity(pos) == this &&
                p.getDistanceSq(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5) <= 64;
    }

    @Override
    public void openInventory(EntityPlayer p) {
    }

    @Override
    public void closeInventory(EntityPlayer p) {
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == SLOT_OUTPUT) return false;
        if (index == SLOT_FUEL) return TileEntityFurnace.isItemFuel(stack);
        return true;
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return burnTime;
            case 1:
                return currentBurnTime;
            case 2:
                return cookTime;
            case 3:
                return totalCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                burnTime = value;
                break;
            case 1:
                currentBurnTime = value;
                break;
            case 2:
                cookTime = value;
                break;
            case 3:
                totalCookTime = value;
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation(getName());
    }

    public void setBurnTime(int v) {
        burnTime = v;
    }

    public void setCurrentBurnTime(int v) {
        currentBurnTime = v;
    }

    public void setCookTime(int v) {
        cookTime = v;
    }

    public void setTotalCookTime(int v) {
        totalCookTime = v;
    }

    private int calcCookTime() {
        return 100;
    }

    public int getCookTime() {
        return cookTime;
    }

    public int getTotalCookTime() {
        return totalCookTime;
    }

    public int getBurnTimeScaled(int pixels) {
        if (currentBurnTime == 0) return 0;
        return burnTime * pixels / currentBurnTime;
    }

    public int getCookProgressScaled(int pixels) {
        if (totalCookTime == 0) return 0;
        return cookTime * pixels / totalCookTime;
    }
}
