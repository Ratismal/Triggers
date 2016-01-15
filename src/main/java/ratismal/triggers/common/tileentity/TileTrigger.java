package ratismal.triggers.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ratismal on 2016-01-14.
 */

public class TileTrigger extends TileSemiEthereal {

    //List of coords to power
    public List<BlockPos> coordsList = new ArrayList<BlockPos>();

    //Is this block active right now
    public boolean active;

    public NBTTagCompound oldCompound;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        coordsList.clear();
        NBTTagCompound compound2 = compound.getCompoundTag("coords");
        for (int i = 0; compound2.hasKey(String.valueOf(i)); i++) {
            coordsList.add(intToPos(compound2.getIntArray(String.valueOf(i))));
        }

        active = compound.getBoolean("active");

        oldCompound = compound;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagCompound compound2 = new NBTTagCompound();
        int i = 0;
        for (BlockPos coords : coordsList) {
            compound2.setIntArray(String.valueOf(i), posToInt(coords));
            i++;
        }
        compound.setTag("coords", compound2);

        compound.setBoolean("active", active);

        oldCompound = compound;
    }

    public NBTTagCompound getTagCompound() {
        return oldCompound;
    }

    public BlockPos intToPos(int[] coords) {
        if (coords.length >= 3)
            return new BlockPos(coords[0], coords[1], coords[2]);
        return null;
    }

    public int[] posToInt(BlockPos pos) {
        return new int[]{pos.getX(), pos.getY(), pos.getZ()};
    }


    public void addTarget(BlockPos newTarget) {
        if (!coordsList.contains(newTarget)) {
            BlockPos target = newTarget;
            coordsList.add(target);
            this.worldObj.markBlockForUpdate(this.pos);

            if (target != null)
            {
                IBlockState targetState = worldObj.getBlockState(target);
                targetState.getBlock().onNeighborBlockChange(worldObj, target, targetState, this.blockType);
                worldObj.notifyNeighborsOfStateChange(target, targetState.getBlock());
            }

        }
    }

}
