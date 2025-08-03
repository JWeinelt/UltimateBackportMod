package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockCandle extends Block {

    public static final PropertyInteger CANDLES = PropertyInteger.create("candles", 1, 4);
    public static final PropertyBool LIT = PropertyBool.create("lit");

    private static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {
            new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.5D, 0.6D), // 1 Kerze
            new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.5D, 0.7D), // 2 Kerzen
            new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.5D, 0.8D), // 3 Kerzen
            new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.5D, 0.9D)  // 4 Kerzen
    };

    private static final Vec3d[][] PARTICLE_OFFSETS = new Vec3d[][] {
            {
                    new Vec3d(0.0D, 0.0D, 0.0D)
            },
            {
                    new Vec3d(-0.1D, 0.0D, 0.0D),
                    new Vec3d( 0.1D, 0.0D, 0.0D)
            },
            {
                    new Vec3d(-0.1D, 0.0D, -0.1D),
                    new Vec3d( 0.1D, 0.0D, -0.1D),
                    new Vec3d( 0.0D, 0.0D,  0.1D)
            },
            {
                    new Vec3d(-0.1D, 0.0D, -0.1D),
                    new Vec3d( 0.1D, 0.0D, -0.1D),
                    new Vec3d(-0.1D, 0.0D,  0.1D),
                    new Vec3d( 0.1D, 0.0D,  0.1D)
            }
    };

    public BlockCandle() {
        super(Material.CIRCUITS); // oder Material.WOOD je nach Optik
        this.setDefaultState(this.blockState.getBaseState()
            .withProperty(CANDLES, 1)
            .withProperty(LIT, false));
        this.setLightOpacity(0);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CANDLES, LIT);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int candles = (meta & 3) + 1;
        boolean lit = (meta & 4) != 0;
        return this.getDefaultState()
                .withProperty(CANDLES, candles)
                .withProperty(LIT, lit);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = (state.getValue(CANDLES) - 1);
        if (state.getValue(LIT)) {
            meta |= 4;
        }
        return meta;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack heldItem = player.getHeldItem(hand);

        if (!world.isRemote) {
            if (heldItem.getItem() == Items.FLINT_AND_STEEL) {
                boolean lit = true;
                world.setBlockState(pos, state.withProperty(LIT, lit), 3);
                setLightLevel(1.0F);
                if (!player.capabilities.isCreativeMode) {
                    heldItem.shrink(1);
                }
                return true;
            } else if (heldItem.isEmpty()) {
                boolean lit = false;
                world.setBlockState(pos, state.withProperty(LIT, lit), 3);
                setLightLevel(0.0F);
            } else if (heldItem.getItem() == Items.IRON_AXE) {
                int candles = state.getValue(CANDLES);
                if (candles < 4) {
                    if (!world.isRemote) {
                        world.setBlockState(pos, state.withProperty(CANDLES, candles + 1), 3);

                        if (!player.capabilities.isCreativeMode) {
                            heldItem.shrink(1);
                        }
                    }
                    return true;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return true; // Boden darunter wird nicht durchsichtig
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOXES[state.getValue(CANDLES) - 1];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if (!state.getValue(LIT)) return; // Nur wenn die Kerzen an sind

        int candles = state.getValue(CANDLES);

        for (Vec3d offset : PARTICLE_OFFSETS[candles - 1]) {
            double x = pos.getX() + 0.5D + offset.x;
            double y = pos.getY() + 0.7D + offset.y;
            double z = pos.getZ() + 0.5D + offset.z;

            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0, 0, 0);
            world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
        }
    }


}
