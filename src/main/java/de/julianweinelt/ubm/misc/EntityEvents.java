package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ItemSpyglass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class EntityEvents {

    private static float spyglassZoom = 1.0F; // Standard-FOV (kein Zoom)

    @SubscribeEvent
    public static void onFovUpdate(FOVUpdateEvent event) {
        EntityPlayer player = event.getEntity();
        ItemStack stack = player.getActiveItemStack();

        boolean active = !stack.isEmpty() && stack.getItem() instanceof ItemSpyglass;

        if (active) {
            spyglassZoom += (0.2F - spyglassZoom) * 0.2F;
        } else {
            spyglassZoom += (1.0F - spyglassZoom) * 0.2F;
        }

        event.setNewfov(spyglassZoom);
    }
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;

        if (player == null) return;
        ItemStack stack = player.getActiveItemStack();

        if (!stack.isEmpty() && stack.getItem() instanceof ItemSpyglass) {
            mc.getTextureManager().bindTexture(new ResourceLocation("ubm:textures/gui/spyglass_scope.png"));

            int width = event.getResolution().getScaledWidth();
            int height = event.getResolution().getScaledHeight();

            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.color(1F, 1F, 1F, 1F);

            Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);

            GlStateManager.disableBlend();
            GlStateManager.enableDepth();
        }
    }



    @SubscribeEvent
    public static void onEntityTick(TickEvent.PlayerTickEvent event) {
        Entity entity = event.player;
        World world = entity.world;

        if (world.isRemote) return;

        BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
        BlockPos pos2 = new BlockPos(entity.posX, entity.posY + 1, entity.posZ);
        if (world.getBlockState(pos).getBlock() == ModBlocks.POWDER_SNOW ||
            world.getBlockState(pos2).getBlock() == ModBlocks.POWDER_SNOW) {
            applySlowdown(entity);
        }
    }

    private static void applySlowdown(Entity entity) {
        entity.motionX *= 0.2D;
        entity.motionZ *= 0.2D;
        entity.motionY *= 0.6D;
    }
}
