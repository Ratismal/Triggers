package ratismal.triggers.common.network;

import ratismal.triggers.common.network.messages.MessageChannelRedstone;
import ratismal.triggers.common.network.messages.MessageTriggerPlayer;

/**
 * Created by Ratismal on 2016-01-20.
 */

public class PacketHandler {

    public static final NetworkWrapper INSTANCE = new NetworkWrapper();

    public static void init()
    {
        INSTANCE.registerMessage(MessageTriggerPlayer.class);
        INSTANCE.registerMessage(MessageChannelRedstone.class);
    }

}
