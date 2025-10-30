package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import de.julianweinelt.ubm.entities.custom.EntityNewVillager;
import de.julianweinelt.ubm.entities.models.*;
import de.julianweinelt.ubm.entities.render.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    private static int currentID = 1;


    public static void init() {
        register(EntityBee.class, "bee");
        register(EntityFrog.class, "frog");
        register(EntityTurtle.class, "turtle");
        register(EntityGoat.class, "goat");
        register(EntityBambooRaft.class, "bamboo_raft");
        register(EntityWarden.class, "warden");
        register(EntityCustomWolf.class, "custom_wolf");
        register(EntityFox.class, "fox");
        register(EntityDolphin.class, "dolphin");
        register(EntitySalmon.class, "salmon");
        register(EntityCod.class, "cod");
        register(EntityAxolotl.class, "axolotl");
        register(EntityGlowSquid.class, "glow_squid");
        register(EntityTropicalFish.class, "tropical_fish");
        register(EntityPillager.class, "pillager");
        register(EntityPhantom.class, "phantom");
        register(EntityNewVillager.class, "villager");
        register(EntityHoglin.class, "hoglin");
        register(EntityZoglin.class, "zoglin");

        register(EntityCreaking.class, "creaking");
        register(EntitySniffer.class, "sniffer");
        register(EntityStrider.class, "strider");
        register(EntityAllay.class, "allay");
        register(EntityArmadillo.class, "armadillo");
        register(EntityPanda.class, "panda");
        register(EntityRavager.class, "ravager");
        register(EntityPiglin.class, "piglin");
        register(EntityCamel.class, "camel");
        register(EntityCat.class, "cat");
        register(EntityBreeze.class, "breeze");
        register(EntityWanderingTrader.class, "wandering_trader");

        //TODO: Add piglin brute, zombified piglin, Tadpole, Copper Golem, Boat Variants, Happy Ghast
    }

    private static void register(Class<? extends Entity> clazz, String name) {
        name = name.toLowerCase();
        String entityName = convertName(name);



        EntityRegistry.registerModEntity(
                new ResourceLocation(MODID, name),
                clazz,
                entityName,
                currentID,
                UBM.instance,
                64, 1, true
        );
        currentID++;
    }

    private static String convertName(String input) {
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    sb.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
        }

        return sb.toString();
    }


    public static void addSpawns() {
        EntityRegistry.addSpawn(EntitySalmon.class, 10, 1, 5, EnumCreatureType.WATER_CREATURE, FISH_BIOMES);
        EntityRegistry.addSpawn(EntityCod.class, 10, 1, 5, EnumCreatureType.WATER_CREATURE, FISH_BIOMES);
    }


    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        UBM.getLogger().info("Registering Renderers");
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


                    @Override
                    protected void preRenderCallback(@Nullable EntityDolphin entitylivingbaseIn, float partialTickTime) {
                        float scale = entitylivingbaseIn.isChild() ? 0.5F : 1.0F;
                        GlStateManager.scale(scale, scale, scale);
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
                        //TODO: add scale to salmons
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
        RenderingRegistry.registerEntityRenderingHandler(EntityPhantom.class, renderManager ->
                new RenderLiving<EntityPhantom>(renderManager, new ModelPhantom(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityPhantom entity) {
                        return new ResourceLocation(MODID, "textures/entity/phantom.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityHoglin.class, renderManager ->
                new RenderLiving<EntityHoglin>(renderManager, new ModelHoglin(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityHoglin entity) {
                        return new ResourceLocation(MODID, "textures/entity/hoglin/hoglin.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityZoglin.class, renderManager ->
                new RenderLiving<EntityZoglin>(renderManager, new ModelHoglin(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityZoglin entity) {
                        return new ResourceLocation(MODID, "textures/entity/hoglin/zoglin.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityWanderingTrader.class, renderManager ->
                new RenderLiving<EntityWanderingTrader>(renderManager, new ModelVillager(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityWanderingTrader entity) {
                        return new ResourceLocation(MODID, "textures/entity/wandering_trader.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityArmadillo.class, renderManager ->
                new RenderLiving<EntityArmadillo>(renderManager, new ModelArmadillo(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityArmadillo entity) {
                        return new ResourceLocation(MODID, "textures/entity/armadillo.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityAllay.class, renderManager ->
                new RenderLiving<EntityAllay>(renderManager, new ModelAllay(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityAllay entity) {
                        return new ResourceLocation(MODID, "textures/entity/allay/allay.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityCreaking.class, renderManager ->
                new RenderLiving<EntityCreaking>(renderManager, new ModelCreaking(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityCreaking entity) {
                        return new ResourceLocation(MODID, "textures/entity/creaking/creaking.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityRavager.class, renderManager ->
                new RenderLiving<EntityRavager>(renderManager, new ModelRavager(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityRavager entity) {
                        return new ResourceLocation(MODID, "textures/entity/illager/ravager.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntitySniffer.class, renderManager ->
                new RenderLiving<EntitySniffer>(renderManager, new ModelSniffer(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntitySniffer entity) {
                        return new ResourceLocation(MODID, "textures/entity/sniffer/sniffer.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityCamel.class, renderManager ->
                new RenderLiving<EntityCamel>(renderManager, new ModelCamel(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityCamel entity) {
                        return new ResourceLocation(MODID, "textures/entity/camel/camel.png");
                    }
                }
        );
        RenderingRegistry.registerEntityRenderingHandler(EntityBreeze.class, renderManager ->
                new RenderLiving<EntityBreeze>(renderManager, new ModelBreeze(), 0.5F) {
                    @Override
                    protected ResourceLocation getEntityTexture(@Nullable EntityBreeze entity) {
                        return new ResourceLocation(MODID, "textures/entity/breeze/breeze.png");
                    }
                }
        );

        //TODO: Cat, Panda, Strider, Piglin, Zombified Piglin, Piglin Brute
        RenderingRegistry.registerEntityRenderingHandler(EntityBambooRaft.class, RenderBambooRaft::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFox.class, RenderFox::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomWolf.class, RenderCustomWolf::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityNewVillager.class, RenderNewVillager::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAxolotl.class, RenderAxolotl::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGlowSquid.class, RenderGlowSquid::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTropicalFish.class, RenderTropicalFish::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPillager.class, RenderPillager::new);
        UBM.getLogger().info("Registered Renderers");
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
