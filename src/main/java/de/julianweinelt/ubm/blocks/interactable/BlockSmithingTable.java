package de.julianweinelt.ubm.blocks.interactable;

import de.julianweinelt.ubm.blocks.interactable.smithing.TileEntitySmithingTable;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSmithingTable extends BlockContainer {
    public BlockSmithingTable() {
        super(Material.WOOD);
        setUnlocalizedName("smithing_table");
        setRegistryName("smithing_table");
        setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySmithingTable();
    }
}