package de.julianweinelt.ubm.misc.effect;

import de.julianweinelt.ubm.UBM;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class ModPotions {
    public static Potion BAD_OMEN;

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> event) {
        BAD_OMEN = new PotionBadOmen();
    }
}
