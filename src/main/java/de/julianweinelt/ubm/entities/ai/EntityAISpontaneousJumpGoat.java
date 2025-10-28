package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityFrog;
import de.julianweinelt.ubm.entities.EntityGoat;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISpontaneousJumpGoat extends EntityAIBase {
    private final EntityGoat goat;

    public EntityAISpontaneousJumpGoat(EntityGoat goat) {
        this.goat = goat;
        this.setMutexBits(0);
    }

    @Override
    public boolean shouldExecute() {
        return goat.onGround && goat.canJump() && goat.rand.nextFloat() < 0.1F;
    }

    @Override
    public void startExecuting() {
        goat.jumping = true;
        goat.playSound(ModSounds.GOAT_JUMP, 1, 1);
        goat.motionY = 0.4D + goat.rand.nextDouble() * 0.5D;
        double dir = goat.rand.nextDouble() * 2 * Math.PI;
        goat.motionX = Math.cos(dir) * 0.3D;
        goat.motionZ = Math.sin(dir) * 0.3D;
        goat.resetJumpCooldown();
        goat.fallDistance = 0;
    }
}