package de.julianweinelt.ubm.blocks.interactable.smithing;

import de.julianweinelt.ubm.blocks.interactable.container.ContainerBlastFurnace;
import de.julianweinelt.ubm.blocks.interactable.gui.GuiBlastFurnace;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int SMITHING_TABLE = 0;
    public static final int BLAST_FURNACE = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SMITHING_TABLE) {
            return new ContainerSmithingTable(player.inventory);
        }
        if (ID == BLAST_FURNACE) {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
            if (te instanceof TileEntityBlastFurnace)
                return new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SMITHING_TABLE) {
            return new GuiSmithingTable(player.inventory);
        }
        if (ID == BLAST_FURNACE) {
            TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
            if (te instanceof TileEntityBlastFurnace) {
                ContainerBlastFurnace container = new ContainerBlastFurnace(player.inventory, (TileEntityBlastFurnace) te);
                return new GuiBlastFurnace(container, (TileEntityBlastFurnace) te);
            }
        }
        return null;
    }
}
