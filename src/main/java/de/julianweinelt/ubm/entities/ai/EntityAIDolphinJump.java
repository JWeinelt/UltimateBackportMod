package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityDolphin;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

public class EntityAIDolphinJump extends EntityAIBase {

    private final EntityDolphin dolphin;
    private int cooldown;

    public EntityAIDolphinJump(EntityDolphin dolphin) {
        this.dolphin = dolphin;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        if (!dolphin.isInWater()) return false;
        if (cooldown > 0) {
            cooldown--;
            return false;
        }
        return dolphin.getRNG().nextInt(100) == 0; // ~1% Chance pro Tick
    }

    @Override
    public void startExecuting() {
        dolphin.motionY = 0.6D; // nach oben springen
        dolphin.motionX += (dolphin.getRNG().nextDouble() - 0.5D) * 0.2D;
        dolphin.motionZ += (dolphin.getRNG().nextDouble() - 0.5D) * 0.2D;
        cooldown = 100; // ca. 5 Sekunden warten
    }

    @Override
    public boolean shouldContinueExecuting() {
        return false; // nur ein kurzer Sprung
    }
}
