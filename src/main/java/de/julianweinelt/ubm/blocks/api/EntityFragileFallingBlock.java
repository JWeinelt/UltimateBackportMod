package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFragileFallingBlock extends EntityFallingBlock {
    public EntityFragileFallingBlock(World worldIn) {
        super(worldIn);
    }


    public EntityFragileFallingBlock(World worldIn, double x, double y, double z, IBlockState state) {
        super(worldIn, x, y, z, state);
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
        if (!this.world.isRemote) {
            this.setDead();
            this.world.destroyBlock(new BlockPos(this), false);
        }
    }
}
