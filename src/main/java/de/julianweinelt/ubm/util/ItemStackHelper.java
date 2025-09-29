package de.julianweinelt.ubm.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ItemStackHelper {
    public static ItemStack setLore(ItemStack inputIn, String... loreIn) {
        if (!inputIn.hasTagCompound()) {
            inputIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound display = inputIn.getTagCompound().getCompoundTag("display");
        if (!inputIn.getTagCompound().hasKey("display")) {
            inputIn.getTagCompound().setTag("display", display);
        }

        NBTTagList loreList = new NBTTagList();
        for (String s :  loreIn) {
            loreList.appendTag(new NBTTagString(s));
        }

        display.setTag("Lore", loreList);
        inputIn.getTagCompound().setTag("display", display);
        return inputIn;
    }
}