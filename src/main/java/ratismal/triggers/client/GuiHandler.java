package ratismal.triggers.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ratismal.triggers.client.gui.GuiTriggerItem;
import ratismal.triggers.client.gui.GuiWirelessReceiver;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.common.ref.RefGui;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiHandler implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RefGui.WIRELESS_RECEIVER:
                return new ContainerEmpty(player, world, x, y, z);
            case RefGui.PROXIMITY_TRIGGER:
                return new ContainerEmpty(player, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RefGui.WIRELESS_RECEIVER:
                return new GuiWirelessReceiver(player, world, x, y, z);
            case RefGui.PROXIMITY_TRIGGER:
                return new GuiTriggerItem(player, world, x, y, z);
        }

        return null;
    }

}
