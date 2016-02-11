package ratismal.triggers.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldSettings;
import ratismal.triggers.common.handler.ConfigHandler;
import ratismal.triggers.common.utils.ChatHelper;

import java.util.Random;

/**
 * Created by Ratismal on 2016-02-03.
 */

public class TileCreativeJammer extends TileSemiEthereal implements ITickable {

    final int DELAY_LENGTH = 10;
    int counter = 0;

    @Override
    public void checkStateServer() {
        super.checkStateServer();
        counter++;
        if (counter == DELAY_LENGTH) {
            for (EntityPlayer player : worldObj.playerEntities) {
                if (player.capabilities.isCreativeMode) {
                    int messageId;
                    if (ConfigHandler.isCreativeJamMessage()) {
                        Random rand = new Random();
                        messageId = rand.nextInt(6);
                    } else {
                        messageId = 99;
                    }
                    String message;
                    switch (messageId) {
                        case 0:
                            message = "I sense a disturbance in the force!";
                            break;
                        case 1:
                            message = "You feel your creativity float away...";
                            break;
                        case 2:
                            message = "I'm sorry " + player.getName() + ", I'm afraid I can't do that.";
                            break;
                        case 3:
                            message = "The creative mode is a lie.";
                            break;
                        case 4:
                            message = "This is not the mode you are looking for.";
                            break;
                        case 5:
                            message = player.getName() + " is not in the creativers file. This incident will be reported.";
                            break;
                        default:
                            message = "You are not allowed to enter creative mode.";
                            break;
                    }
                    ChatHelper.sendMessageToPlayer(player, message, false);
                    player.setGameType(WorldSettings.GameType.ADVENTURE);
                }
            }
            counter = 0;
        }
    }

}
