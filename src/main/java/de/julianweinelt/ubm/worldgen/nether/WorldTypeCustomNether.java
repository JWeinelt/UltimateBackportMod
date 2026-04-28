package de.julianweinelt.ubm.worldgen.nether;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeCustomNether extends WorldType {

    public WorldTypeCustomNether() {
        super("custom_nether");
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new NetherChunkGenerator(world, world.getSeed());
    }

    @Override
    public boolean isCustomizable() {
        return false;
    }
}