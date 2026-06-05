package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.api.EnumHorizontalFacing;
import de.julianweinelt.ubm.blocks.tiles.TileEntityVault;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class BlockVault extends Block {
    public static final PropertyEnum<EnumVaultState> STATE =
            PropertyEnum.create("state", EnumVaultState.class);
    public static final PropertyEnum<EnumHorizontalFacing> FACING =
            PropertyEnum.create("facing", EnumHorizontalFacing.class);

    public BlockVault() {
        super(Material.ROCK);

        setRegistryName("vault");
        setUnlocalizedName("vault");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        setHardness(50F);
        setResistance(50F);

        this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, EnumVaultState.INACTIVE).withProperty(FACING, EnumHorizontalFacing.NORTH));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATE, FACING);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumFacing horizontal = placer.getHorizontalFacing().getOpposite();

        return getDefaultState()
                .withProperty(FACING, EnumHorizontalFacing.fromEnumFacing(horizontal));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int stateIndex = meta & 3;
        int facingIndex = (meta >> 2) & 3;

        EnumVaultState[] states = EnumVaultState.values();
        EnumHorizontalFacing[] facings = EnumHorizontalFacing.values();

        if (stateIndex < 0 || stateIndex >= states.length) {
            stateIndex = 0;
        }

        if (facingIndex < 0 || facingIndex >= facings.length) {
            facingIndex = 0;
        }

        return getDefaultState()
                .withProperty(STATE, states[stateIndex])
                .withProperty(FACING, facings[facingIndex]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int stateIndex = state.getValue(STATE).ordinal();
        int facingIndex = state.getValue(FACING).ordinal();

        return stateIndex | (facingIndex << 2);
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
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isTranslucent(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public boolean shouldSideBeRendered(@Nonnull IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull EnumFacing side) {
        return true;
    }

    @Override
    @Nonnull
    public SoundType getSoundType(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nullable Entity entity) {
        return ModSounds.SoundTypes.TRIAL_SPAWNER;
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntityVault();
    }

    public enum EnumVaultState implements IStringSerializable {
        UNLOCKING("unlocking"),
        INACTIVE("inactive"),
        ACTIVE("active"),
        EJECTING("ejecting");

        private final String name;

        EnumVaultState(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}