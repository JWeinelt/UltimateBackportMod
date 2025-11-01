package de.julianweinelt.ubm.worldgen.dim;

import de.julianweinelt.ubm.worldgen.ModDimension;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderCustomNether extends WorldProvider {
    @Override
    protected void init() {
        this.biomeProvider = new BiomeProviderNether();
        this.hasSkyLight = false;
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
}