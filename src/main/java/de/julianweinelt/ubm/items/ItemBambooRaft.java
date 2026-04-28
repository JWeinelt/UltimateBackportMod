package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.entities.EntityBambooRaft;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBambooRaft extends Item {
    public ItemBambooRaft() {
        setUnlocalizedName("bamboo_raft");
        setRegistryName("bamboo_raft");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        float pitch = player.rotationPitch;
        float yaw = player.rotationYaw;

        Vec3d eyePos = player.getPositionEyes(1.0F);
        Vec3d lookVec = player.getLook(1.0F);
        Vec3d reachVec = eyePos.add(new Vec3d
                (lookVec.x * 5.0D, lookVec.y * 5.0D, lookVec.z * 5.0D)
        );

        RayTraceResult result = world.rayTraceBlocks(eyePos, reachVec, true);

        if (result == null || result.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }

        BlockPos pos = result.getBlockPos();

        if (!world.isRemote) {
            EntityBambooRaft raft = new EntityBambooRaft(world);
            raft.setPosition(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
            raft.rotationYaw = player.rotationYaw;

            world.spawnEntity(raft);
        }

        if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }
}