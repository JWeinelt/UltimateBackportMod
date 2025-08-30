package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerWolfArmor implements LayerRenderer<EntityCustomWolf> {
    private static final ResourceLocation ARMOR_TEXTURE = new ResourceLocation("ubm:textures/equipment/wolf_body/armadillo_scute.png");
    private final RenderLivingBase<?> renderer;
    private final ModelWolf wolfModel = new ModelWolf();

    public LayerWolfArmor(RenderLivingBase<?> rendererIn) {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityCustomWolf entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entity.hasArmor()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.renderer.bindTexture(ARMOR_TEXTURE);
            this.wolfModel.setModelAttributes(this.renderer.getMainModel());
            this.wolfModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
            this.wolfModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
