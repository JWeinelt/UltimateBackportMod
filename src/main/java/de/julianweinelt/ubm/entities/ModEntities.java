package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.models.ModelBee;
import de.julianweinelt.ubm.entities.models.ModelFrog;
import de.julianweinelt.ubm.entities.models.ModelGoat;
import de.julianweinelt.ubm.entities.models.ModelTurtle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static de.julianweinelt.ubm.UBM.MODID;

public class ModEntities {
    public static void init() {
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "bee"),
                EntityBee.class,
                "Bee",
                1,
                UBM.instance,
                64, 1, true,
                0x00FF00, 0xFF0000
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "frog"),
                EntityFrog.class,
                "Frog",
                2,
                UBM.instance,
                64, 1, true,
                0x00FF00, 0xFF0000
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "turtle"),
                EntityTurtle.class,
                "Turtle",
                3,
                UBM.instance,
                64, 1, true,
                0x00FF00, 0xFF0000
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "goat"),
                EntityGoat.class,
                "Goat",
                4,
                UBM.instance,
                64, 1, true,
                0x00FF00, 0xFF0000
        );
    }

    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, renderManager ->
                new RenderLiving<EntityBee>(renderManager, new ModelBee(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityBee entity) {
                        if (entity.getBeeState().equals(EntityBee.BeeState.RETURN_TO_NEST)) return new ResourceLocation(MODID, "textures/entity/bee/bee_nectar.png");
                        if (entity.isAggressive()) return new ResourceLocation(MODID, "textures/entity/bee/bee_angry.png");
                        return new ResourceLocation(MODID, "textures/entity/bee/bee.png");
                    }
                }
        );

        RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, renderManager ->
                new RenderLiving<EntityFrog>(renderManager, new ModelFrog(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityFrog entity) {
                        switch (entity.getFrogType()) {
                            case COOL:
                                return new ResourceLocation(MODID, "textures/entity/frog/cool_frog.png");
                            case WARM:
                                return new ResourceLocation(MODID, "textures/entity/frog/warm_frog.png");
                        }
                        return new ResourceLocation(MODID, "textures/entity/frog/temperate_frog.png");
                    }
                }
        );

        RenderingRegistry.registerEntityRenderingHandler(EntityTurtle.class, renderManager ->
                new RenderLiving<EntityTurtle>(renderManager, new ModelTurtle(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityTurtle entity) {
                        return new ResourceLocation(MODID, "textures/entity/turtle.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityGoat.class, renderManager ->
                new RenderLiving<EntityGoat>(renderManager, new ModelGoat(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(EntityGoat entity) {
                        return new ResourceLocation(MODID, "textures/entity/goat.png");
                    }
                }
        );
    }
}
