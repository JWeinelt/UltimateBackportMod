package de.julianweinelt.ubm.blocks.plant;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.blocks.api.BlockCaveVine;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockGlowBerryVine extends BlockCaveVine implements IGrowable{

    public static final PropertyBool BERRIES = PropertyBool.create("berries");
    public static final PropertyBool END = PropertyBool.create("end");



    public BlockGlowBerryVine() {
        super(5);
        this.setRegistryName("glow_berries");
        this.setUnlocalizedName("glow_berries");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BERRIES, Boolean.FALSE).withProperty(END, Boolean.FALSE));
    }
    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BERRIES, END);
    }

    @Override
    public int getLightValue(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos) {
        return state.getValue(BERRIES) ? 14 : 0;
    }

    @Override
    public boolean onBlockActivated(@Nonnull World world, @Nonnull BlockPos pos, IBlockState state,
                                    @Nonnull EntityPlayer player,
                                    @Nonnull EnumHand hand,
                                    @Nonnull EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {

        if (state.getValue(END)) {
            ItemStack item = player.getHeldItem(hand);
            if (!item.isEmpty() && item.getItem() == Items.SHEARS) {
                if (!world.isRemote) {
                    world.setBlockState(pos, state.withProperty(END, false), 2);
                }
            }
        }

        if (state.getValue(BERRIES)) {
            spawnAsEntity(world, pos, new ItemStack(ModItems.GLOW_BERRIES, 1));

            world.setBlockState(pos, state.withProperty(BERRIES, false), 2);

            world.playSound(null, pos, net.minecraft.init.SoundEvents.BLOCK_GRASS_BREAK,
                    net.minecraft.util.SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;
        }

        return false;
    }



    @Override
    public void grow(World worldIn, @Nonnull Random rand, BlockPos pos, @Nonnull IBlockState state) {
        BlockPos below = pos.down();
        if (worldIn.isAirBlock(below)) {
            if (rand.nextFloat() < 0.11F) {
                worldIn.setBlockState(below, this.getDefaultState().withProperty(BERRIES, Boolean.TRUE).withProperty(END, Boolean.TRUE));
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(END, Boolean.FALSE));
            }
            else {
                worldIn.setBlockState(below, this.getDefaultState().withProperty(BERRIES, Boolean.FALSE).withProperty(END, Boolean.TRUE));
                worldIn.setBlockState(pos, this.getDefaultState().withProperty(END, Boolean.FALSE));
            }
        }
    }
    @Override
    public boolean canUseBonemeal(@Nonnull World worldIn, Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return rand.nextFloat() < 0.75F;
    }



    @Override
    protected void tryGrow(World world, BlockPos pos, IBlockState state, Random rand) {
        BlockPos below = pos.down();
        if (world.isAirBlock(below)) {
            world.setBlockState(below, this.getDefaultState().withProperty(BERRIES, rand.nextBoolean()));
        }
    }
    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BERRIES, meta == 1);
    }
    @Override
    public void neighborChanged(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos,
                                @Nonnull Block blockIn, @Nonnull BlockPos fromPos) {
        super.neighborChanged(state, world, pos, blockIn, fromPos);

        if (!world.getBlockState(pos.up()).getMaterial().isSolid() &&
                !ModBlocks.GLOW_BERRIES.getRegistryName().equals(world.getBlockState(pos.up()).getBlock().getRegistryName())) {
            world.destroyBlock(pos, true);
        }
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BERRIES) ? 1 : 0;
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
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState, @Nonnull IBlockAccess worldIn, @Nonnull BlockPos pos) {
        return NULL_AABB;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}