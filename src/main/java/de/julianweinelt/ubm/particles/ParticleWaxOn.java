package de.julianweinelt.ubm.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleWaxOn extends Particle {
    public ParticleWaxOn(World worldIn, double x, double y, double z, double vx, double vy, double vz) {
        super(worldIn, x, y, z, vx, vy, vz);

        this.particleMaxAge = 60 + this.rand.nextInt(20);

        this.particleScale = 1F;

        this.motionX = vx;
        this.motionY = vy;
        this.motionZ = vz;

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks()
                .getAtlasSprite(new ResourceLocation("ubm:particle/wax").toString());
        this.setParticleTexture(sprite);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.motionX *= 0.95;
        this.motionZ *= 0.95;
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}
