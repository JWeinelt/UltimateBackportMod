package de.julianweinelt.ubm.blocks;

import net.minecraft.block.BlockButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockModButton extends BlockButton {
    public BlockModButton(boolean isWood, String name) {
        super(isWood);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Override
    protected void playClickSound(@Nullable EntityPlayer player, World worldIn, BlockPos pos) {

    }

    @Override
    protected void playReleaseSound(World worldIn, BlockPos pos) {

    }
}
