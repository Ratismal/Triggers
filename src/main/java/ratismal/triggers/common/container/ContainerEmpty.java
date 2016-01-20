package ratismal.triggers.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class ContainerEmpty extends Container {

    public ContainerEmpty(EntityPlayer player, World world, int x, int y, int z)
    {

    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
