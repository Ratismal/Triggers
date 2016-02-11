package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.channels.ChannelRedstone;

/**
 * Created by Ratismal on 2016-01-14.
 */

public class TileTrigger extends TileSemiEthereal {

    public int flag = 0;
    public boolean goOnce = false;
    public boolean triggered = false;

    public NBTTagCompound oldCompound;

    public void setGoOnce(boolean goOnce) {
        this.goOnce = goOnce;
        this.triggered = false;
    }

    public boolean isGoOnce() {
        return goOnce;
    }

    public void setTriggered(boolean triggered) {
        if (goOnce && triggered)
            this.triggered = true;
        else if (!goOnce && triggered)
            TriggersMod.logger.info("Cannot set a block to triggered if goOnce != true!");
        else
            this.triggered = false;
    }

    public boolean canProceed() {
        return !(goOnce && triggered);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        flag = tag.getInteger("flag");
        goOnce = tag.getBoolean("goOnce");
        oldCompound = tag;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("flag", flag);
        tag.setBoolean("goOnce", goOnce);
        oldCompound = tag;
    }

    public boolean getPowerLevel() {
        return ChannelRedstone.get(getWorld()).getChannelState(flag).getPower();
    }

    public String getChannelName() {
        ChannelRedstone channelRedstone = ChannelRedstone.get(getWorld());
        ChannelRedstone.RedstoneChannel rc = channelRedstone.getChannelState(flag);
        return rc.hasName() ? rc.getName() : null;
    }

    public void setChannelName(String name) {
        ChannelRedstone channelRedstone = ChannelRedstone.get(getWorld());
        channelRedstone.setChannelName(flag, name);
        channelRedstone.save(getWorld());
    }

    public void setPowerLevel(boolean power) {
        ChannelRedstone channelRedstone = ChannelRedstone.get(getWorld());
        channelRedstone.setChannelState(flag, power);
        channelRedstone.save(getWorld());
    }

    public void setFlag(int flag) {
        this.flag = flag;
        markDirty();
        worldObj.markBlockForUpdate(getPos());
    }

    @Override
    public void update() {
        super.update();
    }


    public int getFlag() {
        return flag;
    }

    public NBTTagCompound getTagCompound() {
        return oldCompound;
    }

    @Override
    public Packet getDescriptionPacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new S35PacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }
}
