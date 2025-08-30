package de.julianweinelt.ubm.trims;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.awt.*;

public class LayerArmorTrim extends LayerBipedArmor {

    private final RenderLivingBase<?> renderer;
    private final ResourceLocation trimTexture;
    private final ResourceLocation trimTextureLegs;

    public LayerArmorTrim(RenderLivingBase<?> renderer) {
        super(renderer);
        this.renderer = renderer;
        this.trimTexture = new ResourceLocation("ubm", "textures/equipment/trims/entity/humanoid/wayfinder.png");
        this.trimTextureLegs = new ResourceLocation("ubm", "textures/equipment/trims/entity/humanoid_leggings/wayfinder.png");
    }

    @Override
    protected void initArmor() {
        this.modelLeggings = new ModelBiped(0.5F);
        this.modelArmor = new ModelBiped(1.0F);
    }

    @Override
    public void doRenderLayer(@Nonnull EntityLivingBase entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        Color[] palette = new Color[] {
                new Color(0xC98FF3), new Color(0x9A5CC6), new Color(0x6C49AA),
                new Color(0x523687), new Color(0x422776), new Color(0x361C6A),
                new Color(0x240C53), new Color(0x17063B)
        };

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() != EntityEquipmentSlot.Type.ARMOR) continue;

            ItemStack stack = entity.getItemStackFromSlot(slot);
            if (stack.isEmpty()) continue;

            // Modell auswählen
            ModelBiped model = slot == EntityEquipmentSlot.LEGS ? this.modelLeggings : this.modelArmor;

            // Pose übernehmen
            model.setModelAttributes(this.renderer.getMainModel());

            // Sichtbarkeit der Teile setzen
            if (slot == EntityEquipmentSlot.LEGS) {
                model.bipedHead.showModel = false;
                model.bipedHeadwear.showModel = false;
                model.bipedBody.showModel = false;
                model.bipedRightArm.showModel = false;
                model.bipedLeftArm.showModel = false;
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
            } else if (slot == EntityEquipmentSlot.FEET) {
                model.bipedHead.showModel = false;
                model.bipedHeadwear.showModel = false;
                model.bipedBody.showModel = false;
                model.bipedRightArm.showModel = false;
                model.bipedLeftArm.showModel = false;
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
            } else if (slot == EntityEquipmentSlot.CHEST) {
                model.bipedHead.showModel = false;
                model.bipedHeadwear.showModel = false;
                model.bipedBody.showModel = true;
                model.bipedRightArm.showModel = true;
                model.bipedLeftArm.showModel = true;
                model.bipedRightLeg.showModel = false;
                model.bipedLeftLeg.showModel = false;
            } else if (slot == EntityEquipmentSlot.HEAD) {
                model.bipedHead.showModel = true;
                model.bipedHeadwear.showModel = true;
                model.bipedBody.showModel = false;
                model.bipedRightArm.showModel = false;
                model.bipedLeftArm.showModel = false;
                model.bipedRightLeg.showModel = false;
                model.bipedLeftLeg.showModel = false;
            }

            // Overlay-Pfad auswählen (Leggings separat)
            String overlayPath = slot == EntityEquipmentSlot.LEGS
                    ? "textures/equipment/trims/leggings_overlay.png"
                    : "textures/equipment/trims/overlay.png";

            // Dynamische Textur erzeugen
            ResourceLocation coloredTrim = TrimColorHelper.createColoredTrim(overlayPath, palette);
            if (coloredTrim == null) continue;

            // Rendern
            GlStateManager.pushMatrix();
            this.renderer.bindTexture(coloredTrim);
            model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.popMatrix();
        }
    }



}
