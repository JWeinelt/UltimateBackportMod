package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.blocks.api.IWaxedCopper;
import de.julianweinelt.ubm.blocks.api.IWeatherable;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

@Mod.EventBusSubscriber
public class OxidationTickHandler {

    private static final Random RANDOM = new Random();
    private static final int CHECK_INTERVAL = 400;

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.world.isRemote) return;
        if (event.world.getTotalWorldTime() % CHECK_INTERVAL != 0) return;

        WorldServer world = (WorldServer) event.world;

        for (Chunk chunk : world.getChunkProvider().getLoadedChunks()) {
            tryOxidizeInChunk(world, chunk);
        }
    }

    private static void tryOxidizeInChunk(WorldServer world, Chunk chunk) {
        for (int i = 0; i < 3; i++) {
            int x = chunk.x * 16 + RANDOM.nextInt(16);
            int z = chunk.z * 16 + RANDOM.nextInt(16);
            int y = RANDOM.nextInt(256);

            BlockPos pos = new BlockPos(x, y, z);
            IBlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (!(block instanceof IWeatherable)) continue;

            IWeatherable weatherable = (IWeatherable) block;

            float chance = weatherable.getOxidationChance();
            chance *= getNeighborMultiplier(world, pos);

            if (RANDOM.nextFloat() < chance) {
                weatherable.getWeatheredVariant().ifPresent(next -> {
                    world.setBlockState(pos, next.getDefaultState());
                });
            }
        }
    }

    private static float getNeighborMultiplier(World world, BlockPos pos) {
        int copperNeighbors = 0;
        int waxedNeighbors = 0;

        for (BlockPos neighbor : BlockPos.getAllInBoxMutable(
                pos.add(-4, -4, -4), pos.add(4, 4, 4))) {

            Block b = world.getBlockState(neighbor).getBlock();
            if (b instanceof IWeatherable) copperNeighbors++;
            if (b instanceof IWaxedCopper) waxedNeighbors++;
        }

        if (waxedNeighbors > 0) return 0.0f;
        return 1.0f + (copperNeighbors / 16.0f);
    }
}