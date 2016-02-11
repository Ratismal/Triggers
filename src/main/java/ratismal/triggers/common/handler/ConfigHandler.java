package ratismal.triggers.common.handler;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    private static boolean creativeJamMessage;

    private static boolean debugMessages;

    public static Configuration config;
    private static final String CATEGORY_DEBUG = "debug";

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

        //DEBUG
        debugMessages = config.get(CATEGORY_DEBUG, "debugMessages", false, "Should the mob give debug info? (will probably spam console)").getBoolean();

        if (config.hasChanged()) {
            config.save();
        }

    }

    public static boolean isCreativeJamMessage() {
        return creativeJamMessage;
    }
    public static boolean isDebugMessages() {
        return debugMessages;
    }


}
