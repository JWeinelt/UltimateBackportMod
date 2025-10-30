package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.mixin.GuiChatWithSuggestions;
import de.julianweinelt.ubm.particles.ParticleCopperFlameFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ClientEventHandler {
    @SideOnly(Side.CLIENT)
    public static void registerParticles() {
        Minecraft.getMinecraft().effectRenderer.registerParticle(
            200,
            new ParticleCopperFlameFactory()
        );
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.openNewChat.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChatWithSuggestions());
            UBM.getLogger().info("Opening chat gui");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiMainMenu) {
            if (!Loader.isModLoaded("pathfinder")) {
                event.setGui(new MissingDependencyScreen());
            }
        }
    }
}