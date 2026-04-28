package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.BlockWaxedCopper;
import de.julianweinelt.ubm.blocks.api.BlockWeatherableCopper;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Optional;
import java.util.function.Supplier;

public class BlockWaxedCopperBlock extends BlockWaxedCopper {
    private final Supplier<Block> unwaxedVariant;

    public BlockWaxedCopperBlock(String name, Supplier<Block> unwaxedVariant) {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.unwaxedVariant = unwaxedVariant;
    }

    @Override
    public Optional<Block> getUnwaxedVariant() {
        return unwaxedVariant != null ? Optional.ofNullable(unwaxedVariant.get()) : Optional.empty();
    }
}
