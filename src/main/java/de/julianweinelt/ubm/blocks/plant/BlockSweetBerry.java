package de.julianweinelt.ubm.blocks.plant;

import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockSweetBerry extends BlockBush implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

    public BlockSweetBerry() {
        super(Material.PLANTS);
        setUnlocalizedName("sweet_berry_bush");
        setRegistryName("sweet_berry_bush");
        setHardness(0.0F);
        setResistance(0.0F);
        setSoundType(SoundType.PLANT);
        setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        setDefaultState(this.blockState.getBaseState().withProperty(AGE, 0));
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return true;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 60;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        Block[] possibleBlocks = {
                Blocks.DIRT,
                Blocks.GRASS,
                //TODO: Rooted dirt
                Blocks.FARMLAND,
                //TODO: Find out which ID podzol has, if any
                Blocks.MYCELIUM,
                ModBlocks.MOSS_BLOCK,
                //TODO: Mud
                //TODO: Muddy Mangrove roots
        };
        BlockPos down = pos.down();
        Block block = worldIn.getBlockState(down).getBlock();
        return Arrays.asList(possibleBlocks).contains(block);
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.4;
        entityIn.motionZ *= 0.4;

        if (!worldIn.isRemote && entityIn instanceof EntityPlayer) {
            if (entityIn.ticksExisted % 10 == 0) {
                entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
            }
        }
    }

    @Override
    public boolean requiresUpdates() {
        return true;
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(AGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AGE);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(worldIn, pos, state, rand);

        int age = state.getValue(AGE);
        if (age < 3 && rand.nextInt(40) == 0) {
            worldIn.setBlockState(pos, state.withProperty(AGE, age + 1), 2);
        }
    }


    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return state.getValue(AGE) < 3;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int age = state.getValue(AGE);
        if (age < 3) {
            worldIn.setBlockState(pos, state.withProperty(AGE, age + 1), 2);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.SWEET_BERRY;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        int age = state.getValue(AGE);
        if (age == 2) return random.nextInt(1) + 1;
        else if (age == 3) return 2 + random.nextInt(1);
        return 0;
    }


    @Override
    public boolean onBlockActivated(
            World worldIn,
            BlockPos pos,
            IBlockState state,
            EntityPlayer playerIn,
            EnumHand hand,
            EnumFacing facing,
            float hitX,
            float hitY,
            float hitZ) {

        if (!worldIn.isRemote) {
            int age = state.getValue(AGE);
            if (age <= 2) return false;

            ItemStack berries = new ItemStack(ModItems.SWEET_BERRY, new Random().nextInt(2) + 1);
            spawnAsEntity(worldIn, pos, berries);

            worldIn.setBlockState(pos, state.withProperty(AGE, 1), 2);
        }

        return true;
    }
}