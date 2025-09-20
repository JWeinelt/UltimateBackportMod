package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.UBM;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityCampfire extends TileEntity implements ITickable {

    public static final int SLOTS = 4;
    private final ItemStack[] slots = new ItemStack[SLOTS];
    private final ItemStack[] cookedSlots = new ItemStack[SLOTS];

    private final int[] cookTimers = new int[SLOTS];
    private static final int COOK_TIME = 200;

    public boolean addItem(ItemStack stack, ItemStack cooked) {
        for (int i = 0; i < SLOTS; i++) {
            if (slots[i] == null) {
                slots[i] = stack.copy();
                cookedSlots[i] = cooked.copy();
                markDirty();
                return true;
            }
        }
        return false;
    }

    public ItemStack removeItem(int slot) {
        ItemStack stack = slots[slot];
        slots[slot] = null;
        markDirty();
        return stack;
    }

    public ItemStack getItem(int slot) {
        return slots[slot];
    }

    public ItemStack[] getSlots() {
        return slots;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            for (int i = 0; i < SLOTS; i++) {
                if (slots[i] != null) {
                    cookTimers[i]++;
                    if (cookTimers[i] >= COOK_TIME) {
                        ItemStack cooked = cookedSlots[i].copy();
                        world.spawnEntity(new EntityItem(world, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, cooked));
                        slots[i] = null;
                        for (ItemStack s : slots) {
                            UBM.getLogger().info(s == null);
                        }
                        cookTimers[i] = 0;
                        markDirty();
                    }
                }
            }
        }
    }
}