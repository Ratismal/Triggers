package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileChunkLoader;

/**
 * Created by Ratismal on 2016-02-04.
 */

public class BlockChunkLoader extends BaseBlockSemiEthereal implements ITileEntityProvider {

    public BlockChunkLoader() {
        super(RefBlocks.BLOCK_CHUNK_LOADER);
        GameRegistry.registerTileEntity(TileChunkLoader.class, RefBlocks.BLOCK_CHUNK_LOADER);
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileChunkLoader) {
            ((TileChunkLoader) te).onBlockAdded(world, pos, state);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileChunkLoader();
    }



    /*
    @Override
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state, Block block, int meta) {
        super.onBlockDestroyedByPlayer(world, pos, state);
        if (this.ticket != null) {
            ForgeChunkManager.unforceChunk(ticket, new ChunkCoordIntPair(pos.getX() / 16, pos.getZ() / 16));
        }
    }
    */

/*
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCreativeJammer();
    }
*/

}
