package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class VilliBiomeLayer implements LayerRenderer<EntityNewVillager> {
    private final RenderLivingBase<EntityNewVillager> renderer;

    public VilliBiomeLayer(RenderLivingBase<EntityNewVillager> rendererIn) {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityNewVillager entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw,
                              float headPitch, float scale) {

        this.renderer.bindTexture(new ResourceLocation("ubm:textures/entity/villager/type/"
                + entity.getBiome().name().toLowerCase() + ".png"));

        GlStateManager.color(1F, 1F, 1F, 1F);

        this.renderer.getMainModel().render(entity,
                limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.color(1F, 1F, 1F, 1F);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false; // wichtig gegen Z-Fighting
    }
}
