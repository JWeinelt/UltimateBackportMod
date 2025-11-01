package de.julianweinelt.ubm.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSpyglass extends Item {

    public ItemSpyglass() {
        this.setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, EntityPlayer player, @Nonnull EnumHand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public int getMaxItemUseDuration(@Nonnull ItemStack stack) {
        return 72000;
    }

    @Override
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.NONE;
    }
}