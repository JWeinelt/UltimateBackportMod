package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.tiles.TileEntityTrialSpawner;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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

public class BlockTrialSpawner extends Block {
    public static final PropertyEnum<EnumTrialSpawnerState> STATE =
            PropertyEnum.create("state", EnumTrialSpawnerState.class);

    public BlockTrialSpawner() {
        super(Material.ROCK);

        setRegistryName("trial_spawner");
        setUnlocalizedName("trial_spawner");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        setHardness(50F);
        setResistance(50F);

        this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, EnumTrialSpawnerState.INACTIVE));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATE);
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        EnumTrialSpawnerState[] values = EnumTrialSpawnerState.values();

        if (meta < 0 || meta >= values.length) {
            meta = 0;
        }

        return getDefaultState().withProperty(STATE, values[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STATE).ordinal();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
        return ModSounds.SoundTypes.TRIAL_SPAWNER;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTrialSpawner();
    }

    public enum EnumTrialSpawnerState implements IStringSerializable {

        INACTIVE("inactive"),
        ACTIVE("active"),
        EJECTING("ejecting"),
        INACTIVE_OMINOUS("inactive_ominous"),
        ACTIVE_OMINOUS("active_ominous"),
        EJECTING_OMINOUS("ejecting_ominous");

        private final String name;

        EnumTrialSpawnerState(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}