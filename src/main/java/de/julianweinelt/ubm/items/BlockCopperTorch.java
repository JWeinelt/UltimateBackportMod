package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import de.julianweinelt.ubm.particles.ParticleCopperFlame;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockCopperTorch extends BlockTorch {

    public BlockCopperTorch(String name) {
        setRegistryName("ubm", name);
        setUnlocalizedName(name);
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        setHardness(0.0F);
        setLightLevel(1.0F);
        setSoundType(SoundType.WOOD);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(@Nonnull IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        //super.randomDisplayTick(stateIn, worldIn, pos, rand);
        EnumFacing enumfacing = stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;

        if (worldIn.isRemote) {

            if (enumfacing.getAxis().isHorizontal()) {
                EnumFacing enumfacing1 = enumfacing.getOpposite();
                Minecraft.getMinecraft().effectRenderer.addEffect(
                        new ParticleCopperFlame(
                                worldIn, d0 + 0.27D * (double) enumfacing1.getFrontOffsetX(), d1 + 0.22D, d2 + 0.27D * (double) enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D
                        )
                );
            } else {
                Minecraft.getMinecraft().effectRenderer.addEffect(
                        new ParticleCopperFlame(
                                worldIn,  d0, d1, d2, 0.0D, 0.0D, 0.0D
                        )
                );
            }
        }
    }
}