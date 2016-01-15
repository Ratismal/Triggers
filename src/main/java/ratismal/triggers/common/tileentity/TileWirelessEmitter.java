package ratismal.triggers.common.tileentity;

import ratismal.triggers.TriggersMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Ratismal on 2016-01-13.
 */

public class TileWirelessEmitter extends TileTrigger {

    public static Set<TileWirelessEmitter> interfaces = Collections.newSetFromMap(new WeakHashMap());

    public TileWirelessEmitter()
    {
        synchronized (interfaces)
        {
            interfaces.add(this);
        }
    }

    static HashSet<BlockPos> checkedPositions = new HashSet<BlockPos>();


    public static synchronized int getRedstonePower(World blockWorld, BlockPos pos, EnumFacing facing)
    {
        if (checkedPositions.contains(pos))
        {
            return 0;
        }
        checkedPositions.add(pos);

        int totalPower = 0;

        BlockPos checkingBlock = pos.offset(facing.getOpposite());
        synchronized (interfaces)
        {
            for (TileWirelessEmitter wirelessEmitter : interfaces) {
                TriggersMod.logger.info("Synced function");
                for (BlockPos target : wirelessEmitter.coordsList) {
                    if (!wirelessEmitter.isInvalid() && wirelessEmitter.worldObj == blockWorld && target != null) {
                        if (target.equals(pos)) {
                            int remotePower = wirelessEmitter.worldObj.getRedstonePower(wirelessEmitter.pos, facing);
                            checkedPositions.remove(pos);

                            if (remotePower > totalPower) {
                                totalPower = remotePower;
                            }
                        } else if (target.equals(checkingBlock)) {
                            int remotePower = wirelessEmitter.worldObj.getRedstonePower(wirelessEmitter.pos.offset(facing), facing);
                            checkedPositions.remove(pos);

                            if (remotePower > totalPower) {
                                totalPower = remotePower;
                            }
                        }
                    }
                }
            }
        }

        checkedPositions.remove(pos);
        return totalPower;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        //TriggersMod.logger.info("We're doing block changes in the tile entity now");
        if (this.coordsList != null) {
            for (BlockPos target : coordsList) {
                //BlockRedstoneWire
                if (!worldObj.isRemote)
                    TriggersMod.logger.info("Doing some pretty neat shit to a block at " + target.getX() + " " + target.getY() + " " + target.getZ());

                IBlockState targetState = worldIn.getBlockState(target);

                targetState.getBlock().onNeighborBlockChange(worldIn, target, targetState, neighborBlock);
                worldIn.notifyNeighborsOfStateChange(target, targetState.getBlock());
            }
        }
    }

    public static int getStrongPower(BlockPos pos, EnumFacing facing) {
        return 0;
    }

    boolean powered = false;

    public void togglePowered(World world, BlockPos pos) {
        if (powered) {
            for (BlockPos coords : coordsList) {
                IBlockState state = world.getBlockState(coords);
                state.withProperty(BlockRedstoneWire.POWER, 0);
            }
            powered = false;
        } else {
            for (BlockPos coords : coordsList) {
                IBlockState state = world.getBlockState(coords);
                state.withProperty(BlockRedstoneWire.POWER, 15);
            }
            powered = true;

        }
    }

}
