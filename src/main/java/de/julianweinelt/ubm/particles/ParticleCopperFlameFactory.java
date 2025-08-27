package de.julianweinelt.ubm.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ParticleCopperFlameFactory implements IParticleFactory {
    @Nullable
    @Override
    public Particle createParticle(int particleID, @Nonnull World worldIn, double xCoordIn, double yCoordIn,
                                   double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn,
                                   @Nonnull int... p_178902_15_) {
        return new ParticleCopperFlame(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
    }
}
