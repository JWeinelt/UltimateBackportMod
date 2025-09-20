package de.julianweinelt.ubm.misc.client;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;

public class TrimItemOverride extends ItemOverrideList {
    private final IBakedModel baseModel;

    public TrimItemOverride(IBakedModel baseModel) {
        super(Collections.emptyList());
        this.baseModel = baseModel;
    }

    @Override
    public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
        if(stack.hasTagCompound()) {
            Item item = stack.getItem();
            if(item instanceof ItemArmor) {
                EntityEquipmentSlot slot = ((ItemArmor)item).armorType;
                String type = "";
                if (slot.equals(EntityEquipmentSlot.HEAD)) type = "helmet";
                if (slot.equals(EntityEquipmentSlot.CHEST)) type = "chestplate";
                if (slot.equals(EntityEquipmentSlot.LEGS)) type = "leggings";
                if (slot.equals(EntityEquipmentSlot.FEET)) type = "boots";

                NBTTagCompound nbt = stack.getTagCompound();
                if(nbt.hasKey("trim") && nbt.hasKey("trimMaterial")) {
                    String trim = nbt.getString("trim");
                    String material = nbt.getString("trimMaterial");
                    ResourceLocation texture = new ResourceLocation("ubm", "textures/trims/items" + type + "_trim.png");
                    return new BakedTrimArmorModel(baseModel, texture);
                }
            }
        }
        return baseModel;
    }
}
