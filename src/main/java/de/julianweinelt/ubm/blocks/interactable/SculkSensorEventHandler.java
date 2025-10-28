package de.julianweinelt.ubm.blocks.interactable;

import de.julianweinelt.ubm.blocks.BlockSculkSensor;
import de.julianweinelt.ubm.blocks.tiles.TileEntitySculkSensor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class SculkSensorEventHandler {

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.PlaceEvent event) {
        triggerNearbySensors(event.getWorld(), event.getPos());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        triggerNearbySensors(event.getWorld(), event.getPos());
    }

    @SubscribeEvent
    public static void onEntityMove(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity.isSneaking()) return;

        double speed = Math.abs(entity.motionX) + Math.abs(entity.motionY) + Math.abs(entity.motionZ);
        if (speed > 0.1) {
            triggerNearbySensors(entity.world, entity.getPosition());
        }
    }

    private static boolean inRange(double value, double min, double max) {
        return value >= min && value <= max;
    }
    private static boolean inRange(double value, double bound) {
        return inRange(value, value - bound, value + bound);
    }

    private static void triggerNearbySensors(World world, BlockPos sourcePos) {
        if (world.isRemote) return;

        int radius = 6;
        Iterable<BlockPos> area = BlockPos.getAllInBox(
                sourcePos.add(-radius, -radius, -radius),
                sourcePos.add(radius, radius, radius)
        );

        for (BlockPos pos : area) {
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() instanceof BlockSculkSensor) {
                TileEntity te = world.getTileEntity(pos);
                if (te instanceof TileEntitySculkSensor) {
                    ((TileEntitySculkSensor) te).triggerOnce();
                }
            }
        }
    }
}
