package de.julianweinelt.ubm.worldgen.nether;

import de.julianweinelt.ubm.worldgen.ModDimensions;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class NetherWorldProvider extends WorldProvider {

    @Override
    protected void init() {
        this.biomeProvider = new NetherBiomeProvider(this.world);
    }


    @Override
    public BiomeProvider getBiomeProvider() {
        return new NetherBiomeProvider(world);
    }

    @Override
    public boolean isSurfaceWorld() { return false; }

    @Override
    public boolean canRespawnHere() { return false; }

    @Override
    public boolean isNether() { return true; }

    @Override
    public float getCloudHeight() { return 8.0F; }

    @Override
    public Vec3d getFogColor(float angle, float partialTicks) {
        return new Vec3d(0.2, 0.03, 0.03);
    }

    @Override
    public boolean doesXZShowFog(int x, int z) { return true; }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.CUSTOM_NETHER;
    }
}