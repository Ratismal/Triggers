package ratismal.triggers.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;

/**
 * Created by Ratismal on 2016-01-20.
 */

public class MessageUtil {

    public static void writeBlockPos(BlockPos pos, ByteBuf buffer)
    {
        buffer.writeInt(pos.getX());
        buffer.writeInt(pos.getY());
        buffer.writeInt(pos.getZ());
    }

    public static BlockPos readBlockPos(ByteBuf buffer)
    {
        int x = buffer.readInt();
        int y = buffer.readInt();
        int z = buffer.readInt();

        return new BlockPos(x, y, z);
    }

}
