package de.julianweinelt.ubm.entities.custom;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityCustomWolf extends EntityWolf {
    private boolean hasArmor;
    private ItemStack armorItem = ItemStack.EMPTY;

    public ItemStack getArmorItem() {
        return armorItem;
    }

    public void setArmorItem(ItemStack stack) {
        this.armorItem = stack;
    }

    private int textureVariant;

    public EntityCustomWolf(World worldIn) {
        super(worldIn);
        this.textureVariant = this.rand.nextInt(7);
    }

    public int getTextureVariant() {
        return textureVariant;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean value) {
        this.hasArmor = value;
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("TextureVariant", textureVariant);

        if (!armorItem.isEmpty()) {
            NBTTagCompound armorNBT = new NBTTagCompound();
            armorItem.writeToNBT(armorNBT);
            compound.setTag("ArmorItem", armorNBT);
        }
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        textureVariant = compound.getInteger("TextureVariant");

        if (compound.hasKey("ArmorItem")) {
            armorItem = new ItemStack(compound.getCompoundTag("ArmorItem"));
            hasArmor = !armorItem.isEmpty();
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);

        if (!heldItem.isEmpty() && heldItem.getItem() == ModItems.WOLF_ARMOR) {
            if (!this.hasArmor()) {
                this.setArmorItem(heldItem.copy());
                this.armorItem.setCount(1);
                this.setHasArmor(true);

                if (!player.capabilities.isCreativeMode) {
                    heldItem.shrink(1);
                }

                this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 1.0F);
                return true;
            }
        }

        if (this.hasArmor() && heldItem.getItem() == Items.SHEARS) {
            if (!player.inventory.addItemStackToInventory(this.getArmorItem())) {
                player.dropItem(this.getArmorItem(), false);
            }
            heldItem.shrink(1);
            this.setHasArmor(false);
            this.setArmorItem(ItemStack.EMPTY);
            this.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, 1.0F);
            return true;
        }

        return super.processInteract(player, hand);
    }


    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource damageSrc, float damageAmount) {
        if (!this.hasArmor()) return super.attackEntityFrom(damageSrc, damageAmount);

        List<DamageSource> blackList = Arrays.asList(
                DamageSource.OUT_OF_WORLD,
                DamageSource.MAGIC,
                DamageSource.WITHER,
                DamageSource.IN_WALL
        );
        if (blackList.contains(damageSrc)) return true;

        EntityLivingBase attacker = null;
        if (damageSrc.getTrueSource() instanceof EntityLivingBase) {
            attacker = (EntityLivingBase) damageSrc.getTrueSource();
        }
        if (attacker == null) return true;

        this.armorItem.damageItem((int) damageAmount, attacker);
        UBM.getLogger().info("Armor Damage: " + armorItem.getItemDamage());

        if (armorItem.getItemDamage() >= armorItem.getMaxDamage()) {
            this.setArmorItem(ItemStack.EMPTY);
            this.setHasArmor(false);
            this.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0F, 1.0F);
        }

        return false;
    }


}