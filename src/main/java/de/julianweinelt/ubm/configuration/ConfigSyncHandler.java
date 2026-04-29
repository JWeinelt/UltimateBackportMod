package de.julianweinelt.ubm.configuration;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ConfigSyncHandler implements IMessageHandler<ConfigSyncPacket, IMessage> {

    @Override
    public IMessage onMessage(ConfigSyncPacket message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            String yaml = message.getYamlData();

        });

        return null;
    }
}