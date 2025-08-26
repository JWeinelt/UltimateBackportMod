package de.julianweinelt.ubm.entities.ai;

import de.julianweinelt.ubm.entities.EntityDolphin;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIDolphinBreath extends EntityAIBase {

    private final EntityDolphin dolphin;

    public EntityAIDolphinBreath(EntityDolphin dolphin) {
        this.dolphin = dolphin;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return dolphin.isInWater() && dolphin.getAir() < 100;
    }

    @Override
    public void startExecuting() {
        dolphin.getNavigator().tryMoveToXYZ(
                dolphin.posX,
                dolphin.posY + 5,
                dolphin.posZ,
                1.2D
        );
    }

    @Override
    public boolean shouldContinueExecuting() {
        return dolphin.isInWater() && dolphin.getAir() < 100;
    }
}
