package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileTriggerPlayer extends TileTrigger implements ITickable {

    final int MAX_POWER_LEVEL = 15;
    final int MAX_COUNTER = 511;
    final int MIN_POWER_LEVEL = 0;
    final int DELAY_LENGTH = 20;

    int counter = 0;
    int oldCounter = 0;

    boolean playerIsInMe = false;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
    }

    public boolean isPlayerIsInMe() {
        return playerIsInMe;
    }

    public void setPlayerIsInMe(boolean ewGrossGetItOut) {
        playerIsInMe = ewGrossGetItOut;
    }

    public void updatePlayerIsInMe() {
        if (!playerIsInMe) {
            //TriggersMod.logger.debug("Block> THERE'S SOMETHING INSIDE ME THERE'S SOMETHING INSIDE ME OH GOD GET IT OUT");
            setPlayerIsInMe(true);
        }
        counter++;
        if (counter >= MAX_COUNTER) {
            counter = 0;
        }
    }

    int timeCount = 0;

    @Override
    public void update() {
        if (playerIsInMe) {
            if (timeCount >= DELAY_LENGTH) {
                if (oldCounter != counter) {
                    oldCounter = counter;
                    if (getPowerLevel() != MAX_POWER_LEVEL) {
                        //TriggersMod.logger.info("Block> MY POWER LEVEL IS OVER 14!");
                        setPowerLevel(MAX_POWER_LEVEL);
                    }
                } else {
                    //TriggersMod.logger.info("Block> Oh good, the gross thing isn't in my anymore.");
                    setPlayerIsInMe(false);
                    setPowerLevel(0);
                }
                timeCount = 0;
            }
            timeCount++;
        }
    }

}
