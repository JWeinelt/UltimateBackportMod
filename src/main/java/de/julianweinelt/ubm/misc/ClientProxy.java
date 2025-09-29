package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.tiles.RenderTileEntityCampfire;
import de.julianweinelt.ubm.blocks.tiles.TileEntityCampfire;
import de.julianweinelt.ubm.entities.ModEntities;
import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.qol.SwimClientHandler;
import de.julianweinelt.ubm.trims.LayerArmorTrim;
import de.julianweinelt.ubm.trims.TrimColorHelper;
import de.julianweinelt.ubm.worldgen.WorldTypeModern;
import de.julianweinelt.ubm.worldgen.WorldTypeSelectableBiome;
import de.julianweinelt.ubm.worldgen.structure.village.ModCustomVillage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        Map<Class<? extends Entity>, Render<? extends Entity>> renderMap =
                Minecraft.getMinecraft().getRenderManager().entityRenderMap;

        addLayerIfLiving(renderMap.get(EntityZombie.class));
        addLayerIfLiving(renderMap.get(EntitySkeleton.class));
        addLayerIfLiving(renderMap.get(EntityArmorStand.class));

        super.init(e);

        for (RenderPlayer renderer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
            renderer.addLayer(new LayerArmorTrim(renderer));
        }

        ClientEventHandler.registerParticles();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCampfire.class, new RenderTileEntityCampfire());

        ModEntities.registerRenders();

        MinecraftForge.EVENT_BUS.register(new SwimClientHandler());

        new WorldTypeSelectableBiome("selectable_biome");
        new WorldTypeModern();



        String basePath = "textures/equipment/trims/color_palettes/";
        for (String material : UBM.colorPalettes) {
            UBM.getLogger().info("Registering color palette: " + material);
            Color[] palette = TrimColorHelper.extractPalette(basePath + material + ".png");
            LayerArmorTrim.MATERIAL_TO_PALETTE.put(material, palette);
        }

        ModCustomVillage.init();
    }

    private static void addLayerIfLiving(Render<? extends Entity> renderer) {
        if(renderer instanceof RenderLivingBase) {
            ((RenderLivingBase<?>) renderer).addLayer(new LayerArmorTrim((RenderLivingBase<?>) renderer));
        }
    }



    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        ModModels.registerAllArmorModels();
    }
}