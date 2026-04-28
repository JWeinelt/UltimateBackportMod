package de.julianweinelt.ubm.worldgen.dim;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import de.julianweinelt.ubm.worldgen.ModDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderCustomNether extends WorldProvider {
    @Override
    protected void init() {
        this.biomeProvider = new BiomeProviderNether(world.getSeed());
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorHell(world, true, world.getSeed());
    }

    @Override
    public DimensionType getDimensionType() {
        return DimensionType.getById(ModDimension.CUSTOM_NETHER_DIM_ID);
    }

    @Override
    public boolean canBlockFreeze(BlockPos pos, boolean byWater) {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public WorldSleepResult canSleepAt(EntityPlayer player, BlockPos pos) {
        return WorldSleepResult.BED_EXPLODES;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public float getCloudHeight() {
        return 500;
    }

    @Override
    public boolean hasSkyLight() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        BlockPos pos = new BlockPos(0, 64, 0); // Fallback, falls Welt null ist
        if (this.world != null && this.world.getPlayerEntityByName(Minecraft.getMinecraft().player.getName()) != null) {
            pos = this.world.getPlayerEntityByName(Minecraft.getMinecraft().player.getName()).getPosition();
        }

        Biome biome = this.world.getBiome(pos);

        if (biome == ModBiomes.CRIMSON_FOREST) {
            return new Vec3d(0.200, 0.012, 0.012);
        } else if (biome == ModBiomes.WARPED_FOREST) {
            return new Vec3d(0.102, 0.020, 0.102);
        }

        // Soulsand valley new Vec3d(0.106, 0.278, 0.271)
        // Basalt Deltas new Vec3d(0.408, 0.373, 0.439)

        return new Vec3d(0.200, 0.031, 0.031);
    }
}