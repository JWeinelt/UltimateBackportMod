package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.EnumHorizontalFacing;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.particles.ParticleCampfireSmoke;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockCampFire extends Block {
    public static final AxisAlignedBB CAMPFIRE_AABB = new AxisAlignedBB(
        0.0D, 0.0D, 0.0D,
        1.0D, 0.5D, 1.0D
);

    public static final PropertyEnum<EnumHorizontalFacing> FACING =
            PropertyEnum.create("facing", EnumHorizontalFacing.class);
    public static final PropertyBool LIT = PropertyBool.create("lit");

    public BlockCampFire() {
        super(Material.WOOD);
        this.setRegistryName("ubm", "campfire");
        this.setUnlocalizedName("campfire");
        this.setLightLevel(1.0F);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(FACING, EnumHorizontalFacing.NORTH)
                .withProperty(LIT, Boolean.TRUE));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, LIT);
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        boolean lit = (meta & 4) != 0;
        EnumHorizontalFacing facing = EnumHorizontalFacing.values()[meta & 3];
        return this.getDefaultState().withProperty(FACING, facing).withProperty(LIT, lit);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(FACING).ordinal();
        if (state.getValue(LIT)) {
            i |= 4;
        }
        return i;
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState()
                .withProperty(FACING, EnumHorizontalFacing.fromFacing(placer.getHorizontalFacing().getOpposite()))
                .withProperty(LIT, Boolean.TRUE);
    }

    @Override
    @Nonnull
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        EnumFacing facing = rot.rotate(state.getValue(FACING).toEnumFacing());
        return state.withProperty(FACING, EnumHorizontalFacing.fromEnumFacing(facing));
    }

    @Override
    @Nonnull
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        EnumFacing facing = mirrorIn.toRotation(state.getValue(FACING).toEnumFacing()).rotate(EnumFacing.NORTH);
        return state.withProperty(FACING, EnumHorizontalFacing.fromEnumFacing(facing));
    }


    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, @Nonnull IBlockAccess world,
                                        @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        return true;
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState state, @Nonnull IBlockAccess source,
                                        @Nonnull BlockPos pos) {
        return CAMPFIRE_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn,
                                                 @Nonnull BlockPos pos) {
        return CAMPFIRE_AABB;
    }

    @Override
    public boolean onBlockActivated(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state,
                                    @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        ItemStack heldItem = playerIn.getHeldItem(hand);

        if (heldItem != null) {
            if (heldItem.getItem() instanceof ItemSpade) {
                if (state.getValue(LIT)) {
                    if (!worldIn.isRemote) {
                        worldIn.setBlockState(pos, state.withProperty(LIT, false));
                    }
                    return true;
                }
            }

            if (heldItem.getItem() == Items.FLINT_AND_STEEL) {
                if (!state.getValue(LIT)) {
                    if (!worldIn.isRemote) {
                        worldIn.setBlockState(pos, state.withProperty(LIT, true));
                    }
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos,
                                  @Nonnull Random rand) {
        if (state.getValue(LIT)) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY() + 0.6D;
            double z = pos.getZ() + 0.5D;

            double offsetX = (rand.nextDouble() - 0.5D) * 0.3D;
            double offsetZ = (rand.nextDouble() - 0.5D) * 0.3D;

            double velocityY = 0.05D + rand.nextDouble() * 0.05D;

            Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft.getMinecraft().effectRenderer.addEffect(
                        new ParticleCampfireSmoke(worldIn, x, y, z, 0, velocityY, 0, 60)
                );
            });
        }
    }
}