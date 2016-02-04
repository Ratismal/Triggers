package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileTriggerEntity extends TileEmitter implements ITickable {

    final int MAX_COUNTER = 2048;
    final int DELAY_LENGTH = 2;

    int counter = 0;
    int oldCounter = 0;

    boolean entityIsInMe = false;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
    }

    public boolean isEntityIsInMe() {
        return entityIsInMe;
    }

    public void setEntityIsInMe(boolean ewGrossGetItOut) {
        entityIsInMe = ewGrossGetItOut;
    }

    public void updateEntityIsInMe() {
        if (!entityIsInMe) {
            //TriggersMod.logger.debug("Block> THERE'S SOMETHING INSIDE ME THERE'S SOMETHING INSIDE ME OH GOD GET IT OUT");
            setEntityIsInMe(true);
        }
        counter++;
        if (counter >= MAX_COUNTER) {
            counter = 0;
        }

    }

    int timeCount = 0;

    @Override
    public void checkStateServer() {
        super.checkStateServer();
        if (entityIsInMe) {
            if (timeCount >= DELAY_LENGTH) {
                if (oldCounter != counter) {
                    oldCounter = counter;
                    if (!isActive()) {
                        //TriggersMod.logger.info("Block> MY POWER LEVEL IS OVER 14!");
                        setActive(true);
                    }
                } else {
                    TriggersMod.logger.info("Block> Oh good, the gross thing isn't in me anymore.");

                    setEntityIsInMe(false);
                    setActive(false);

                }
                timeCount = 0;
            }
            timeCount++;
        }
    }

    //  public static List<TileTriggerEntity> triggerPlayerList = new ArrayList<TileTriggerEntity>();

    //  public void registerTrigger() {
    //      TileTriggerEntity.triggerPlayerList.add(this);
    //  }

    //  public void destroyTrigger() {
    //     TileTriggerEntity.triggerPlayerList.remove(this);
    // }

}
