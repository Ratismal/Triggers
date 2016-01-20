package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileTriggerPlayer extends TileTrigger implements ITickable {

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

    int counter = 0;
    int oldCounter = 0;

    public void updatePlayerIsInMe() {
        if (!playerIsInMe) {
            TriggersMod.logger.debug("Block> THERE'S SOMETHING INSIDE ME THERE'S SOMETHING INSIDE ME OH GOD GET IT OUT");
            setPlayerIsInMe(true);
        }
        counter++;
        if (counter >= 511) {
            counter = 0;
        }
    }

    int timeCount = 0;
    int delayLength = 20;

    @Override
    public void update() {
        if (playerIsInMe) {
            if (timeCount >= delayLength) {
                if (oldCounter != counter) {
                    oldCounter = counter;
                    if (getPowerLevel() != 15) {
                        //TriggersMod.logger.info("Block> MY POWER LEVEL IS OVER 14!");
                        setPowerLevel(15);
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
