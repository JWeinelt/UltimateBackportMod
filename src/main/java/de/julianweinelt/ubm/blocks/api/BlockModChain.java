package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SuppressWarnings("deprecation")
public class BlockModChain extends Block {

    public static final PropertyEnum<AttachSide> SIDE = PropertyEnum.create("facing", AttachSide.class);

    public BlockModChain() {
        super(Material.IRON);
        this.setHardness(3.0F);

        this.setDefaultState(this.blockState.getBaseState().withProperty(SIDE, AttachSide.NORTH));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SIDE);
    }


    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(SIDE, AttachSide.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(SIDE).getMetadata();
    }

    @Override
    public boolean isOpaqueCube(@Nonnull IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(@Nonnull IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }


    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta,
                                            @Nonnull EntityLivingBase placer) {
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
        @Nonnull
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
                case NORTH:
                    return NORTH;
                case SOUTH:
                    return SOUTH;
                case EAST:
                    return EAST;
                case WEST:
                    return WEST;
                case UP:
                    return UP;
                case DOWN:
                    return DOWN;
                default:
                    return NORTH;
            }
        }
    }
}