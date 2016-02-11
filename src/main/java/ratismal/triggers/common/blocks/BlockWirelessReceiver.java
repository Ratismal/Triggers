package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileWirelessReceiver;
import ratismal.triggers.common.utils.WorldHelper;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class BlockWirelessReceiver extends BaseBlockTrigger implements ITileEntityProvider {


    public BlockWirelessReceiver() {
        super(RefBlocks.BLOCK_WIRELESS_RECEIVER);
        GameRegistry.registerTileEntity(TileWirelessReceiver.class, RefBlocks.BLOCK_WIRELESS_RECEIVER);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessReceiver();
    }

    @Override
    public int getStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        TileWirelessReceiver te = (TileWirelessReceiver) WorldHelper.getTileEntity(worldIn, pos);
        //BlockLever
        if (te != null)
            if (te.getPowerLevel()) {
                return 15;
            }
            else {
                return 0;
            }
        else
            return 0;
    }

    @Override
    public int getWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        TileWirelessReceiver te = (TileWirelessReceiver) WorldHelper.getTileEntity(worldIn, pos);
        if (te != null)
            if (te.getPowerLevel()) {
                return 15;
            }
            else {
                return 0;
            }
        else
            return 0;
    }

}
