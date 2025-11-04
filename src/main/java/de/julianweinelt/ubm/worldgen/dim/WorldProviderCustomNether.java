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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class WorldProviderCustomNether extends WorldProvider {
    @Override
    protected void init() {
        this.biomeProvider = new BiomeProviderNether(world.getSeed());
    }

    @Override
    @Nonnull
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorHell(world, true, world.getSeed());
    }

    @Override
    @Nonnull
    public DimensionType getDimensionType() {
        return DimensionType.getById(ModDimension.CUSTOM_NETHER_DIM_ID);
    }

    @Override
    public boolean canBlockFreeze(@Nonnull BlockPos pos, boolean byWater) {
        return false;
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    @Nonnull
    public WorldSleepResult canSleepAt(@Nonnull EntityPlayer player, @Nonnull BlockPos pos) {
        return WorldSleepResult.BED_EXPLODES;
    }

    @Override
    public boolean canDoRainSnowIce(@Nonnull Chunk chunk) {
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
    public boolean doesWaterVaporize() {
        return true;
    }

    @Override
    public boolean isNether() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }


    @Override
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(int x, int z)
    {
        return true;
    }



    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        BlockPos pos = new BlockPos(0, 64, 0);
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