package ratismal.triggers.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Ratismal on 2016-01-20.
 */

public class TMessageHandler implements IMessageHandler<ITMessage, ITMessage> {

    @Override
    public ITMessage onMessage(ITMessage message, MessageContext ctx)
    {
        if (message.getHandlingSide() == ctx.side)
        {
            message.onMessage(ctx);
        }

        return null;
    }
}
