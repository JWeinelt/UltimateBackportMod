package de.julianweinelt.ubm.entities.sync;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class PacketSyncTradesHandler implements IMessageHandler<PacketSyncTrades, IMessage> {
    @Override
    public IMessage onMessage(PacketSyncTrades message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            Entity entity = Minecraft.getMinecraft().world.getEntityByID(message.getEntityId());
            if (entity instanceof EntityNewVillager) {
                List<MerchantRecipe> trades = new ArrayList<>();
                for (int i = 0; i < message.getTrades().tagCount(); i++) {
                    trades.add(new MerchantRecipe(message.getTrades().getCompoundTagAt(i)));
                }
                ((EntityNewVillager) entity).setTrades(trades);
            }
        });
        return null;
    }
}
