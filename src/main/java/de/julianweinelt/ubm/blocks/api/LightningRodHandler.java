package de.julianweinelt.ubm.blocks.api;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.BlockLightningRod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class LightningRodHandler {

    private static final int RANGE = 128;

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event) {
        if (event.getWorld().isRemote) return;
        if (!(event.getEntity() instanceof EntityLightningBolt)) return;

        EntityLightningBolt bolt = (EntityLightningBolt) event.getEntity();
        BlockPos pos = bolt.getPosition();

        BlockPos rodPos = findNearestRod(bolt.world, pos);
        if (rodPos != null) {
            UBM.getLogger().info("Lightning Rod found at " + rodPos);
            event.setCanceled(true);

            EntityLightningBolt rodBolt = new EntityLightningBolt(
                    bolt.world,
                    rodPos.getX() + 0.5,
                    rodPos.getY(),
                    rodPos.getZ() + 0.5,
                    false
            );
            bolt.world.addWeatherEffect(rodBolt);
        }
    }




    private static BlockPos findNearestRod(World world, BlockPos center) {
        BlockPos closest = null;
        double closestDist = Double.MAX_VALUE;

        for (int x = -LightningRodHandler.RANGE; x <= LightningRodHandler.RANGE; x++) {
            for (int y = -LightningRodHandler.RANGE; y <= LightningRodHandler.RANGE; y++) {
                for (int z = -LightningRodHandler.RANGE; z <= LightningRodHandler.RANGE; z++) {
                    BlockPos pos = center.add(x, y, z);
                    IBlockState state = world.getBlockState(pos);
                    if (state.getBlock() instanceof BlockLightningRod) {
                        double dist = center.distanceSq(pos);
                        if (dist < closestDist) {
                            closestDist = dist;
                            closest = pos;
                        }
                    }
                }
            }
        }
        return closest;
    }
}