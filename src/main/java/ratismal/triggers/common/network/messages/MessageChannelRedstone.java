package ratismal.triggers.common.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.channels.ChannelRedstone;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.common.network.ITMessage;
import ratismal.triggers.common.network.MessageUtil;
import ratismal.triggers.common.tileentity.TileTrigger;

/**
 * Created by Ratismal on 2016-01-20.
 */

public class MessageChannelRedstone implements ITMessage {

    // this will store the id of the gui to open
    private int flag;
    private String name;
    private BlockPos pos;

    // The basic, no-argument constructor MUST be included to use the new automated handling
    public MessageChannelRedstone() {
    }

    // if there are any class fields, be sure to provide a constructor that allows
    // for them to be initialized, and use that constructor when sending the packet


    public MessageChannelRedstone(String name, int flag, BlockPos pos) {
        this.flag = flag;
        this.name = name;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        // basic Input/Output operations, very much like DataInputStream
        flag = buffer.readInt();
        name = ByteBufUtils.readUTF8String(buffer);
        pos = MessageUtil.readBlockPos(buffer);
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        // basic Input/Output operations, very much like DataOutputStream
        buffer.writeInt(flag);
        ByteBufUtils.writeUTF8String(buffer, name);
        MessageUtil.writeBlockPos(pos, buffer);
    }

    @Override
    public void onMessage(final MessageContext context) {
        FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                if (context.netHandler instanceof NetHandlerPlayServer) {
                    NetHandlerPlayServer handler = (NetHandlerPlayServer) context.netHandler;

                    EntityPlayerMP player = handler.playerEntity;

                    TriggersMod.logger.info(flag + " " + name);

                    if (player != null && player.openContainer != null && player.openContainer instanceof ContainerEmpty) {
                        TriggersMod.logger.info(pos.getX() + " " + pos.getY() + " " + pos.getZ());
                        TileEntity te = player.worldObj.getTileEntity(pos);

                        if (te != null && te instanceof TileTrigger) {
                            TileTrigger trigger = (TileTrigger) te;
                            TriggersMod.logger.info("Setting name");
                            trigger.setChannelName(name);

                        }
                    }

                    ChannelRedstone.get(player.worldObj).setChannelName(flag, name);
                    ChannelRedstone.get(player.worldObj).markDirty();
                }
            }
        });

    }

    @Override
    public Side getHandlingSide() {
        return Side.SERVER;
    }
}
