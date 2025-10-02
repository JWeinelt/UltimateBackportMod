package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockPowderSnow extends Block {
    public BlockPowderSnow() {
        super(Material.SNOW);
        setUnlocalizedName("powder_snow");
        setRegistryName("powder_snow");
        setCreativeTab(ModCreativeTabs.UBM_TAB_WILD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);

        if (!heldItem.isEmpty() && heldItem.getItem() == Items.BUCKET) {

            if (!worldIn.isRemote) {
                worldIn.setBlockToAir(pos);
                playerIn.setHeldItem(hand, new ItemStack(ModItems.POWDER_SNOW_BUCKET));

                worldIn.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, @Nullable IBlockAccess world,
                                        @Nullable BlockPos pos, @Nullable EnumFacing side) {
        return true;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }


    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
                                      List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;

            ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if (!boots.isEmpty() && boots.getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) boots.getItem();
                if (armor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER) {
                    if (entityIn.isSneaking()) return;
                    AxisAlignedBB bb = FULL_BLOCK_AABB.offset(pos);
                    if (entityBox.intersects(bb)) {
                        collidingBoxes.add(bb);
                    }
                }
            }
        }
    }


    @Override
    @Nonnull
    protected ItemStack getSilkTouchDrop(@Nonnull IBlockState state) {
        return new ItemStack(ModItems.POWDER_SNOW_BUCKET);
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nullable IBlockState state, @Nullable Random rand, int fortune) {
        return ModItems.POWDER_SNOW_BUCKET;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return 1;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        entity.motionX *= 0.5;
        entity.motionZ *= 0.5;
        entity.motionY *= 0.3;
        entity.fallDistance = 0.0F;
    }

}