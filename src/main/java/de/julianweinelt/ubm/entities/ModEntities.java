package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import de.julianweinelt.ubm.entities.models.*;
import de.julianweinelt.ubm.entities.render.RenderBambooRaft;
import de.julianweinelt.ubm.entities.render.RenderCustomWolf;
import de.julianweinelt.ubm.entities.render.RenderFox;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nullable;

import static de.julianweinelt.ubm.UBM.MODID;

public class ModEntities {
    public static void init() {
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "bee"),
                EntityBee.class,
                "Bee",
                1,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "frog"),
                EntityFrog.class,
                "Frog",
                2,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "turtle"),
                EntityTurtle.class,
                "Turtle",
                3,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "goat"),
                EntityGoat.class,
                "Goat",
                4,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "bamboo_raft"),
                EntityBambooRaft.class,
                "bamboo_raft",
                5,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, "warden"),
                EntityWarden.class,
                "warden",
                6,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "custom_wolf"),
                EntityCustomWolf.class,
                "CustomWolf",
                7,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "fox"),
                EntityFox.class,
                "Fox",
                8,
                UBM.instance,
                64, 1, true
        );

    }

    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, renderManager ->
                new RenderLiving<EntityBee>(renderManager, new ModelBee(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityBee entity) {
                        if (entity.getBeeState().equals(EntityBee.BeeState.RETURN_TO_NEST)) return new ResourceLocation(MODID, "textures/entity/bee/bee_nectar.png");
                        if (entity.isAggressive()) return new ResourceLocation(MODID, "textures/entity/bee/bee_angry.png");
                        return new ResourceLocation(MODID, "textures/entity/bee/bee.png");
                    }
                }
        );

        RenderingRegistry.registerEntityRenderingHandler(EntityFrog.class, renderManager ->
                new RenderLiving<EntityFrog>(renderManager, new ModelFrog(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityFrog entity) {
                        switch (entity.getFrogType()) {
                            case COOL:
                                return new ResourceLocation(MODID, "textures/entity/frog/cold_frog.png");
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
                    protected ResourceLocation getEntityTexture(@Nullable EntityTurtle entity) {
                        return new ResourceLocation(MODID, "textures/entity/turtle.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityGoat.class, renderManager ->
                new RenderLiving<EntityGoat>(renderManager, new ModelGoat(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityGoat entity) {
                        return new ResourceLocation(MODID, "textures/entity/goat.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityWarden.class, renderManager ->
                new RenderLiving<EntityWarden>(renderManager, new ModelWarden(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityWarden entity) {
                        return new ResourceLocation(MODID, "textures/entity/warden/warden.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityBambooRaft.class, RenderBambooRaft::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomWolf.class, RenderCustomWolf::new);
    }
}
