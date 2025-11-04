package de.julianweinelt.ubm.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionIconRenderer {

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;

        for (PotionEffect effect : mc.player.getActivePotionEffects()) {
            if (effect.getPotion() == ModEffects.TRIAL_OMEN) {
                renderCustomEffectIcon(event.getResolution(), 2, 1);
            } else if (effect.getPotion() == ModEffects.BAD_OMEN) {
                renderCustomEffectIcon(event.getResolution(), 1, 0);
            } else if (effect.getPotion() == ModEffects.RAID_OMEN) {
                renderCustomEffectIcon(event.getResolution(), 0, 1);
            } else if (effect.getPotion() == ModEffects.OOZING) {
                renderCustomEffectIcon(event.getResolution(), 7, 0);
            } else if (effect.getPotion() == ModEffects.INFESTED) {
                renderCustomEffectIcon(event.getResolution(), 6, 0);
            } else if (effect.getPotion() == ModEffects.HERO_OF_THE_VILLAGE) {
                renderCustomEffectIcon(event.getResolution(), 5, 0);
            }
        }
    }

    private void renderCustomEffectIcon(ScaledResolution resolution, int col, int row) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(new ResourceLocation("ubm", "textures/gui/status_effects.png"));

        GlStateManager.color(1F, 1F, 1F, 1F);

        int u = col * 18;
        int v = row * 18;

        int x = resolution.getScaledWidth() - 25;
        int y = resolution.getScaledHeight() - 50;

        mc.ingameGUI.drawTexturedModalRect(x, y, u, v, 18, 18);
    }

}
