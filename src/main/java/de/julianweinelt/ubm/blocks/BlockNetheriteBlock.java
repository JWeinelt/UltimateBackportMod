package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class BlockNetheriteBlock extends Block {
    public BlockNetheriteBlock() {
        super(Material.ROCK);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
        this.setRegistryName("netherite_block");
        this.setUnlocalizedName("netherite_block");
        this.setResistance(1200);
        this.setHardness(50F);
        this.setHarvestLevel("pickaxe", 4);
    }

    @Override
    public boolean isBeaconBase(@Nonnull IBlockAccess worldObj, @Nonnull BlockPos pos, @Nonnull BlockPos beacon) {
        return true;
    }
}
