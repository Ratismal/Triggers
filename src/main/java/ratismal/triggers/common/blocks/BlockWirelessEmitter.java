package ratismal.triggers.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileWirelessEmitter;
import net.minecraft.block.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class BlockWirelessEmitter extends BlockEmitter implements ITileEntityProvider {
    //receives the wireless signal, emits redstone signal

    public BlockWirelessEmitter() {
        super(RefBlocks.BLOCK_WIRELESS_EMITTER);
        GameRegistry.registerTileEntity(TileWirelessEmitter.class, RefBlocks.BLOCK_WIRELESS_EMITTER);

    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessEmitter();
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        if (!worldIn.isRemote) {
            TileWirelessEmitter te = (TileWirelessEmitter) worldIn.getTileEntity(pos);
            //if (te.canProceed())
                if (te.isActive() && !worldIn.isBlockPowered(pos)) {
                    te.setActive(false);
                } else if (!te.isActive() && worldIn.isBlockPowered(pos)) {
                    te.setActive(true);
                }
        }
    }

}



