package de.julianweinelt.ubm.blocks.api;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class BlockCopperBulb extends BlockWeatherableCopper {
    public static final PropertyBool LIT = PropertyBool.create("lit");
    public static final PropertyBool POWERED = PropertyBool.create("powered");

    private Supplier<Block> weatheredVariant;
    private Supplier<Block> unweatheredVariant;
    private Supplier<Block> waxedVariant;

    private String type;

    public BlockCopperBulb(String type,
                           @Nullable Supplier<Block> weatheredVariant, @Nullable Supplier<Block> unweatheredVariant,
                           @Nonnull Supplier<Block> waxedVariant) {
        super(Material.ROCK);
        this.type = type;
        this.weatheredVariant = weatheredVariant;
        this.unweatheredVariant = unweatheredVariant;
        this.waxedVariant = waxedVariant;
        String name = (type.isEmpty() ? "" : type + "_") + "copper_bulb";
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        setCreativeTab(ModCreativeTabs.UBM_TAB_CAVES);

        setHarvestLevel("pickaxe", 1);
        setHardness(3F);
        setResistance(6);

        setDefaultState(this.blockState.getBaseState().withProperty(LIT, false).withProperty(POWERED, false));
        setLightLevel(0.0F);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, LIT, POWERED);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {

        boolean lit = (meta & 1) != 0;
        boolean powered = (meta & 2) != 0;

        return this.getDefaultState()
                .withProperty(LIT, lit)
                .withProperty(POWERED, powered);
    }

    @Override
    public int getMetaFromState(IBlockState state) {

        int meta = 0;

        if (state.getValue(LIT)) meta |= 1;
        if (state.getValue(POWERED)) meta |= 2;

        return meta;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!world.isRemote) {
            world.scheduleUpdate(pos, this, 1);
        }
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, java.util.Random rand) {

        boolean powered = world.isBlockPowered(pos);

        if (powered) {
            world.setBlockState(
                    pos,
                    state.withProperty(LIT, !state.getValue(LIT)).withProperty(POWERED, true),
                    2
            );
        } else {
            world.setBlockState(
                    pos,
                    state.withProperty(POWERED, false),
                    2
            );
        }
    }

    @Override
    public int getLightValue(IBlockState state) {
        if (state.getValue(LIT)) {
            if (type.isEmpty()) return 15;
            else if (type.equals("exposed")) return 12;
            else if (type.equals("weathered")) return 8;
            else if (type.equals("oxidized")) return 4;
        }
        return 0;
    }

    @Override
    public Optional<Block> getWeatheredVariant() {
        return weatheredVariant != null ? Optional.ofNullable(weatheredVariant.get()) : Optional.empty();
    }

    @Override
    public Optional<Block> getWaxedVariant() {
        return waxedVariant != null ? Optional.ofNullable(waxedVariant.get()) : Optional.empty();
    }

    @Override
    public Optional<Block> getUnweatheredVariant() {
        return unweatheredVariant != null ? Optional.ofNullable(unweatheredVariant.get()) : Optional.empty();
    }
}
