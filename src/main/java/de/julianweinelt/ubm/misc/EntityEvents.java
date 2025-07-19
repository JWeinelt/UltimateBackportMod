package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityTick(TickEvent.PlayerTickEvent event) {
        Entity entity = event.player;
        World world = entity.world;

        if (world.isRemote) return;

        BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
        BlockPos pos2 = new BlockPos(entity.posX, entity.posY + 1, entity.posZ);
        if (world.getBlockState(pos).getBlock() == ModBlocks.POWDER_SNOW ||
            world.getBlockState(pos2).getBlock() == ModBlocks.POWDER_SNOW) {
            applySlowdown(entity);
        }
    }

    private static void applySlowdown(Entity entity) {
        entity.motionX *= 0.2D;
        entity.motionZ *= 0.2D;
        entity.motionY *= 0.6D;
    }
}
