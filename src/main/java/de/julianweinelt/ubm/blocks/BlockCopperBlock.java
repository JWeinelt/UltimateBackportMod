package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.BlockWeatherableCopper;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Optional;
import java.util.function.Supplier;

public class BlockCopperBlock extends BlockWeatherableCopper {
    private final Supplier<Block> weatheredVariant;
    private final Supplier<Block> unweatheredVariant;
    private final Supplier<Block> waxedVariant;

    public BlockCopperBlock(String name, Supplier<Block> weatheredVariant, Supplier<Block> unweatheredVariant, Supplier<Block> waxedVariant) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.weatheredVariant = weatheredVariant;
        this.unweatheredVariant = unweatheredVariant;
        this.waxedVariant = waxedVariant;
    }

    @Override
    public Optional<Block> getWeatheredVariant() {
        return weatheredVariant != null ? Optional.ofNullable(weatheredVariant.get()) : Optional.empty();
    }

    @Override
    public Optional<Block> getUnweatheredVariant() {
        return unweatheredVariant != null ? Optional.ofNullable(unweatheredVariant.get()) : Optional.empty();
    }

    @Override
    public Optional<Block> getWaxedVariant() {
        return waxedVariant != null ? Optional.ofNullable(waxedVariant.get()) : Optional.empty();
    }
}
