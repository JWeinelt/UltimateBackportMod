package de.julianweinelt.ubm.misc.server;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.configuration.ConfigSyncPacket;
import de.julianweinelt.ubm.configuration.ModYamlConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ConfigEvents {
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayerMP player = (EntityPlayerMP) event.player;

        String yamlString = ModYamlConfig.instance().getConfigData();

        UBM.network.sendTo(new ConfigSyncPacket(yamlString), player);
    }
}
