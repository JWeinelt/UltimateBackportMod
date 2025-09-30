package de.julianweinelt.ubm;


import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.blocks.api.sign.TileEntityModSign;
import de.julianweinelt.ubm.blocks.interactable.smithing.GuiHandler;
import de.julianweinelt.ubm.blocks.interactable.smithing.TileEntitySmithingTable;
import de.julianweinelt.ubm.entities.ModEntities;
import de.julianweinelt.ubm.misc.ClientEventHandler;
import de.julianweinelt.ubm.misc.ClientProxy;
import de.julianweinelt.ubm.misc.CommonProxy;
import de.julianweinelt.ubm.misc.ModRecipes;
import de.julianweinelt.ubm.qol.SwimClientHandler;
import de.julianweinelt.ubm.trims.LayerArmorTrim;
import de.julianweinelt.ubm.trims.TrimColorHelper;
import de.julianweinelt.ubm.worldgen.PowderSnowWorldGen;
import de.julianweinelt.ubm.worldgen.StructureWorldGen;
import de.julianweinelt.ubm.worldgen.structure.village.ModCustomVillage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;


@Mod(modid = UBM.MODID, name = UBM.NAME, version = UBM.VERSION)
public class UBM {
    public static final String MODID = "ubm";
    public static final String NAME = "Ultimate Backport Mod";
    public static final String VERSION = "1.1.0";

    private static Logger logger;
    public static UBM instance;

    @SidedProxy(clientSide = "de.julianweinelt.ubm.misc.ClientProxy",
            serverSide = "de.julianweinelt.ubm.misc.CommonProxy")
    public static CommonProxy proxy;

    public static String[] colorPalettes = {
            "amethyst", "copper", "diamond", "emerald", "gold", "iron", "lapis", "netherite",
            "quartz", "redstone", "resin"
    };

    public static File config;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        instance = this;

        config = event.getModConfigurationDirectory();
        if (!config.exists()) {
            config.mkdirs();
        }

        ModEntities.init();
        ModEntities.addSpawns();

        GameRegistry.registerWorldGenerator(new PowderSnowWorldGen(), 0);
        GameRegistry.registerWorldGenerator(new StructureWorldGen(), 0);

        GameRegistry.registerTileEntity(TileEntitySmithingTable.class, new ResourceLocation("ubm", "smithing_table"));
        GameRegistry.registerTileEntity(TileEntityModSign.class, new ResourceLocation("ubm", "sign_te"));


        ModCustomVillage.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(UBM.instance, new GuiHandler());
        proxy.init(event);
        ModBlocks.WAXED_VARIANTS.put(ModBlocks.COPPER_BLOCK.getRegistryName(), ModBlocks.WAXED_COPPER_BLOCK.getRegistryName());
        ModBlocks.WAXED_VARIANTS.put(ModBlocks.EXPOSED_COPPER_BLOCK.getRegistryName(), ModBlocks.WAXED_EXPOSED_COPPER_BLOCK.getRegistryName());
        ModBlocks.WAXED_VARIANTS.put(ModBlocks.WEATHERED_COPPER_BLOCK.getRegistryName(), ModBlocks.WAXED_WEATHERED_COPPER_BLOCK.getRegistryName());
        ModBlocks.WAXED_VARIANTS.put(ModBlocks.OXIDIZED_COPPER_BLOCK.getRegistryName(), ModBlocks.WAXED_OXIDIZED_COPPER_BLOCK.getRegistryName());

        ModBlocks.UNWAXED_VARIANTS.put(ModBlocks.WAXED_COPPER_BLOCK.getRegistryName(), ModBlocks.COPPER_BLOCK.getRegistryName());
        ModBlocks.UNWAXED_VARIANTS.put(ModBlocks.WAXED_EXPOSED_COPPER_BLOCK.getRegistryName(), ModBlocks.EXPOSED_COPPER_BLOCK.getRegistryName());
        ModBlocks.UNWAXED_VARIANTS.put(ModBlocks.WAXED_WEATHERED_COPPER_BLOCK.getRegistryName(), ModBlocks.WEATHERED_COPPER_BLOCK.getRegistryName());
        ModBlocks.UNWAXED_VARIANTS.put(ModBlocks.WAXED_OXIDIZED_COPPER_BLOCK.getRegistryName(), ModBlocks.OXIDIZED_COPPER_BLOCK.getRegistryName());

        ModBlocks.PREVIOUS_OXIDATION.put(ModBlocks.EXPOSED_COPPER_BLOCK.getRegistryName(), ModBlocks.COPPER_BLOCK.getRegistryName());
        ModBlocks.PREVIOUS_OXIDATION.put(ModBlocks.WEATHERED_COPPER_BLOCK.getRegistryName(), ModBlocks.EXPOSED_COPPER_BLOCK.getRegistryName());
        ModBlocks.PREVIOUS_OXIDATION.put(ModBlocks.OXIDIZED_COPPER_BLOCK.getRegistryName(), ModBlocks.WEATHERED_COPPER_BLOCK.getRegistryName());

        ModRecipes.init();
    }

    public static Logger getLogger() {
        return logger;
    }
}
