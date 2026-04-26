package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockModWall extends Block {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST  = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST  = PropertyBool.create("west");
    public static final PropertyBool UP    = PropertyBool.create("up");

    protected static final AxisAlignedBB[] CLIP_AABB_BY_INDEX;

    protected static final AxisAlignedBB[] AABB_BY_INDEX = new AxisAlignedBB[]{
            new AxisAlignedBB(0.25F, 0.0F, 0.25F,0.75F,1.0F,0.75F), 
            new AxisAlignedBB(0.25F,0.0F,0.25F,0.75F,1.0F,1.0F), 
            new AxisAlignedBB(0.0F,0.0F,0.25F,0.75F,1.0F,0.75F), 
            new AxisAlignedBB(0.0F,0.0F,0.25F,0.75F,1.0F,1.0F), 
            new AxisAlignedBB(0.25F,0.0F,0.0F,0.75F,1.0F,0.75F), 
            new AxisAlignedBB(0.3125F,0.0F,0.0F,0.6875F,0.875F,1.0F),
            new AxisAlignedBB(0.0F,0.0F,0.0F,0.75F,1.0F,0.75F),
            new AxisAlignedBB(0.0F,0.0F,0.0F,0.75F,1.0F,1.0F),
            new AxisAlignedBB(0.25F,0.0F,0.25F,1.0F,1.0F,0.75F),
            new AxisAlignedBB(0.25F,0.0F,0.25F,1.0F,1.0F,1.0F), 
            new AxisAlignedBB(0.0F,0.0F,0.3125F,1.0F,0.875F,0.6875F),
            new AxisAlignedBB(0.0F,0.0F,0.25F,1.0F,1.0F,1.0F), 
            new AxisAlignedBB(0.25F,0.0F,0.0F,1.0F,1.0F,0.75F), 
            new AxisAlignedBB(0.25F,0.0F,0.0F,1.0F,1.0F,1.0F), 
            new AxisAlignedBB(0.0F,0.0F,0.0F,1.0F,1.0F,0.75F), 
            new AxisAlignedBB(0.0F,0.0F,0.0F,1.0F,1.0F,1.0F)};

    public BlockModWall(Block parent, String name) {
        super(parent.getDefaultState().getMaterial());
        setRegistryName(name);
        setUnlocalizedName(name);
        setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(NORTH, false)
                .withProperty(EAST, false)
                .withProperty(SOUTH, false)
                .withProperty(WEST, false)
                .withProperty(UP, false));
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, NORTH, EAST, SOUTH, WEST, UP);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Nonnull
    public AxisAlignedBB getBoundingBox(@Nonnull IBlockState blockState,
                                        @Nonnull IBlockAccess blockAccess,
                                        @Nonnull BlockPos blockPos) {
        blockState = this.getActualState(blockState, blockAccess, blockPos);
        return AABB_BY_INDEX[getAABBIndex(blockState)];
    }

    public void addCollisionBoxToList(@Nonnull IBlockState blockState,
                                      @Nonnull World worldIn,
                                      @Nonnull BlockPos blockPos,
                                      @Nonnull AxisAlignedBB hitBox,
                                      @Nonnull List<AxisAlignedBB> collisionList,
                                      @Nullable Entity entity,
                                      boolean addToList) {
        if (!addToList) {
            blockState = this.getActualState(blockState, worldIn, blockPos);
        }

        addCollisionBoxToList(blockPos, hitBox, collisionList, CLIP_AABB_BY_INDEX[getAABBIndex(blockState)]);
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(@Nonnull IBlockState blockState,
                                                 @Nonnull IBlockAccess blockAccess,
                                                 @Nonnull BlockPos blockPos) {
        blockState = this.getActualState(blockState, blockAccess, blockPos);
        return CLIP_AABB_BY_INDEX[getAABBIndex(blockState)];
    }

    private static int getAABBIndex(IBlockState p_getAABBIndex_0_) {
        int i = 0;
        if (p_getAABBIndex_0_.getValue(NORTH)) {
            i |= 1 << EnumFacing.NORTH.getHorizontalIndex();
        }

        if (p_getAABBIndex_0_.getValue(EAST)) {
            i |= 1 << EnumFacing.EAST.getHorizontalIndex();
        }

        if (p_getAABBIndex_0_.getValue(SOUTH)) {
            i |= 1 << EnumFacing.SOUTH.getHorizontalIndex();
        }

        if (p_getAABBIndex_0_.getValue(WEST)) {
            i |= 1 << EnumFacing.WEST.getHorizontalIndex();
        }

        return i;
    }

    public boolean isFullCube(@Nonnull IBlockState blockState) {
        return false;
    }

    public boolean isPassable(@Nonnull IBlockAccess blockState, @Nonnull BlockPos pos) {
        return false;
    }

    public boolean isOpaqueCube(@Nonnull IBlockState blockState) {
        return false;
    }

    private boolean canConnectTo(IBlockAccess p_canConnectTo_1_, BlockPos p_canConnectTo_2_, EnumFacing p_canConnectTo_3_) {
        IBlockState iblockstate = p_canConnectTo_1_.getBlockState(p_canConnectTo_2_);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(p_canConnectTo_1_, p_canConnectTo_2_, p_canConnectTo_3_);
        boolean flag = blockfaceshape == BlockFaceShape.MIDDLE_POLE_THICK || blockfaceshape == BlockFaceShape.MIDDLE_POLE && block instanceof BlockFenceGate;
        return !isExcepBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID || flag;
    }

    protected static boolean isExcepBlockForAttachWithPiston(Block p_isExcepBlockForAttachWithPiston_0_) {
        return Block.isExceptBlockForAttachWithPiston(p_isExcepBlockForAttachWithPiston_0_) || p_isExcepBlockForAttachWithPiston_0_ == Blocks.BARRIER || p_isExcepBlockForAttachWithPiston_0_ == Blocks.MELON_BLOCK || p_isExcepBlockForAttachWithPiston_0_ == Blocks.PUMPKIN || p_isExcepBlockForAttachWithPiston_0_ == Blocks.LIT_PUMPKIN;
    }

    @Override
    public void getSubBlocks(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks) {
        stacks.add(new ItemStack(this));
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(@Nonnull IBlockState blockState,
                                        @Nonnull IBlockAccess blockAccess,
                                        @Nonnull BlockPos blockPos,
                                        @Nonnull EnumFacing facing) {
        return facing != EnumFacing.DOWN || super.shouldSideBeRendered(blockState, blockAccess, blockPos, facing);
    }

    @Nonnull
    public IBlockState getActualState(IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos blockPos) {
        boolean flag = this.canWallConnectTo(blockAccess, blockPos, EnumFacing.NORTH);
        boolean flag1 = this.canWallConnectTo(blockAccess, blockPos, EnumFacing.EAST);
        boolean flag2 = this.canWallConnectTo(blockAccess, blockPos, EnumFacing.SOUTH);
        boolean flag3 = this.canWallConnectTo(blockAccess, blockPos, EnumFacing.WEST);
        boolean flag4 = flag && !flag1 && flag2 && !flag3 || !flag && flag1 && !flag2 && flag3;
        return blockState.withProperty(UP, !flag4 || !blockAccess.isAirBlock(blockPos.up())).withProperty(NORTH, flag).withProperty(EAST, flag1).withProperty(SOUTH, flag2).withProperty(WEST, flag3);
    }

    @Nonnull
    public BlockFaceShape getBlockFaceShape(@Nonnull IBlockAccess blockAccess,
                                            @Nonnull IBlockState blockState,
                                            @Nonnull BlockPos blockPos,
                                            @Nonnull EnumFacing facing) {
        return facing != EnumFacing.UP && facing != EnumFacing.DOWN ?
                BlockFaceShape.MIDDLE_POLE_THICK : BlockFaceShape.CENTER_BIG;
    }

    public boolean canBeConnectedTo(@Nonnull IBlockAccess canBeConnectedTo, BlockPos p_canBeConnectedTo_2_, EnumFacing p_canBeConnectedTo_3_) {
        return this.canConnectTo(canBeConnectedTo, p_canBeConnectedTo_2_.offset(p_canBeConnectedTo_3_), p_canBeConnectedTo_3_.getOpposite());
    }

    private boolean canWallConnectTo(IBlockAccess p_canWallConnectTo_1_, BlockPos p_canWallConnectTo_2_, EnumFacing p_canWallConnectTo_3_) {
        BlockPos other = p_canWallConnectTo_2_.offset(p_canWallConnectTo_3_);
        Block block = p_canWallConnectTo_1_.getBlockState(other).getBlock();
        return block.canBeConnectedTo(p_canWallConnectTo_1_, other, p_canWallConnectTo_3_.getOpposite()) || this.canConnectTo(p_canWallConnectTo_1_, other, p_canWallConnectTo_3_.getOpposite());
    }

    static {
        CLIP_AABB_BY_INDEX = new AxisAlignedBB[]{AABB_BY_INDEX[0].setMaxY((double)1.5F), AABB_BY_INDEX[1].setMaxY((double)1.5F), AABB_BY_INDEX[2].setMaxY((double)1.5F), AABB_BY_INDEX[3].setMaxY((double)1.5F), AABB_BY_INDEX[4].setMaxY((double)1.5F), AABB_BY_INDEX[5].setMaxY((double)1.5F), AABB_BY_INDEX[6].setMaxY((double)1.5F), AABB_BY_INDEX[7].setMaxY((double)1.5F), AABB_BY_INDEX[8].setMaxY((double)1.5F), AABB_BY_INDEX[9].setMaxY((double)1.5F), AABB_BY_INDEX[10].setMaxY((double)1.5F), AABB_BY_INDEX[11].setMaxY((double)1.5F), AABB_BY_INDEX[12].setMaxY((double)1.5F), AABB_BY_INDEX[13].setMaxY((double)1.5F), AABB_BY_INDEX[14].setMaxY((double)1.5F), AABB_BY_INDEX[15].setMaxY((double)1.5F)};
    }
}