package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.BlockAmethystBud;
import de.julianweinelt.ubm.blocks.plant.BlockGlowLichen;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
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

public class BlockLightningRod extends Block {

    public static final PropertyBool POWERED = PropertyBool.create("powered");

    public static final PropertyEnum<AttachSide> SIDE = PropertyEnum.create("facing", AttachSide.class);


    public BlockLightningRod() {
        super(Material.ROCK);
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.setRegistryName("lightning_rod");
        this.setUnlocalizedName("lightning_rod");
        this.setHardness(3.0F);

        this.setDefaultState(this.blockState.getBaseState().withProperty(SIDE, AttachSide.NORTH).withProperty(POWERED, Boolean.FALSE));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SIDE, POWERED);
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
                case NORTH: return NORTH;
                case SOUTH: return SOUTH;
                case EAST:  return EAST;
                case WEST:  return WEST;
                case UP:    return UP;
                case DOWN:  return DOWN;
                default:    return NORTH;
            }
        }
    }
}