package de.julianweinelt.ubm.blocks.plant;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockGlowLichen extends Block {
    private static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    private static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D,   1.0D, 1.0D, 0.125D);
    private static final AxisAlignedBB WEST_AABB  = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    private static final AxisAlignedBB EAST_AABB  = new AxisAlignedBB(0.0D,   0.0D, 0.0D, 0.125D, 1.0D, 1.0D);
    private static final AxisAlignedBB UP_AABB    = new AxisAlignedBB(0.0D, 0.0D, 0.0D,   1.0D, 0.125D, 1.0D);
    private static final AxisAlignedBB DOWN_AABB  = new AxisAlignedBB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D,   1.0D);

    public static final PropertyEnum<AttachSide> SIDE = PropertyEnum.create("side", AttachSide.class);

    public BlockGlowLichen() {
        super(Material.PLANTS);
        this.setUnlocalizedName("glow_lichen");
        this.setRegistryName("glow_lichen");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.setSoundType(SoundType.PLANT);
        this.setLightLevel(0.4F);
        this.setHardness(0.2F);
        this.setResistance(0.2F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(SIDE, AttachSide.NORTH));
    }


    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, @Nonnull IBlockAccess source, @Nonnull BlockPos pos) {
        switch (state.getValue(SIDE)) {
            case NORTH: return SOUTH_AABB;
            case SOUTH: return NORTH_AABB;
            case EAST:  return WEST_AABB;
            case WEST:  return EAST_AABB;
            case UP:    return DOWN_AABB;
            case DOWN:  return UP_AABB;
            default:    return FULL_BLOCK_AABB;
        }
    }
    @Override
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }




    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SIDE);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(SIDE, AttachSide.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(SIDE).getMetadata();
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta,
                                            EntityLivingBase placer) {
        return this.getDefaultState().withProperty(SIDE, AttachSide.fromFacing(facing));
    }

    public enum AttachSide implements IStringSerializable {
        NORTH(0, "north"),
        SOUTH(1, "south"),
        EAST(2, "east"),
        WEST(3, "west"),
        UP(4, "up"),
        DOWN(5, "down");

        private final int meta;
        private final String name;

        AttachSide(int meta, String name) {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata() {
            return meta;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public static AttachSide byMetadata(int meta) {
            for (AttachSide side : values()) {
                if (side.meta == meta) return side;
            }
            return NORTH;
        }

        public static AttachSide fromFacing(EnumFacing facing) {
            switch (facing) {
                case NORTH: return SOUTH;
                case SOUTH: return NORTH;
                case EAST:  return WEST;
                case WEST:  return EAST;
                case UP:    return DOWN;
                case DOWN:  return UP;
                default:    return NORTH;
            }
        }
    }
}
