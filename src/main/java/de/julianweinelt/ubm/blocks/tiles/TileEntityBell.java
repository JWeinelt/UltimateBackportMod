package de.julianweinelt.ubm.blocks.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityBell extends TileEntity implements ITickable {

    public int ticks;

    public boolean ringing;

    @Override
    public void update() {
        if (ringing) {
            ticks++;

            if (ticks > 40) {
                ringing = false;
                ticks = 0;
            }
        }
    }

    public void ring() {
        this.ringing = true;
        this.ticks = 0;
    }
}