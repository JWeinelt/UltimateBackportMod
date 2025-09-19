package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.enchantments.ModEnchantments;
import de.julianweinelt.ubm.misc.AdvancementHelper;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemCrossbow extends Item {

    public static final ResourceLocation PULL_PROP = new ResourceLocation("pull_custom");

    public ItemCrossbow() {
        this.setMaxStackSize(1);
        this.setMaxDamage(500);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);

        this.addPropertyOverride(PULL_PROP, (stack, world, entity) -> {
            if (!(entity instanceof EntityPlayer)) return 0.0F;

            EntityPlayer player = (EntityPlayer) entity;

            if (isCharged(stack)) return 1.0F;
            if (!player.isHandActive() || player.getActiveItemStack() != stack) return 0.0F;

            int useDuration = this.getMaxItemUseDuration(stack);
            int count = player.getItemInUseCount();

            return MathHelper.clamp((float)(useDuration - count) / 20.0F, 0.0F, 1.0F);
        });
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (isCharged(stack)) {
            shoot(world, player, stack);
            AdvancementHelper.grantAdvancement(player, "ol_betsy");
            setCharged(stack, false);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ,
                    ModSounds.ITEM_CROSSBOW_LOADING_START, SoundCategory.PLAYERS, 1.0F, 1.0F);
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
        if (entity instanceof EntityPlayer) {
            boolean hasQuickCharge = EnchantmentHelper.getEnchantments(stack).containsKey(ModEnchantments.QUICK_CHARGE);
            if (hasQuickCharge) {
                int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.QUICK_CHARGE, stack);
                timeLeft -= level * 5;
            }

            int charge = this.getMaxItemUseDuration(stack) - timeLeft;
            if (charge >= 25) {
                setCharged(stack, true);
                world.playSound(null, entity.posX, entity.posY, entity.posZ,
                        ModSounds.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }


    private void shoot(World world, EntityPlayer player, ItemStack stack) {
        if (!world.isRemote) {
            EntityArrow arrow = new EntityTippedArrow(world, player);
            arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
            world.spawnEntity(arrow);
            stack.damageItem(1, player);

            world.playSound(null, player.posX, player.posY, player.posZ,
                    ModSounds.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    private boolean isCharged(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("Charged");
    }

    private void setCharged(ItemStack stack, boolean charged) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.getTagCompound().setBoolean("Charged", charged);
    }
}
