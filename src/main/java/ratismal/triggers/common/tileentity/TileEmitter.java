package ratismal.triggers.common.tileentity;

import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.channels.ChannelRedstone;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class TileEmitter extends TileTrigger {

    private boolean active;

    @Override
    protected void checkStateServer() {
        super.checkStateServer();
        //TriggersMod.logger.info("Tick");
        if (!ChannelRedstone.transmitters.containsKey(pos)) {
         //   TriggersMod.logger.info("Submitting transmitter entry");
            ChannelRedstone.transmitters.put(pos, flag);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        //TriggersMod.logger.info(canProceed() + " " + goOnce + " " + triggered);
        if (canProceed()) {
            //TriggersMod.logger.info(active);
            this.active = active;
            // TriggersMod.logger.info("This tile entity is now " + isActive());
            ChannelRedstone.get(worldObj).update(flag, worldObj);
            if (goOnce) {
                setTriggered(true);
            }
        }
    }

    @Override
    public void setTriggered(boolean triggered) {
        super.setTriggered(triggered);
        if (!triggered) {
            this.active = false;
            ChannelRedstone.get(worldObj).update(flag, worldObj);
        }
    }

    @Override
    public void setFlag(int flag) {
        super.setFlag(flag);
        ChannelRedstone.transmitters.remove(pos);
        ChannelRedstone.transmitters.put(pos, flag);
    }
}
