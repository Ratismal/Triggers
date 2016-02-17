package ratismal.triggers.common.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
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

    ItemStack stack;

    public ModeProximityTrigger mode = ModeProximityTrigger.PLAYER;


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        //LogHelper.debugInfo("read mode = " + ModeProximityTrigger.get(compound.getInteger("mode")));
        mode = ModeProximityTrigger.get(compound.getInteger("mode"));

        if (compound.hasKey("item"))
            stack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("item"));
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        //LogHelper.debugInfo("write mode = " + mode.getID());
        compound.setInteger("mode", mode.getID());
        if (stack != null)
            compound.setTag("item", stack.serializeNBT());
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
        this.mode = mode;


        if (!worldObj.isRemote) {
            worldObj.setBlockState(pos, worldObj.getBlockState(pos).withProperty(BlockTriggerProximity.MODE, mode));
            //LogHelper.debugInfo("Setting mode server-side");
        }
        markDirty();
        worldObj.markBlockForUpdate(getPos());
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
        markDirty();
        worldObj.markBlockForUpdate(getPos());
        LogHelper.debugInfo("The stack has been set!");
    }

    public ItemStack getStack() {
        return stack;
    }

    public ModeProximityTrigger getMode() {
        return mode;
    }


    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        /*
        if (!newSate.getProperties().containsKey(BlockTriggerProximity.MODE)) {
            return super.shouldRefresh(world, pos, oldState, newSate);
        }
        return oldState.getValue(BlockTriggerProximity.MODE) == newSate.getValue(BlockTriggerProximity.MODE) && super.shouldRefresh(world, pos, oldState, newSate);
        */
        return false;
    }

}
