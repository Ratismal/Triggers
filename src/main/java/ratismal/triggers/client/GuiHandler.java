package ratismal.triggers.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ratismal.triggers.client.gui.GuiWirelessReceiver;
import ratismal.triggers.common.container.ContainerEmpty;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiHandler implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                System.out.println("Test1");
                return new ContainerEmpty(player, world, x, y, z);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0:
                System.out.println("Test2");
                return new GuiWirelessReceiver(player, world, x, y, z);
        }

        return null;
    }

}
