package de.julianweinelt.ubm.trims;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LayerArmorTrim extends LayerBipedArmor {
    public static final Map<String, Color[]> MATERIAL_TO_PALETTE = new HashMap<>();


    private final RenderLivingBase<?> renderer;

    public LayerArmorTrim(RenderLivingBase<?> renderer) {
        super(renderer);
        this.renderer = renderer;
    }

    @Override
    protected void initArmor() {
        this.modelLeggings = new ModelBiped(0.5F);
        this.modelArmor = new ModelBiped(1.0F);
    }

    @Override
    public void doRenderLayer(@Nonnull EntityLivingBase entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() != EntityEquipmentSlot.Type.ARMOR) continue;

            ItemStack stack = entity.getItemStackFromSlot(slot);
            if (stack.isEmpty() || !stack.hasTagCompound()) continue;

            NBTTagCompound trim = stack.getTagCompound();
            if (trim == null) continue;

            String pattern = trim.getString("trim").isEmpty() ? "tide" : trim.getString("trim");
            String material = trim.getString("trimMaterial").isEmpty() ? "emerald" : trim.getString("trimMaterial");

            String overlayPath = (slot == EntityEquipmentSlot.LEGS)
                    ? "textures/equipment/trims/entity/humanoid_leggings/" + pattern + ".png"
                    : "textures/equipment/trims/entity/humanoid/" + pattern + ".png";
            Color[] palette = MATERIAL_TO_PALETTE.getOrDefault(material.toLowerCase(), null);
            if (palette == null) continue;

            ModelBiped model = slot == EntityEquipmentSlot.LEGS ? this.modelLeggings : this.modelArmor;
            model.setModelAttributes(this.renderer.getMainModel());

            setupVisibilityForSlot(model, slot);

            ResourceLocation coloredTrim = TrimColorHelper.createColoredTrim(overlayPath, palette);
            if (coloredTrim == null) continue;

            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.bindTexture(coloredTrim);
            model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.popMatrix();
        }
    }
    private static final Map<String, ResourceLocation> TRIM_CACHE = new HashMap<>();

    private ResourceLocation getOrCreateTrim(String pattern, String material) {
        String key = pattern + "_" + material;
        if (TRIM_CACHE.containsKey(key)) return TRIM_CACHE.get(key);

        String overlayPath = "textures/equipment/troms/entity/humanoid/" + pattern + ".png";
        Color[] palette = MATERIAL_TO_PALETTE.get(material);
        if (palette == null) return null;

        ResourceLocation res = TrimColorHelper.createColoredTrim(overlayPath, palette);
        TRIM_CACHE.put(key, res);
        return res;
    }

    private void setupVisibilityForSlot(ModelBiped model, EntityEquipmentSlot slot) {
        model.bipedHead.showModel = false;
        model.bipedHeadwear.showModel = false;
        model.bipedBody.showModel = false;
        model.bipedRightArm.showModel = false;
        model.bipedLeftArm.showModel = false;
        model.bipedRightLeg.showModel = false;
        model.bipedLeftLeg.showModel = false;

        switch (slot) {
            case HEAD:
                model.bipedHead.showModel = true;
                model.bipedHeadwear.showModel = true;
                break;
            case CHEST:
                model.bipedBody.showModel = true;
                model.bipedRightArm.showModel = true;
                model.bipedLeftArm.showModel = true;
                break;
            case LEGS:
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            case FEET:
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            default:
                break;
        }
    }
}