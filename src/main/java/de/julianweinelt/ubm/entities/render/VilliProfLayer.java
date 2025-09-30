package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.entities.models.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class VilliProfLayer implements LayerRenderer<EntityNewVillager> {
    private static final ResourceLocation PROFESSION_TEXTURE = new ResourceLocation("ubm:textures/entity/villager/profession/armorer.png");
    private final RenderLivingBase<?> renderer;
    private final ModelVillager villagerModel;

    public VilliProfLayer(RenderLivingBase<?> rendererIn) {
        this.renderer = rendererIn;
        villagerModel = (ModelVillager) this.renderer.getMainModel();
    }

    @Override
    public void doRenderLayer(EntityNewVillager entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.renderer.bindTexture(PROFESSION_TEXTURE);
        this.villagerModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
