package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;

import java.util.Optional;

public interface IWeatherable {
    Optional<Block> getWeatheredVariant();

    Optional<Block> getUnweatheredVariant();

    Optional<Block> getWaxedVariant();

    default float getOxidationChance() {
        return 0.00027f;
    }
}
