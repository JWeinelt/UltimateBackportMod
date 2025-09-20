package de.julianweinelt.ubm.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ParticleTextureRegistry {
    @SubscribeEvent
    public static void registerTextures(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(new ResourceLocation("ubm:particle/copper_flame"));
        event.getMap().registerSprite(new ResourceLocation("ubm:particle/big_smoke_2"));
    }
}