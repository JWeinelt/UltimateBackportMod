package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.entities.models.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderNewVillager extends RenderLiving<EntityNewVillager> {

    public RenderNewVillager(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelVillager(), 0.5F);
        this.addLayer(new VilliBiomeLayer(this));
        this.addLayer(new VilliProfLayer(this));
        this.addLayer(new VilliProfLevelLayer(this));
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nullable EntityNewVillager entity) {
        return new ResourceLocation("ubm:textures/entity/villager/villager.png");
    }

    @Override
    protected void preRenderCallback(@Nullable EntityNewVillager en, float partialTickTime) {
        if (en == null) return;
        float scale = en.isChild() ? 0.5F : 1.0F;
        GlStateManager.scale(scale, scale, scale);
    }
}