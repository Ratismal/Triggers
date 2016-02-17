package ratismal.triggers.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ratismal.triggers.client.gui.GuiTriggerProximity;
import ratismal.triggers.client.gui.GuiTriggerBase;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.client.ref.RefGui;
import ratismal.triggers.common.container.ContainerTriggerProximity;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiHandler implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RefGui.TRIGGER_BASE:
                return new ContainerEmpty(player, world, x, y, z);
            case RefGui.TRIGGER_PROXIMITY:
                return new ContainerTriggerProximity(player, world, new BlockPos(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RefGui.TRIGGER_BASE:
                return new GuiTriggerBase(player, world, x, y, z, new ContainerEmpty(player, world, x, y, z));
            case RefGui.TRIGGER_PROXIMITY:
                return new GuiTriggerProximity(player, world, x, y, z);
        }

        return null;
    }

}
