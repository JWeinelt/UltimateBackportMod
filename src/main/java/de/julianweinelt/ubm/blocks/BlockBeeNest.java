package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.entities.EntityBee;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockBeeNest extends Block {

    public static final PropertyInteger BEES = PropertyInteger.create("bees", 0, 3);
    public static final PropertyInteger HONEY_LEVEL = PropertyInteger.create("honey_level", 0, 5);

    public BlockBeeNest() {
        super(Material.WOOD);
        setUnlocalizedName("bee_nest");
        setRegistryName("bee_nest");
        setCreativeTab(ModCreativeTabs.UBM_TAB_BEES);
        setResistance(0.3F);
        setHardness(0.3F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(BEES, 0).withProperty(HONEY_LEVEL, 0));
    }
    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BEES, HONEY_LEVEL);
    }


    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BEES, 0).withProperty(HONEY_LEVEL, 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BEES);
    }

    @Override
    @Nonnull
    public List<ItemStack> getDrops(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, IBlockState state, int fortune) {
        java.util.List<ItemStack> drops = new java.util.ArrayList<>();
        int bees = state.getValue(BEES);
        int honey = state.getValue(HONEY_LEVEL);

        ItemStack stack = new ItemStack(this);

        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("Bees", bees);
        tag.setInteger("HoneyLevel", honey);
        stack.setTagCompound(tag);

        drops.add(stack);
        return drops;
    }

    @Override
    public void onBlockPlacedBy(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state,
                                @Nonnull EntityLivingBase placer, @Nonnull ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag == null) return;
            int bees = tag.getInteger("Bees");
            int honey = tag.getInteger("HoneyLevel");

            world.setBlockState(pos, state.withProperty(BEES, bees).withProperty(HONEY_LEVEL, honey), 2);
        }
    }

    @Override
    public void harvestBlock(@Nonnull World world, @Nonnull EntityPlayer player,
                             @Nonnull BlockPos pos, IBlockState state,
                             @Nullable TileEntity te, @Nonnull ItemStack stack) {
        int bees = state.getValue(BEES);

        boolean hasSilk = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0;

        if (!hasSilk && !world.isRemote) {
            for (int i = 0; i < bees; i++) {
                EntityBee bee = new EntityBee(world);
                bee.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                world.spawnEntity(bee);
            }
        }

        super.harvestBlock(world, player, pos, state, te, stack);
    }

    @Override
    public boolean getTickRandomly() {
        return true;
    }

    @Override
    public void randomTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
        super.randomTick(world, pos, state, rand);

        int bees = state.getValue(BEES);
        int honey = state.getValue(HONEY_LEVEL);

        if (bees > 0 && honey < 5) {
            world.setBlockState(pos, state.withProperty(HONEY_LEVEL, honey + 1), 2);
        }

        if (bees > 0) {
            world.setBlockState(pos, state.withProperty(BEES, bees - 1), 2);
            EntityBee bee = new EntityBee(world);
            bee.setPosition(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() - 0.5);
            world.spawnEntity(bee);
            bee.setNestPos(pos);
        }
    }
    @Override
    public boolean onBlockActivated(@Nonnull World world, @Nonnull BlockPos pos, IBlockState state,
                                    EntityPlayer player, @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem(hand);

        if (state.getValue(HONEY_LEVEL) >= 5) {
            if (held.getItem() == Items.GLASS_BOTTLE) {
                if (!world.isRemote) {
                    player.addItemStackToInventory(new ItemStack(ModItems.HONEY_BOTTLE));
                    world.setBlockState(pos, state.withProperty(HONEY_LEVEL, 0), 2);
                    if (noCampfireNearby(world, pos)) {
                        int bees = state.getValue(BEES);
                        for (int i = 0; i < bees; i++) {
                            EntityBee bee = new EntityBee(world);
                            bee.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                            world.spawnEntity(bee);
                            bee.setAggressive(player);
                            bee.setNestPos(pos);
                        }
                    }
                }
                return true;
            } else if (held.getItem() == Items.SHEARS) {
                if (!world.isRemote) {
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.HONEYCOMB, 1)));
                    world.setBlockState(pos, state.withProperty(HONEY_LEVEL, 0), 2);
                    if (noCampfireNearby(world, pos)) {
                        int bees = state.getValue(BEES);
                        for (int i = 0; i < bees; i++) {
                            EntityBee bee = new EntityBee(world);
                            bee.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                            world.spawnEntity(bee);
                            bee.setAggressive(player);
                            bee.setNestPos(pos);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean noCampfireNearby(World worldIn, BlockPos pos) {
        int posY = pos.getY();
        for (int y = posY; y > posY - 5; y--) {
            if (worldIn.getBlockState(new BlockPos(pos.getX(), y, pos.getZ())).getBlock() == Blocks.FIRE) {
                return false;
            }
        }
        return true;
    }
}