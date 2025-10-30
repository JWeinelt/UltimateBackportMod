package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.tiles.TileEntityDecoratedPot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderDecoratedPottery extends FastTESR<TileEntityDecoratedPot> {

    private static final ModelResourceLocation BASE_MODEL = new ModelResourceLocation("ubm:block/decorated_pot");
    private static final ResourceLocation[] PATTERN_TEXTURES = new ResourceLocation[24];

    static {
        for (int i = 0; i < 24; i++) {
            PATTERN_TEXTURES[i] = new ResourceLocation("ubm", "textures/blocks/decorated_pot/" + getName(i) + "_pottery_pattern.png");
        }
    }

    private static String getName(int id) {
        switch (id) {
            case 0: return "angler";
            case 1: return "howl";
            case 2: return "snort";
            default: return "empty";
        }
    }

    @Override
    public void renderTileEntityFast(@Nonnull TileEntityDecoratedPot te, double x, double y, double z, float partialTicks,
                                     int destroyStage, float alpha, @Nonnull BufferBuilder buffer) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        Minecraft mc = Minecraft.getMinecraft();
        BlockRendererDispatcher brd = mc.getBlockRendererDispatcher();

        IBakedModel baseModel = brd.getBlockModelShapes().getModelManager().getModel(BASE_MODEL);
        brd.getBlockModelRenderer().renderModelBrightnessColor(baseModel, 1.0F, 1.0F, 1.0F, 1.0F);

        for (EnumFacing side : EnumFacing.HORIZONTALS) {
            int id = te.getSherdForSide(side);
            if (id < 0 || id >= PATTERN_TEXTURES.length) continue;

            mc.getTextureManager().bindTexture(PATTERN_TEXTURES[id]);
            GlStateManager.pushMatrix();

            GlStateManager.translate(0.5, 0.5, 0.5);
            GlStateManager.rotate(getRotationFor(side), 0, 1, 0);
            GlStateManager.translate(-0.5, -0.5, -0.5);

            brd.getBlockModelRenderer().renderModelBrightnessColor(baseModel, 1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
    }

    private float getRotationFor(EnumFacing side) {
        switch (side) {
            case SOUTH: return 180;
            case WEST: return 90;
            case EAST: return -90;
            default: return 0;
        }
    }
}
