package de.julianweinelt.ubm.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCopperFlame extends Particle {

    public ParticleCopperFlame(World world, double x, double y, double z,
                          double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);

        this.particleRed = 0.2F;
        this.particleGreen = 1.0F;
        this.particleBlue = 0.2F;

        this.particleMaxAge = 40;
        this.setSize(0.02F, 0.02F);

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks()
                .getAtlasSprite(new ResourceLocation("ubm:particle/copper_flame").toString());
        this.setParticleTexture(sprite);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.motionY += 0.002;
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}