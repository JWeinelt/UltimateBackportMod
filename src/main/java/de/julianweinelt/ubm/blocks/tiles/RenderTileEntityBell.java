package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.entities.models.ModelBell;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityBell extends TileEntitySpecialRenderer<TileEntityBell> {

    private final ModelBell model = new ModelBell();

    @Override
    public void render(TileEntityBell te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        GlStateManager.pushMatrix();

        GlStateManager.translate(x + 0.5, y + 1.75, z + 0.5);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);

        float angle = 0.0f;

        if (te.ringing) {
            float time = te.ticks + partialTicks;

            float progress = time / 40.0f;

            if (progress > 1.0f) {
                progress = 1.0f;
            }

            float damping = 1.0f - progress;

            angle = (float) Math.sin(time * 0.4f) * 20.0f * damping;
        }

        GlStateManager.rotate(angle, 0.0f, 0.0f, 1.0f);

        bindTexture(new ResourceLocation("ubm:textures/entity/bell/bell_body.png"));
        model.render(null, 0, 0, 0, 0, 0, 0.0625f);

        GlStateManager.popMatrix();
    }
}