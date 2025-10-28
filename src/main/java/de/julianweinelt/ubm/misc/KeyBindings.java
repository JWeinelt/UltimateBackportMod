package de.julianweinelt.ubm.misc;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding openNewChat;

    public static void init() {
        openNewChat = new KeyBinding(
            "key.ubm.open_new_chat",
            Keyboard.KEY_U,
            "key.categories.ubm"
        );

        ClientRegistry.registerKeyBinding(openNewChat);
    }
}
