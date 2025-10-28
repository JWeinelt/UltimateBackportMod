package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.mixin.GuiChatWithSuggestions;
import de.julianweinelt.ubm.mixin.GuiMainMenuNonBlur;
import de.julianweinelt.ubm.mixin.loading.SimpleLoadingScreen;
import de.julianweinelt.ubm.particles.ParticleCopperFlameFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

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
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == Keyboard.KEY_U) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChatWithSuggestions());
            UBM.getLogger().info("Opening chat gui");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onGuiOpen(GuiOpenEvent event) {
        if (event.getGui() instanceof GuiChat) {
            event.setGui(new GuiChatWithSuggestions());
        }
        if (event.getGui() instanceof GuiMainMenu) {
            //event.setGui(new GuiMainMenuNonBlur());
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTextureStitchPre(TextureStitchEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.addScheduledTask(() -> mc.displayGuiScreen(new SimpleLoadingScreen("Loading resources...")));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTextureStitchPost(TextureStitchEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            if (mc.currentScreen instanceof SimpleLoadingScreen) {
                mc.displayGuiScreen(null);
            }
        });
    }
}