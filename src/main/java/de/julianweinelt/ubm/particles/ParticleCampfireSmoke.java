package de.julianweinelt.ubm.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;

public class ParticleCampfireSmoke extends Particle {
    public ParticleCampfireSmoke(World worldIn, double x, double y, double z, double vx, double vy, double vz, int lifetime) {
        super(worldIn, x, y, z, vx, vy, vz);

        this.particleMaxAge = lifetime + this.rand.nextInt(20);

        this.particleScale = 5F;

        this.motionX = vx;
        this.motionY = vy;
        this.motionZ = vz;

        TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks()
                .getAtlasSprite(new ResourceLocation("ubm:particle/big_smoke_2").toString());
        setParticleTexture(sprite);
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
