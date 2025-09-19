package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import de.julianweinelt.ubm.entities.models.*;
import de.julianweinelt.ubm.entities.render.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Random;

import static de.julianweinelt.ubm.UBM.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ModEntities {
    private static final Biome[] FISH_BIOMES = {
            Biomes.FROZEN_RIVER,
            Biomes.FROZEN_OCEAN,
            Biomes.RIVER,
            Biomes.DEEP_OCEAN,
            Biomes.OCEAN
            //TODO: Lukewarm Ocean, Warm Ocean, Deep Lukewarm Ocean, Cold / Deep cold ocean
    };


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
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "dolphin"),
                EntityDolphin.class,
                "Dolphin",
                9,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "salmon"),
                EntitySalmon.class,
                "Salmon",
                10,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "axolotl"),
                EntityAxolotl.class,
                "Axolotl",
                11,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "cod"),
                EntityCod.class,
                "Cod",
                12,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "glow_squid"),
                EntityGlowSquid.class,
                "GlowSquid",
                13,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "tropical_fish"),
                EntityTropicalFish.class,
                "TropicalFish",
                14,
                UBM.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation("ubm", "pillager"),
                EntityPillager.class,
                "Pillager",
                15,
                UBM.instance,
                64, 1, true
        );

    }

    public static void addSpawns() {
        EntityRegistry.addSpawn(EntitySalmon.class, 10, 1, 5, EnumCreatureType.WATER_CREATURE, FISH_BIOMES);
        EntityRegistry.addSpawn(EntityCod.class, 10, 1, 5, EnumCreatureType.WATER_CREATURE, FISH_BIOMES);
    }

    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, renderManager ->
                new RenderLiving<EntityBee>(renderManager, new ModelBee(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityBee entity) {
                        if (entity == null) return new ResourceLocation(MODID, "textures/entity/bee/bee.png");
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
                        if (entity == null) return new ResourceLocation(MODID, "textures/entity/frog/temperate_frog.png");
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
                    @Override
                    protected void preRenderCallback(@Nonnull EntityGoat entityLivingBaseIn, float partialTickTime) {
                        float scale = entityLivingBaseIn.isChild() ? 0.5F : 1.0F;
                        GlStateManager.scale(scale, scale, scale);
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
        RenderingRegistry.registerEntityRenderingHandler(EntityDolphin.class, renderManager ->
                new RenderLiving<EntityDolphin>(renderManager, new ModelDolphin(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityDolphin entity) {
                        return new ResourceLocation(MODID, "textures/entity/dolphin.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntitySalmon.class, renderManager ->
                new RenderLiving<EntitySalmon>(renderManager, new ModelSalmon(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntitySalmon entity) {
                        return new ResourceLocation(MODID, "textures/entity/fish/salmon.png");
                    }

                    @Override
                    protected void preRenderCallback(@Nonnull EntitySalmon entityLivingBaseIn, float partialTickTime) {
                        Random random = entityLivingBaseIn.getRNG();
                        float min = 0.3F;
                        float max = 1.2F;

                        float scale = random.nextFloat() * (max - min) + min;
                        //GlStateManager.scale(scale, scale, scale);
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityCod.class, renderManager ->
                new RenderLiving<EntityCod>(renderManager, new ModelCod(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityCod entity) {
                        return new ResourceLocation(MODID, "textures/entity/fish/cod.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityBambooRaft.class, RenderBambooRaft::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomWolf.class, RenderCustomWolf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAxolotl.class, RenderAxolotl::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGlowSquid.class, RenderGlowSquid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTropicalFish.class, RenderTropicalFish::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPillager.class, RenderPillager::new);
    }

    @SubscribeEvent
    public void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {
        if (event.getEntity() instanceof EntitySalmon) {
            UBM.getLogger().info("Tried to spawn salmon");
            Biome biome = event.getWorld().getBiome(event.getEntity().getPosition());
            if (biome != Biomes.OCEAN && biome != Biomes.DEEP_OCEAN && biome != Biomes.RIVER) {
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
