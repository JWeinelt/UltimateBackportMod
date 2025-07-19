package de.julianweinelt.ubm.worldgen.tree;

import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenMyNetherTree extends WorldGenAbstractTree {
    public WorldGenMyNetherTree() {
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        // hier deinen Baum aufbauen
        // z.B. mehrere Blöcke für Stamm + Blätter platzieren
        worldIn.setBlockState(position, ModBlocks.WARPED_WART_BLOCK.getDefaultState());
        worldIn.setBlockState(position.up(), ModBlocks.WARPED_STEM.getDefaultState());
        return true;
    }
}
