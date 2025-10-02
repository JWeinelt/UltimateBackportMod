package de.julianweinelt.ubm.core;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class ExtendedChunk extends Chunk {

    public static final int MIN_Y = -64;
    public static final int MAX_Y = 255;
    private final ExtendedBlockStorage[] extendedStorage;

    public ExtendedChunk(World worldIn, int x, int z) {
        super(worldIn, x, z);
        int storageCount = (MAX_Y - MIN_Y + 16) / 16;
        extendedStorage = new ExtendedBlockStorage[storageCount];
        for (int i = 0; i < storageCount; i++) {
            extendedStorage[i] = new ExtendedBlockStorage((i << 4) + MIN_Y, worldIn.provider.hasSkyLight());
        }
    }

    @Override
    public IBlockState getBlockState(BlockPos pos) {
        int y = pos.getY();
        if (y < MIN_Y || y > MAX_Y) return super.getBlockState(pos); // Outside extended range
        int storageIndex = (y - MIN_Y) >> 4;
        int localY = (y - MIN_Y) & 15;
        return extendedStorage[storageIndex].get(pos.getX() & 15, localY, pos.getZ() & 15);
    }

    @Override
    public IBlockState setBlockState(BlockPos pos, IBlockState state) {
        int y = pos.getY();
        if (y < MIN_Y || y > MAX_Y) {
            super.setBlockState(pos, state);
            return state;
        }
        int storageIndex = (y - MIN_Y) >> 4;
        int localY = (y - MIN_Y) & 15;
        extendedStorage[storageIndex].set(pos.getX() & 15, localY, pos.getZ() & 15, state);
        return state;
    }
}
