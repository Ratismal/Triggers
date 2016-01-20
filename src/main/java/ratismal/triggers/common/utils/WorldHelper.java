package ratismal.triggers.common.utils;

import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class WorldHelper {

    public static TileEntity getTileEntity(World world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

    public static TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

    public static IBlockState getBlockState(World world, BlockPos pos) {
        return world.getBlockState(pos);
    }

}
