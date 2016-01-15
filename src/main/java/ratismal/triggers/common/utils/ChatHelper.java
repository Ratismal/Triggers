package ratismal.triggers.common.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by Ratismal on 2016-01-14.
 */

public class ChatHelper {

    public static void sendMessageToPlayer(EntityPlayer player, String message) {
        if (!player.getEntityWorld().isRemote)
            player.addChatMessage(new ChatComponentText(message));
    }

}
