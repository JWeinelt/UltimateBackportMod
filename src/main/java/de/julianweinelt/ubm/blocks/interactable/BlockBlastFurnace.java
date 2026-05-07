package de.julianweinelt.ubm.blocks.interactable;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.api.EnumHorizontalFacing;
import de.julianweinelt.ubm.blocks.interactable.smithing.GuiHandler;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBlastFurnace;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockBlastFurnace extends BlockContainer {
    public static final PropertyEnum<EnumHorizontalFacing> FACING =
            PropertyEnum.create("facing", EnumHorizontalFacing.class);

    public static final PropertyBool LIT = PropertyBool.create("lit");

    public BlockBlastFurnace() {
        super(Material.ROCK);
        this.setRegistryName("blast_furnace");
        this.setUnlocalizedName("blast_furnace");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_PILLAGE);
        setHardness(3.5f);
        setResistance(6.0f);
        setSoundType(SoundType.STONE);
        setDefaultState(blockState.getBaseState().withProperty(LIT, false)
                .withProperty(FACING, EnumHorizontalFacing.NORTH));
    }


    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LIT, FACING);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(FACING).ordinal();
        if (state.getValue(LIT)) {
            meta |= 4;
        }
        return meta;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        EnumHorizontalFacing facing = EnumHorizontalFacing.values()[meta & 3];
        boolean lit = (meta & 4) != 0;

        return this.getDefaultState()
                .withProperty(FACING, facing)
                .withProperty(LIT, lit);
    }

    @Override
    @Nonnull
    public IBlockState getStateForPlacement(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState()
                .withProperty(FACING, EnumHorizontalFacing.fromFacing(placer.getHorizontalFacing().getOpposite()));
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

    public static void setState(boolean burning, World world, BlockPos pos) {
        IBlockState old = world.getBlockState(pos);

        TileEntity oldTe = world.getTileEntity(pos);
        NBTTagCompound savedData = null;
        if (oldTe != null) {
            savedData = oldTe.writeToNBT(new NBTTagCompound());
        }

        world.setBlockState(pos, old.withProperty(LIT, burning), 3);

        if (savedData != null) {
            TileEntity newTe = world.getTileEntity(pos);
            if (newTe != null) {
                newTe.readFromNBT(savedData);
            }
        }
    }


    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityBlastFurnace();
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(UBM.instance, GuiHandler.BLAST_FURNACE,
                    world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }


    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityBlastFurnace) {
            TileEntityBlastFurnace furnace = (TileEntityBlastFurnace) te;
            for (int i = 0; i < furnace.getSizeInventory(); i++) {
                ItemStack stack = furnace.getStackInSlot(i);
                if (!stack.isEmpty()) {
                    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                }
            }
        }
        super.breakBlock(world, pos, state);
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if (!state.getValue(LIT)) return;

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.0 + rand.nextDouble() * 6.0 / 16.0;
        double z = pos.getZ() + 0.5;

        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y + 1.0, z, 0, 0, 0);
        world.spawnParticle(EnumParticleTypes.FLAME, x, y + 1.0, z, 0, 0, 0);
    }

    @Override
    public int getLightValue(IBlockState state) {
        return state.getValue(LIT) ? 13 : 0;
    }
}