package ratismal.triggers.common.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldSettings;
import ratismal.triggers.TriggersMod;
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
    public void update() {
        super.update();
        counter++;
        if (counter == DELAY_LENGTH) {
            for (EntityPlayer player : worldObj.playerEntities) {
                if (player.capabilities.isCreativeMode) {
                    //TriggersMod.logger.info("SOMEONE'S CHEATING");
                    //ChatHelper.sendMessageToPlayer(player);
                    if (worldObj.isRemote) {
                        int messageId;
                        if (ConfigHandler.isCreativeJamMessage()) {
                            Random rand = new Random();
                            messageId = rand.nextInt(6);
                        } else {
                            messageId = 99;
                        }
                        TriggersMod.logger.info(messageId);
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
                                message = "Whoops! Something happened.";
                                break;
                            case 5:
                                message = player.getName() + " is not in the creative file. This incident will be reported.";
                                break;
                            default:
                                message = "You are not allowed to enter creative mode.";
                                break;
                        }
                        ChatHelper.sendMessageToPlayer(player, message);
                    }
                    player.setGameType(WorldSettings.GameType.ADVENTURE);
                }
            }
            counter = 0;
        }
    }

}
