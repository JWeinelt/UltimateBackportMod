package de.julianweinelt.ubm.blocks.interactable.smithing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int SMITHING_TABLE = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SMITHING_TABLE) {
            return new ContainerSmithingTable(player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SMITHING_TABLE) {
            return new GuiSmithingTable(player.inventory);
        }
        return null;
    }
}
