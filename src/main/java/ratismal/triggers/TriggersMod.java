package ratismal.triggers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ratismal.triggers.client.GuiHandler;
import ratismal.triggers.common.handler.ConfigHandler;
import ratismal.triggers.common.init.ModBlocks;
import ratismal.triggers.common.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import ratismal.triggers.common.network.PacketHandler;

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
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
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

        /**
         * Returns a side-appropriate EntityPlayer for use during message handling
         */
        public EntityPlayer getPlayerEntity(MessageContext ctx) {
            return ctx.getServerHandler().playerEntity;
        }

        public void preInit(FMLPreInitializationEvent e) {
            ConfigHandler.init(e.getSuggestedConfigurationFile());
            // Initialization of blocks and items typically goes here:
            ModBlocks.init();
            ModItems.init();
            PacketHandler.init();
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
        public EntityPlayer getPlayerEntity(MessageContext ctx) {
            // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
            // your packets will not work because you will be getting a client
            // player even when you are on the server! Sounds absurd, but it's true.

            // Solution is to double-check side before returning the player:
            return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
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