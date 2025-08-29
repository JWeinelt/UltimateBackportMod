package de.julianweinelt.ubm.worldgen;

import de.julianweinelt.ubm.worldgen.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import java.util.Arrays;

public class BiomeProviderNetherCustom extends BiomeProvider {

    private final Biome[] netherBiomes = new Biome[] {
            Biomes.HELL,
            //ModBiomes.NETHER_FOREST
    };

    public BiomeProviderNetherCustom() {
        allowedBiomes.clear();
        allowedBiomes.addAll(Arrays.asList(netherBiomes));
    }

    @Override
    public Biome getBiome(BlockPos pos) {
        return (pos.getX() / 100 + pos.getZ() / 100) % 2 == 0 ? netherBiomes[0] : netherBiomes[0];
    }

    @Override
    public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int length) {
        if (oldBiomeList == null || oldBiomeList.length < width * length) {
            oldBiomeList = new Biome[width * length];
        }

        for (int i = 0; i < width * length; i++) {
            int bx = x + (i % width);
            int bz = z + (i / width);
            oldBiomeList[i] = getBiome(new BlockPos(bx, 0, bz));
        }

        return oldBiomeList;
    }
}
