package de.julianweinelt.ubm.effects;

import de.julianweinelt.ubm.UBM;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModEffectHandler {
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent e) {
        EntityLivingBase entity = e.getEntityLiving();
        if (e.getEntityLiving().isPotionActive(ModEffects.INFESTED)) {
            World w = entity.world;
            if (w.rand.nextFloat() < 0.1F) {
                spawnSilverfish(entity);
                spawnSilverfish(entity);
            }
        }
    }

    private static void spawnSilverfish(EntityLivingBase entity) {
        World w = entity.world;
        EntitySilverfish silverfish = new EntitySilverfish(w);
        silverfish.setLocationAndAngles(
                entity.posX + w.rand.nextInt(4) - 2,
                entity.posY,
                entity.posZ + w.rand.nextInt(4) - 2,
                w.rand.nextFloat() * 360F,
                0
        );
        w.spawnEntity(silverfish);
    }
}
