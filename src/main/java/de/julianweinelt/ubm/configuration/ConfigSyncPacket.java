package de.julianweinelt.ubm.configuration;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConfigSyncPacket implements IMessage {

    private String yamlData;

    public ConfigSyncPacket() {}

    public ConfigSyncPacket(String yamlData) {
        this.yamlData = yamlData;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, yamlData);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        yamlData = ByteBufUtils.readUTF8String(buf);
    }

    public String getYamlData() {
        return yamlData;
    }
}