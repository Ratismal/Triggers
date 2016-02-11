package ratismal.triggers.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.blocks.BlockTriggerProximity;
import ratismal.triggers.common.utils.LogHelper;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class TileTriggerProximity extends TileEmitter implements ITickable {

    final int MAX_COUNTER = 2048;
    final int DELAY_LENGTH = 2;

    int counter = 0;
    int oldCounter = 0;

    boolean entityIsInMe = false;

    ItemStack item;
    NBTTagCompound mobTags;


    private ModeProximityTrigger mode = ModeProximityTrigger.PLAYER;

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ModeProximityTrigger.get(compound.getInteger("mode"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("mode", mode.ordinal());
    }

    public boolean isEntityIsInMe() {
        return entityIsInMe;
    }

    public void setEntityIsInMe(boolean ewGrossGetItOut) {
        entityIsInMe = ewGrossGetItOut;
    }

    public void updateEntityIsInMe() {
        if (!entityIsInMe) {
            //TriggersMod.logger.debug("Block> THERE'S SOMETHING INSIDE ME THERE'S SOMETHING INSIDE ME OH GOD GET IT OUT");
            setEntityIsInMe(true);
        }
        counter++;
        if (counter >= MAX_COUNTER) {
            counter = 0;
        }

    }

    int timeCount = 0;

    @Override
    public void checkStateServer() {
        super.checkStateServer();
        //if (canProceed())
            if (entityIsInMe) {
                if (timeCount >= DELAY_LENGTH) {
                    if (oldCounter != counter) {
                        oldCounter = counter;
                        if (!isActive()) {
                            setActive(true);
                        }
                    } else {
                        setEntityIsInMe(false);
                        setActive(false);
                    }
                    timeCount = 0;
                }
                timeCount++;
            }
    }

    public void setMode(ModeProximityTrigger mode) {
        if (!worldObj.isRemote) {
            worldObj.setBlockState(pos, worldObj.getBlockState(pos).withProperty(BlockTriggerProximity.MODE, mode));
            LogHelper.debugInfo("Setting mode server-side");
        }
        this.mode = mode;
        markDirty();
        worldObj.markBlockForUpdate(getPos());
        //IBlockState state = worldObj.getBlockState(pos);
        //worldObj.
    }

    public ModeProximityTrigger getMode() {
        return mode;
    }
}
