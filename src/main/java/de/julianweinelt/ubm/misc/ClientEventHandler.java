package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.mixin.GuiChatWithSuggestions;
import de.julianweinelt.ubm.particles.ParticleCopperFlameFactory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ClientEventHandler {
    public static void registerParticles() {
        Minecraft.getMinecraft().effectRenderer.registerParticle(
            200,
            new ParticleCopperFlameFactory()
        );
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == Keyboard.KEY_U) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiChatWithSuggestions());
            UBM.getLogger().info("Opening chat gui");
        }
    }

}
