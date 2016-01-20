package ratismal.triggers.common.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Ratismal on 2016-01-20.
 */

public interface ITMessage extends IMessage {

    public void onMessage(MessageContext context);

    public abstract Side getHandlingSide();

}
