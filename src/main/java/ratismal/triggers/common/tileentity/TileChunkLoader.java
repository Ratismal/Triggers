package ratismal.triggers.common.tileentity;

import com.google.common.collect.Sets;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import ratismal.triggers.TriggersMod;

/**
 * Created by Ratismal on 2016-02-04.
 */

public class TileChunkLoader extends TileSemiEthereal {

    private ForgeChunkManager.Ticket chunkTicket;

    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!worldObj.isRemote) {
            if (chunkTicket == null) {
                chunkTicket = ForgeChunkManager.requestTicket(TriggersMod.instance, world, ForgeChunkManager.Type.NORMAL);
            }
            chunkTicket.getModData().setInteger("posX", pos.getX());
            chunkTicket.getModData().setInteger("posY", pos.getY());
            chunkTicket.getModData().setInteger("posZ", pos.getZ());

            ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(pos.getX() >> 4, pos.getZ() >> 4));
        }
    }

    public void forceChunkLoading(ForgeChunkManager.Ticket ticket) {
        if (chunkTicket == null) {
            chunkTicket = ticket;
        }
        ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(pos.getX() >> 4, pos.getZ() >> 4));
    }

    @Override
    public void invalidate() {
        if (chunkTicket != null) {
            ForgeChunkManager.releaseTicket(chunkTicket);
        }
        super.invalidate();
    }

}
