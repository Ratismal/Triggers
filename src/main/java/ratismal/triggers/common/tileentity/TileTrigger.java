package ratismal.triggers.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.channels.ChannelRedstone;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ratismal on 2016-01-14.
 */

public class TileTrigger extends TileSemiEthereal {

    public int flag = 0;
    private int prevPower = 0;

    public NBTTagCompound oldCompound;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        flag = tag.getInteger("flag");

        oldCompound = tag;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("flag", flag);
        //TriggersMod.logger.info("Setting nbt flag to " + flag);
        oldCompound = tag;
    }

    public int getPowerLevel() {
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

    public void setPowerLevel(int power) {
        ChannelRedstone channelRedstone = ChannelRedstone.get(getWorld());
        channelRedstone.setChannelState(flag, power);
        channelRedstone.save(getWorld());

//        worldObj.markBlockForUpdate(pos);

        //    TriggersMod.logger.info("Notifying neighbors of power change.");
        //      worldObj.notifyNeighborsOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
    }

    public void setFlag(int flag) {
        TriggersMod.logger.info("Setting int flag to " + flag);
        this.flag = flag;
        markDirty();
        worldObj.markBlockForUpdate(getPos());
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void checkStateServer() {
        super.checkStateServer();
        if (getPowerLevel() != prevPower) {
            worldObj.notifyNeighborsOfStateChange(pos, worldObj.getBlockState(pos).getBlock());
            prevPower = getPowerLevel();
            ChannelRedstone.get(worldObj).save(worldObj);
        }
        //ChannelRedstone.get(worldObj).
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

    /*
    //List of coords to power
    public List<BlockPos> coordsList = new ArrayList<BlockPos>();

    //Is this block active right now
    public boolean active;

    public NBTTagCompound oldCompound;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        coordsList.clear();
        NBTTagCompound compound2 = compound.getCompoundTag("coords");
        for (int i = 0; compound2.hasKey(String.valueOf(i)); i++) {
            coordsList.add(intToPos(compound2.getIntArray(String.valueOf(i))));
        }

        active = compound.getBoolean("active");

        oldCompound = compound;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagCompound compound2 = new NBTTagCompound();
        int i = 0;
        for (BlockPos coords : coordsList) {
            compound2.setIntArray(String.valueOf(i), posToInt(coords));
            i++;
        }
        compound.setTag("coords", compound2);

        compound.setBoolean("active", active);

        oldCompound = compound;
    }

    public NBTTagCompound getTagCompound() {
        return oldCompound;
    }

    public BlockPos intToPos(int[] coords) {
        if (coords.length >= 3)
            return new BlockPos(coords[0], coords[1], coords[2]);
        return null;
    }

    public int[] posToInt(BlockPos pos) {
        return new int[]{pos.getX(), pos.getY(), pos.getZ()};
    }


    public void addTarget(BlockPos newTarget) {
        if (!coordsList.contains(newTarget)) {
            BlockPos target = newTarget;
            coordsList.add(target);
            this.worldObj.markBlockForUpdate(this.pos);

            if (target != null)
            {
                IBlockState targetState = worldObj.getBlockState(target);
                targetState.getBlock().onNeighborBlockChange(worldObj, target, targetState, this.blockType);
                worldObj.notifyNeighborsOfStateChange(target, targetState.getBlock());
            }

        }
    }
*/
}
