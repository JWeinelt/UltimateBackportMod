package de.julianweinelt.ubm.entities;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityAllay extends EntityFlying {
    public EntityAllay(World worldIn) {
        super(worldIn);
    }



    @Override
    public boolean canBeLeashedTo(EntityPlayer player) {
        return true;
    }
}