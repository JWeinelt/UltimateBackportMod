package de.julianweinelt.ubm.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ParticleCampfireSmokeFactory implements IParticleFactory {

    @Override
    public Particle createParticle(int particleID, @Nonnull World worldIn, double x, double y, double z,
                                   double xSpeed, double ySpeed, double zSpeed, @Nonnull int... parameters) {
        return new ParticleCampfireSmoke(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 40);
    }
}
