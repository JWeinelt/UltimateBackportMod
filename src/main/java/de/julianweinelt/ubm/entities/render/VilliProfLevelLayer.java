package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.entities.models.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class VilliProfLevelLayer implements LayerRenderer<EntityNewVillager> {
    private static final ResourceLocation LEVEL_TEXTURE = new ResourceLocation("ubm:textures/entity/villager/profession_level/stone.png");
    private final RenderLivingBase<?> renderer;
    private final ModelVillager villagerModel = new ModelVillager();

    public VilliProfLevelLayer(RenderLivingBase<?> rendererIn) {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityNewVillager entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.renderer.bindTexture(LEVEL_TEXTURE);
        this.villagerModel.setModelAttributes(this.renderer.getMainModel());
        this.villagerModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        this.villagerModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}