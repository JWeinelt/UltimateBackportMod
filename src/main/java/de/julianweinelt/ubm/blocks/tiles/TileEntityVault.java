package de.julianweinelt.ubm.blocks.tiles;

import de.julianweinelt.ubm.blocks.BlockVault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TileEntityVault extends TileEntity implements ITickable {

    private final Set<UUID> rewardedPlayers = new HashSet<>();


    public boolean hasOpened(EntityPlayer player) {
        return rewardedPlayers.contains(player.getUniqueID());
    }

    public void markOpened(EntityPlayer player) {
        rewardedPlayers.add(player.getUniqueID());
        markDirty();
    }

    @Override
    public void update() {
        if (world.isRemote) return;

        AxisAlignedBB box = new AxisAlignedBB(pos).grow(14);

        List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class, box);

        boolean eligiblePlayerNearby = false;

        for (EntityPlayer player : players) {
            if (!hasOpened(player)) {
                eligiblePlayerNearby = true;
                break;
            }
        }

        IBlockState state = world.getBlockState(pos);

        if (eligiblePlayerNearby) {
            if (state.getValue(BlockVault.STATE) == BlockVault.EnumVaultState.INACTIVE) {
                world.setBlockState(pos, state.withProperty(
                        BlockVault.STATE,
                        BlockVault.EnumVaultState.ACTIVE
                ).withProperty(
                        BlockVault.FACING,
                        state.getValue(BlockVault.FACING)
                ));
            }
        } else {
            if (state.getValue(BlockVault.STATE) == BlockVault.EnumVaultState.ACTIVE) {
                world.setBlockState(pos, state.withProperty(
                        BlockVault.STATE,
                        BlockVault.EnumVaultState.INACTIVE
                ).withProperty(
                        BlockVault.FACING,
                        state.getValue(BlockVault.FACING)
                ));
            }
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList list = new NBTTagList();

        for (UUID uuid : rewardedPlayers) {
            NBTTagCompound entry = new NBTTagCompound();
            entry.setUniqueId("uuid", uuid);
            list.appendTag(entry);
        }

        compound.setTag("RewardedPlayers", list);

        return compound;
    }

    @Override
    public void readFromNBT(@Nonnull NBTTagCompound compound) {
        super.readFromNBT(compound);

        rewardedPlayers.clear();

        NBTTagList list = compound.getTagList("RewardedPlayers", 10);

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound entry = list.getCompoundTagAt(i);
            rewardedPlayers.add(entry.getUniqueId("uuid"));
        }
    }
}
