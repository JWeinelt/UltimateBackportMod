package de.julianweinelt.ubm.blocks;

import de.julianweinelt.ubm.blocks.tiles.TileEntityDecoratedPot;
import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDecoratedPot extends BlockContainer {

    public BlockDecoratedPot() {
        super(Material.CLAY);
        setUnlocalizedName("decorated_pottery");
        setRegistryName("decorated_pottery");
        setHardness(0.8F);
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityDecoratedPot();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand,
                                    EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ItemStack held = player.getHeldItem(hand);
            TileEntityDecoratedPot te = (TileEntityDecoratedPot) world.getTileEntity(pos);
            if (held.getItem() instanceof ItemPotterySherd) {
                int id = ((ItemPotterySherd) held.getItem()).getSherdId(held);
                te.setSherdForSide(facing, id);
                world.markBlockRangeForRenderUpdate(pos, pos);
                if (!player.capabilities.isCreativeMode) held.shrink(1);
                return true;
            }
        }
        return true;
    }
}
