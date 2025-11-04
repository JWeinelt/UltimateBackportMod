package de.julianweinelt.ubm.misc;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding openNewChat;

    @SideOnly(Side.CLIENT)
    public static void init() {
        openNewChat = new KeyBinding(
            "key.ubm.open_new_chat",
            Keyboard.KEY_U,
            "key.categories.ubm"
        );

        ClientRegistry.registerKeyBinding(openNewChat);
    }
}
