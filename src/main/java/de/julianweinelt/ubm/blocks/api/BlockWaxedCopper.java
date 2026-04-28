package de.julianweinelt.ubm.blocks.api;

import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class BlockWaxedCopper extends Block implements IWaxedCopper {

    public BlockWaxedCopper(Material material) {
        super(material);
        setSoundType(SoundType.METAL);
        setHardness(3.0f);
        setResistance(6.0f);
    }

    @Override
    public boolean onBlockActivated(@Nonnull World world, @Nonnull BlockPos pos,
                                    @Nonnull IBlockState state, EntityPlayer player,
                                    @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem(hand);
        if (held.getItem() instanceof ItemAxe) {
            return getUnwaxedVariant().map(prev -> {
                if (!world.isRemote) {
                    world.setBlockState(pos, prev.getDefaultState());
                    world.playSound(null, pos, ModSounds.ITEM_AXE_WAX_OFF,
                            SoundCategory.BLOCKS, 1.0f, 1.0f);
                    if (!player.capabilities.isCreativeMode)
                        held.damageItem(1, player);
                }
                return true;
            }).orElse(false);
        }
        return false;
    }
}