package ratismal.triggers.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import ratismal.triggers.common.utils.LogHelper;

/**
 * Created by Ratismal on 2016-02-12.
 */

public class TestTile extends TileSemiEthereal {

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        LogHelper.debugInfo("TestTile reading from NBT");
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        LogHelper.debugInfo("TestTile writing to NBT");
        super.writeToNBT(compound);
    }
}
