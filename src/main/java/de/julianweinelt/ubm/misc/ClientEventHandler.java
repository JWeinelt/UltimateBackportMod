package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.particles.ParticleCopperFlameFactory;
import net.minecraft.client.Minecraft;

public class ClientEventHandler {
    public static void registerParticles() {
        Minecraft.getMinecraft().effectRenderer.registerParticle(
            200,
            new ParticleCopperFlameFactory()
        );
    }
}
