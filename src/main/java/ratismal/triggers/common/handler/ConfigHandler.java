package ratismal.triggers.common.handler;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static boolean creativeJamMessage;

    public static Configuration config;

    public static void init(File configFile) {

        //create config file
        if (config == null) {
            config = new Configuration(configFile);
            loadConfig();
        }


    }

    private static void loadConfig() {


        //read properties
        //GENERAL
        creativeJamMessage = config.get(Configuration.CATEGORY_GENERAL, "jamMessages", true, "Are funny creative jammer messages enabled").getBoolean();

        if (config.hasChanged()) {
            config.save();
        }

    }

    public static boolean isCreativeJamMessage() {
        return creativeJamMessage;
    }


}
