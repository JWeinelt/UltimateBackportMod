package de.julianweinelt.ubm.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemSpawnEggCustom extends Item {
    private final Class<? extends Entity> entityClass;
    private final String entityName;

    public ItemSpawnEggCustom(Class<? extends Entity> entityClass, CreativeTabs tab, String entityName) {
        this.entityClass = entityClass;
        this.entityName = entityName;
        setCreativeTab(tab);
        setRegistryName(entityName + "_spawn_egg");
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nullable ItemStack stack) {
        return I18n.format("egg." + entityName + ".name");
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nullable EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

        if (raytraceresult == null) {
            return new ActionResult<>(EnumActionResult.PASS, stack);
        } else {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                BlockPos blockpos = raytraceresult.getBlockPos();
                if (!worldIn.isRemote) {
                    double x = blockpos.getX() + 0.5;
                    double y = blockpos.getY() + 1.0;
                    double z = blockpos.getZ() + 0.5;

                    Entity entity = EntityList.createEntityByIDFromName(EntityList.getKey(entityClass), worldIn);
                    if (entity != null) {
                        entity.setPosition(x, y, z);
                        worldIn.spawnEntity(entity);
                    }
                }
                if (!playerIn.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, stack);
    }
}
