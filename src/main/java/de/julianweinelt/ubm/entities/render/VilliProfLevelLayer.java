package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.entities.models.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class VilliProfLevelLayer implements LayerRenderer<EntityNewVillager> {
    private static final String LEVEL_TEXTURE_BASE = "ubm:textures/entity/villager/profession_level/";
    private final RenderLivingBase<?> renderer;
    private final ModelVillager villagerModel;

    public VilliProfLevelLayer(RenderLivingBase<?> rendererIn) {
        this.renderer = rendererIn;
        villagerModel = (ModelVillager) this.renderer.getMainModel();
    }

    @Override
    public void doRenderLayer(EntityNewVillager entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        String levelName = "stone";
        switch (entity.getProfessionLevel()) {
            case 0:
                levelName = "stone";
                break;
            case 1:
                levelName = "iron";
                break;
            case 2:
                levelName = "gold";
                break;
            case 3:
                levelName = "emerald";
                break;
            case 4:
                levelName = "diamond";
                break;
        }
        this.renderer.bindTexture(new ResourceLocation("ubm", LEVEL_TEXTURE_BASE + levelName + ".png"));
        this.villagerModel.setModelAttributes(this.renderer.getMainModel());
        this.villagerModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        this.villagerModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}