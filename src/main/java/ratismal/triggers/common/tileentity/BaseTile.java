package ratismal.triggers.common.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

/**
 * Created by Ratismal on 2016-01-20.
 */

public class BaseTile extends TileEntity implements ITickable {


    @Override
    public void update() {
        if (worldObj.isRemote)
            checkStateClient();
        else
            checkStateServer();
    }

    protected void checkStateClient() {

    }

    protected void checkStateServer() {

    }

}
