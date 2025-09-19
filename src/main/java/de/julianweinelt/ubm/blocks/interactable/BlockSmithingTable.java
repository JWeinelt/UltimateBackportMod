package de.julianweinelt.ubm.blocks.interactable;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.interactable.smithing.GuiHandler;
import de.julianweinelt.ubm.blocks.interactable.smithing.TileEntitySmithingTable;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockSmithingTable extends Block {
    public BlockSmithingTable() {
        super(Material.WOOD);
        setUnlocalizedName("smithing_table");
        setRegistryName("smithing_table");
        setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(UBM.instance, GuiHandler.SMITHING_TABLE, world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

}