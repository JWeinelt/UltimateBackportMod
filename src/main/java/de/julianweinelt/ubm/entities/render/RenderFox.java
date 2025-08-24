package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.EntityFox;
import de.julianweinelt.ubm.entities.models.ModelFox;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderFox extends RenderLiving<EntityFox> {

    private static final ResourceLocation FOX_TEXTURE = new ResourceLocation("ubm:textures/entity/fox/fox.png");

    public RenderFox(RenderManager renderManager) {
        super(renderManager, new ModelFox(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityFox entity) {
        return FOX_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityFox entitylivingbaseIn, float partialTickTime) {
        float scale = entitylivingbaseIn.isChild() ? 0.5F : 1.0F;
        GlStateManager.scale(scale, scale, scale);
    }
}
