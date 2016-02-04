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

    protected void notifyBlockUpdate() {
        //int oldMeta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        //int newMeta = updateMetaData(oldMeta);
        //if (oldMeta != newMeta) {
        //    worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, newMeta, 2);
        //}
        worldObj.notifyNeighborsOfStateChange(pos, getBlockType());
        worldObj.markBlockForUpdate(pos);
    }

    protected void checkStateClient() {

    }

    protected void checkStateServer() {

    }

}
