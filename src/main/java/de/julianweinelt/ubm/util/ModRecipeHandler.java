package de.julianweinelt.ubm.util;

import de.julianweinelt.ubm.UBM;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber
public class ModRecipeHandler {

    @SubscribeEvent
    public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event) {
        UBM.getLogger().info("Patching vanilla recipes...");
        UBM.getLogger().info("This is needed to prevent recipe collisions with our custom recipes.");
        IForgeRegistryModifiable<IRecipe> registry =
                (IForgeRegistryModifiable<IRecipe>) event.getRegistry();

        ResourceLocation woodenButton = new ResourceLocation("minecraft:wooden_button");
        if (registry.containsKey(woodenButton)) {
            registry.remove(woodenButton);
        }
    }
}
