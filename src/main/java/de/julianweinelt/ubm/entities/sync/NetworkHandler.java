package de.julianweinelt.ubm.entities.sync;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper INSTANCE =
            NetworkRegistry.INSTANCE.newSimpleChannel("ubm");

    private static int packetId = 0;

    public static void registerMessages() {
        INSTANCE.registerMessage(PacketSyncTradesHandler.class, PacketSyncTrades.class, packetId++, Side.CLIENT);
    }
}
