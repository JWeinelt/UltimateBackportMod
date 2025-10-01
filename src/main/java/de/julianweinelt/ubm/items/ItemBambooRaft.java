package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.entities.EntityBambooRaft;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBambooRaft extends ItemBoat {
    public ItemBambooRaft() {
        super(EntityBoat.Type.OAK);
        setUnlocalizedName("bamboo_raft");
        setRegistryName("bamboo_raft");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

        if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
            double x = raytraceresult.hitVec.x;
            double y = raytraceresult.hitVec.y;
            double z = raytraceresult.hitVec.z;

            if (!worldIn.isRemote) {
                EntityBambooRaft raft = new EntityBambooRaft(worldIn, x, y + 1.0D, z);
                worldIn.spawnEntity(raft);

                if (!playerIn.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }
            }

            //playerIn.playSound(SoundEvents.ENTITY_BOAT_PLACE, 1.0F, 1.0F);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
        }

        return new ActionResult<>(EnumActionResult.PASS, itemstack);
    }
}