package de.julianweinelt.ubm.worldgen.misc;

import de.julianweinelt.ubm.worldgen.ModDimensions;
import de.julianweinelt.ubm.worldgen.nether.CustomTeleporter;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

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

            int dimensionId = ModDimensions.CUSTOM_NETHER.getId();
            WorldServer world = server.getWorld(dimensionId);

            player.changeDimension(dimensionId, new CustomTeleporter(world));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}
