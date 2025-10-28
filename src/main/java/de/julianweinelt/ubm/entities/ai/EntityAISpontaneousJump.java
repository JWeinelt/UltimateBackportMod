package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityFrog;
import de.julianweinelt.ubm.misc.ModSounds;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISpontaneousJump extends EntityAIBase {
    private final EntityFrog frog;

    public EntityAISpontaneousJump(EntityFrog frog) {
        this.frog = frog;
        this.setMutexBits(0);
    }

    @Override
    public boolean shouldExecute() {
        return frog.onGround && frog.canJump() && frog.rand.nextFloat() < 0.1F;
    }

    @Override
    public void startExecuting() {
        frog.jumping = true;
        frog.playSound(ModSounds.FROG_LONG_JUMP, 1, 1);
        frog.motionY = 0.4D + frog.rand.nextDouble() * 0.5D;
        double dir = frog.rand.nextDouble() * 2 * Math.PI;
        frog.motionX = Math.cos(dir) * 0.3D;
        frog.motionZ = Math.sin(dir) * 0.3D;
        frog.resetJumpCooldown();
    }
}