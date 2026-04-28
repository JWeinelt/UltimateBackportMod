package de.julianweinelt.ubm;


import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.blocks.api.sign.TileEntityModSign;
import de.julianweinelt.ubm.blocks.interactable.smithing.GuiHandler;
import de.julianweinelt.ubm.blocks.interactable.smithing.TileEntitySmithingTable;
import de.julianweinelt.ubm.blocks.tiles.TileEntityBlastFurnace;
import de.julianweinelt.ubm.blocks.tiles.TileEntitySculkSensor;
import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.effects.ModEffects;
import de.julianweinelt.ubm.effects.ModPotionTypes;
import de.julianweinelt.ubm.entities.ModEntities;
import de.julianweinelt.ubm.misc.CommonProxy;
import de.julianweinelt.ubm.misc.ModRecipes;
import de.julianweinelt.ubm.misc.ModSounds;
import de.julianweinelt.ubm.worldgen.ModDimensions;
import de.julianweinelt.ubm.worldgen.PowderSnowWorldGen;
import de.julianweinelt.ubm.worldgen.StructureWorldGen;
import de.julianweinelt.ubm.worldgen.misc.CommandGotoCustomNether;
import de.julianweinelt.ubm.worldgen.structure.village.ModCustomVillage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Mod(modid = UBM.MODID, name = UBM.NAME, version = UBM.VERSION)
public class UBM {
    public static final String MODID = "ubm";
    public static final String NAME = "Ultimate Backport Mod";
    public static final String VERSION = "1.2.2";

    private static Logger logger;
    public static UBM instance;

    @SidedProxy(clientSide = "de.julianweinelt.ubm.misc.ClientProxy",
            serverSide = "de.julianweinelt.ubm.misc.CommonProxy")
    public static CommonProxy proxy;

    public static String[] colorPalettes = {
            "amethyst", "copper", "diamond", "emerald", "gold", "iron", "lapis", "netherite",
            "quartz", "redstone", "resin"
    };

    public static File configDir;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        instance = this;

        proxy.preInit(event);

        configDir = event.getModConfigurationDirectory();
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        ModEffects.init();
        ModPotionTypes.init();

        File configFile = new File(configDir, MODID + ".cfg");

        if (!configFile.exists()) {
            try (InputStream in = getClass().getResourceAsStream("/assets/ubm/ubm.cfg")) {
                if (in != null) {
                    java.nio.file.Files.copy(in, configFile.toPath());
                } else {
                    logger.fatal("Failed to load ubm.cfg! The file seems not to be in the mod itself. That is a problem.");
                }
            } catch (IOException e) {
                logger.error("Failed to load config file!", e);
            }
        }

        ModConfig.init(configFile);

        ModEntities.init();
        ModEntities.addSpawns();

        GameRegistry.registerWorldGenerator(new PowderSnowWorldGen(), 0);
        GameRegistry.registerWorldGenerator(new StructureWorldGen(), 0);

        GameRegistry.registerTileEntity(TileEntitySmithingTable.class, new ResourceLocation("ubm", "smithing_table"));
        GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, new ResourceLocation("ubm", "blast_furnace"));
        GameRegistry.registerTileEntity(TileEntityModSign.class, new ResourceLocation("ubm", "sign_te"));
        GameRegistry.registerTileEntity(TileEntitySculkSensor.class, new ResourceLocation("ubm", "sculk_sensor_te"));


        ModCustomVillage.preInit();
        ModDimensions.register();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(UBM.instance, new GuiHandler());
        proxy.init(event);

        ModRecipes.init();
        ModSounds.SoundTypes.init();
        ModBlocks.AMETHYST_BLOCK.soundType(ModSounds.SoundTypes.AMETHYST_BLOCK);
        ModBlocks.BUDDING_AMETHYST.soundType(ModSounds.SoundTypes.AMETHYST_BLOCK);
        ModBlocks.TUFF.soundType(ModSounds.SoundTypes.TUFF);
        ModBlocks.TUFF_BRICKS.soundType(ModSounds.SoundTypes.TUFF);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGotoCustomNether());
    }


    public static Logger getLogger() {
        return logger;
    }
}
