package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.common.channels.ChannelRedstone;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileWirelessReceiver extends TileTrigger implements ITickable {

    //public static List<TileWirelessReceiver> receiverList = new ArrayList<TileWirelessReceiver>();

    //int lastPower;
    private boolean prevPower = false;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
    }

    @Override
    protected void checkStateServer() {
        super.checkStateServer();
        if (canProceed()) {
            if (getPowerLevel() != prevPower) {
                worldObj.notifyNeighborsOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
                prevPower = getPowerLevel();
                ChannelRedstone.get(worldObj).save(worldObj);
                notifyBlockUpdate();
                if (goOnce && getPowerLevel()) {
                    setTriggered(true);
                }
            }
        }
        if (!ChannelRedstone.receivers.containsKey(pos)) {
            ChannelRedstone.receivers.put(pos, flag);
        }
    }

    public void setPrevPower(boolean prevPower) {
        this.prevPower = prevPower;
    }

    /*
    public void registerReceiver() {
        receiverList.add(this);
    }
    public void destroyReceiver() {
        receiverList.remove(this);
    }
    */
}
