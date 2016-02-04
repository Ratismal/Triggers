package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.channels.ChannelRedstone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileWirelessReceiver extends TileTrigger implements ITickable {

    //public static List<TileWirelessReceiver> receiverList = new ArrayList<TileWirelessReceiver>();

    //int lastPower;
    private int prevPower = 0;


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
        if (getPowerLevel() != prevPower) {
            worldObj.notifyNeighborsOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
            prevPower = getPowerLevel();
            ChannelRedstone.get(worldObj).save(worldObj);
            //notify
            notifyBlockUpdate();
        }
        //ChannelRedstone.get(worldObj).
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
