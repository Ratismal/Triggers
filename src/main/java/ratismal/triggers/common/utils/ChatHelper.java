package ratismal.triggers.common.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

/**
 * Created by Ratismal on 2016-01-14.
 */

public class ChatHelper {

    public static void sendMessageToPlayer(EntityPlayer player, String message, boolean isRemote) {
        if (player.getEntityWorld().isRemote == isRemote)
            player.addChatMessage(new ChatComponentText(message));
    }

}
