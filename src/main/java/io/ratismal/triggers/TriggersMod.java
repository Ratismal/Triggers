package io.ratismal.triggers;

import io.ratismal.triggers.common.init.ModBlocks;
import io.ratismal.triggers.common.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by Ratismal on 2016-01-11.
 */

@Mod(modid = TriggersMod.MODID, name = TriggersMod.MODNAME, dependencies = "required-after:Forge@[11.15.0.1634,)", useMetadata = true)
public class TriggersMod {

    public static final String MODID = "triggers";
    public static final String MODNAME = "Triggers Mod";

    @SidedProxy
    public static CommonProxy proxy;

    @Mod.Instance
    public static TriggersMod instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    public static class CommonProxy {
        public void preInit(FMLPreInitializationEvent e) {
            // Initialization of blocks and items typically goes here:
            ModBlocks.init();
            ModItems.init();
        }

        public void init(FMLInitializationEvent e) {

        }

        public void postInit(FMLPostInitializationEvent e) {

        }
    }


    public static class ClientProxy extends CommonProxy {

        public static EntityPlayer getThePlayer() {
            return FMLClientHandler.instance().getClientPlayerEntity();
        }

        @Override
        public void preInit(FMLPreInitializationEvent e) {
            super.preInit(e);

            //MinecraftForge.EVENT_BUS.register(new ClientEventHandlers());
            //OBJLoader.instance.addDomain(MODID);

            // Typically initialization of models and such goes here:
            ModBlocks.initModels();
            ModItems.initModels();
        }

        @Override
        public void init(FMLInitializationEvent e) {
            super.init(e);

            //ModBlocks.initItemModels();
        }
    }

    public static class ServerProxy extends CommonProxy {

    }
}