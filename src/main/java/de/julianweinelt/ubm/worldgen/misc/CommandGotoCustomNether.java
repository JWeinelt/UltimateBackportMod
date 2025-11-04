package de.julianweinelt.ubm.worldgen.misc;

import de.julianweinelt.ubm.worldgen.ModDimension;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandGotoCustomNether extends CommandBase {

    @Override
    public String getName() {
        return "goto_customnether";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/goto_customnether";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;
            int dimensionId = ModDimension.CUSTOM_NETHER_DIM_ID;

            player.changeDimension(dimensionId);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
