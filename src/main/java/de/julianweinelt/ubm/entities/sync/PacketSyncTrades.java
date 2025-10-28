package de.julianweinelt.ubm.entities.sync;

import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.village.MerchantRecipe;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketSyncTrades implements IMessage {
    private int entityId;
    private NBTTagList trades;

    public PacketSyncTrades() {}
    public PacketSyncTrades(EntityNewVillager villager) {
        this.entityId = villager.getEntityId();
        this.trades = new NBTTagList();
        for (MerchantRecipe recipe : villager.getTrades()) {
            trades.appendTag(recipe.writeToTags());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, entityId, 5);
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("Trades", trades);
        ByteBufUtils.writeTag(buf, compound);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityId = ByteBufUtils.readVarInt(buf, 5);
        NBTTagCompound compound = ByteBufUtils.readTag(buf);
        trades = compound.getTagList("Trades", 10);
    }

    public int getEntityId() {
        return entityId;
    }

    public NBTTagList getTrades() {
        return trades;
    }
}
