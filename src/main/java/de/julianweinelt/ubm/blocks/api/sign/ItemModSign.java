package de.julianweinelt.ubm.blocks.api.sign;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSign;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemModSign extends Item {
    private final Block standing;
    private final Block wall;

    public ItemModSign(String name, Block standing, Block wall) {
        this.standing = standing;
        this.wall = wall;
        this.setRegistryName(name + "_sign");
        this.setUnlocalizedName(name + "_sign");

        setMaxStackSize(16);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing == EnumFacing.DOWN) return EnumActionResult.FAIL;

        BlockPos placePos = pos.offset(facing);

        if (!player.canPlayerEdit(placePos, facing, player.getHeldItem(hand))) return EnumActionResult.FAIL;

        if (!standing.canPlaceBlockAt(worldIn, placePos)) return EnumActionResult.FAIL;

        IBlockState state;

        if (facing == EnumFacing.UP) {
            state = standing.getDefaultState();
        } else {
            state = wall.getDefaultState().withProperty(BlockHorizontal.FACING, facing);
        }

        worldIn.setBlockState(placePos, state, 3);

        TileEntity te = worldIn.getTileEntity(placePos);
        if (te instanceof TileEntitySign) {
            player.openEditSign((TileEntitySign)te);
        }
        player.getHeldItem(hand).shrink(1);
        return EnumActionResult.SUCCESS;
    }
}
