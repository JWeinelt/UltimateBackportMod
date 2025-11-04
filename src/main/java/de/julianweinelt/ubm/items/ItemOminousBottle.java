package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.effects.ModEffects;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemOminousBottle extends ItemFood {
    public ItemOminousBottle() {
        super(0, 0.0F, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("ominous_bottle");
        this.setRegistryName("ominous_bottle");
        this.setMaxStackSize(64);
        this.setHasSubtypes(true);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
    }

    @Override
    protected void onFoodEaten(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityPlayer player) {
        if (!worldIn.isRemote) {
            int level = stack.getMetadata();
            player.addPotionEffect(new PotionEffect(ModEffects.BAD_OMEN, 100*60*20, level));
        }
    }

    @Override
    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int level = stack.getMetadata();
        tooltip.add("§9Bad Omen " + getLevelString(level) + " (01:40:00)");
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < 5; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    @Nonnull
    public EnumRarity getRarity(@Nonnull ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    private String getLevelString(int level) {
        switch (level) {
            case 1: return "II";
            case 2: return "III";
            case 3: return "IV";
            case 4: return "V";
            default: return "";
        }
    }
}