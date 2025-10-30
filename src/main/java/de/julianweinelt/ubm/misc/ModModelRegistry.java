package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = UBM.MODID)
public class ModModelRegistry {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomMeshDefinition(ModItems.OMINOUS_BOTTLE, stack ->
            new ModelResourceLocation(ModItems.OMINOUS_BOTTLE.getRegistryName(), "inventory"));

        Item item = ModItems.SPYGLASS;
        item.addPropertyOverride(new ResourceLocation("in_hand"), (stack, world, entity) -> {
            if (entity == null) return 0.0F;
            boolean holding = (entity.getHeldItemMainhand() == stack || entity.getHeldItemOffhand() == stack) && entity.getActiveItemStack() == stack;
            return holding ? 1.0F : 0.0F;
        });
    }
}
