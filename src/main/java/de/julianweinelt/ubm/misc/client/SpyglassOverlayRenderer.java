package de.julianweinelt.ubm.misc.client;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.items.ItemSpyglass;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class SpyglassOverlayRenderer {

    private static final ResourceLocation OVERLAY = new ResourceLocation("ubm:textures/misc/spyglass_scope.png");

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();

        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
            return;

        if (mc.player == null)
            return;

        if (mc.player.isHandActive() && mc.player.getActiveItemStack().getItem() == ModItems.SPYGLASS) {
            renderOverlay(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
        }
    }

    @SideOnly(Side.CLIENT)
    private static void renderOverlay(int width, int height) {
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.disableAlpha();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();

        Minecraft.getMinecraft().getTextureManager().bindTexture(OVERLAY);

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);

        float texAspect = 1.0f;
        float screenAspect = (float) width / (float) height;

        float scale;
        float xOffset = 0;
        float yOffset = 0;

        if (screenAspect > texAspect) {
            scale = (float) height;
            xOffset = (width - scale) / 2.0f;
        } else {
            scale = (float) width;
            yOffset = (height - scale) / 2.0f;
        }

        buffer.pos(xOffset, height - yOffset, -90).tex(0, 1).endVertex();
        buffer.pos(width - xOffset, height - yOffset, -90).tex(1, 1).endVertex();
        buffer.pos(width - xOffset, yOffset, -90).tex(1, 0).endVertex();
        buffer.pos(xOffset, yOffset, -90).tex(0, 0).endVertex();

        tess.draw();

        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
    }
}
