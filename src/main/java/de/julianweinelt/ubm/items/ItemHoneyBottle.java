package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemHoneyBottle extends ItemFood {
    public ItemHoneyBottle() {
        super(2, false);
        this.setRegistryName("honey_bottle");
        this.setUnlocalizedName("honey_bottle");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.DRINK;
    }



    @Override
    protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player) {
        for (PotionEffect e : player.getActivePotionEffects()) {
            if (e.getPotion().isBadEffect()) player.removePotionEffect(e.getPotion());
        }
    }
}