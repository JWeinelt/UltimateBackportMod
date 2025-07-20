package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.models.ModelBambooRaft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderBambooRaft extends RenderBoat {
    private static final ResourceLocation TEXTURE = new ResourceLocation("ubm:textures/entity/boat/bamboo.png");
    private final ModelBambooRaft model = new ModelBambooRaft();

    public RenderBambooRaft(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(@Nonnull EntityBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float) x, (float) y + 0.375F, (float) z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);

        this.bindEntityTexture(entity);

        model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nonnull
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityBoat entity) {
        return TEXTURE;
    }
}
