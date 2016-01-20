package ratismal.triggers.common.channels;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants;

import java.util.*;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class ChannelRedstone extends WorldSavedData {

    public static final String IDENTIFIER = "TriggersRedstoneChannels";

    public static Map<Integer, RedstoneChannel> redstoneChannels = new HashMap<Integer, RedstoneChannel>();

    public ChannelRedstone() {
        super(IDENTIFIER);
    }

    public ChannelRedstone(String name) {
        super(name);
    }

    public RedstoneChannel getChannelState(int key) {
        if (!redstoneChannels.containsKey(key)) {
            RedstoneChannel rc = new RedstoneChannel();
            redstoneChannels.put(key, rc);
        }
        return redstoneChannels.get(key);
    }

    public void setChannelState(int key, int power) {
        RedstoneChannel rc;
        if (redstoneChannels.containsKey(key)) {
            rc = redstoneChannels.get(key);
            rc.setPower(power);
        } else {
            rc = new RedstoneChannel(power);
        }

        redstoneChannels.put(key, rc);
        markDirty();
    }

    public void setChannelName(int key, String name) {
        RedstoneChannel rc;
        if (redstoneChannels.containsKey(key)) {
            rc = redstoneChannels.get(key);
            rc.setName(name);
        } else {
            rc = new RedstoneChannel(name);
        }

        redstoneChannels.put(key, rc);
        markDirty();
    }

    public static ChannelRedstone get(World world) {
        ChannelRedstone data = (ChannelRedstone) world.loadItemData(ChannelRedstone.class, IDENTIFIER);
        if (data == null) {
            data = new ChannelRedstone();
            world.setItemData(IDENTIFIER, data);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        redstoneChannels.clear();
        NBTTagList channels = nbt.getTagList("channels", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < channels.tagCount(); i++) {
            NBTTagCompound miniCompound = new NBTTagCompound();
            RedstoneChannel rc;
            int flag = miniCompound.getInteger("flag");
            int power = miniCompound.getInteger("power");
            if (miniCompound.hasKey("name")) {
                String name = miniCompound.getString("name");
                rc = new RedstoneChannel(power, name);
            } else {
                rc = new RedstoneChannel(power);
            }
            redstoneChannels.put(flag, rc);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList channels = new NBTTagList();
        for (Map.Entry<Integer, RedstoneChannel> entry : redstoneChannels.entrySet()) {
            NBTTagCompound miniCompound = new NBTTagCompound();
            miniCompound.setInteger("flag", entry.getKey());
            miniCompound.setInteger("power", entry.getValue().getPower());
            if (entry.getValue().hasName())
                miniCompound.setString("name", entry.getValue().getName());
            channels.appendTag(miniCompound);
        }
        nbt.setTag("flags", channels);
    }

    public static class RedstoneChannel {

        public RedstoneChannel() {

        }

        public RedstoneChannel(int power) {
            setPower(power);
        }

        public RedstoneChannel(String name) {
            setName(name);
        }

        public RedstoneChannel(int power, String name) {
            setName(name);
            setPower(power);
        }

        private int power = 0;
        private String name;

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean hasName() {
            return name != null;
        }
    }

}
