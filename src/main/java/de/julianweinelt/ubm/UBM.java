package de.julianweinelt.ubm;


import de.julianweinelt.ubm.blocks.ModBlocks;
import de.julianweinelt.ubm.entities.ModEntities;
import de.julianweinelt.ubm.misc.ClientEventHandler;
import de.julianweinelt.ubm.misc.CommonProxy;
import de.julianweinelt.ubm.misc.ModRecipes;
import de.julianweinelt.ubm.qol.SwimClientHandler;
import de.julianweinelt.ubm.worldgen.PowderSnowWorldGen;
import de.julianweinelt.ubm.worldgen.StructureWorldGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;


@Mod(modid = UBM.MODID, name = UBM.NAME, version = UBM.VERSION)
public class UBM {
    public static final String MODID = "ubm";
    public static final String NAME = "Ultimate Backport Mod";
    public static final String VERSION = "0.0.1";

    private static Logger logger;
    public static UBM instance;

    @SidedProxy(clientSide = "de.julianweinelt.ubm.misc.ClientProxy",
            serverSide = "de.julianweinelt.ubm.misc.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        instance = this;


        ModEntities.init();
        ModEntities.registerRenders();
        ModEntities.addSpawns();

        GameRegistry.registerWorldGenerator(new PowderSnowWorldGen(), 0);
        GameRegistry.registerWorldGenerator(new StructureWorldGen(), 0);
        MinecraftForge.EVENT_BUS.register(new SwimClientHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
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
        ClientEventHandler.registerParticles();
    }

    public static Logger getLogger() {
        return logger;
    }
}