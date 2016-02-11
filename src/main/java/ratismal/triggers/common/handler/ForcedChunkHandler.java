package ratismal.triggers.common.handler;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import ratismal.triggers.common.blocks.BlockChunkLoader;
import ratismal.triggers.common.tileentity.TileChunkLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2016-02-04.
 */

public class ForcedChunkHandler implements ForgeChunkManager.OrderedLoadingCallback {

    @Override
    public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
        for (ForgeChunkManager.Ticket ticket : tickets) {
            BlockPos pos = new BlockPos(ticket.getModData().getInteger("posX"),
                    ticket.getModData().getInteger("posY"),
                    ticket.getModData().getInteger("posZ"));
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileChunkLoader) {
                ((TileChunkLoader) te).forceChunkLoading(ticket);
            }

        }
    }

    @Override
    public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount) {
        List<ForgeChunkManager.Ticket> validTickets = new ArrayList();
        for (ForgeChunkManager.Ticket ticket : tickets) {
            BlockPos pos = new BlockPos(ticket.getModData().getInteger("posX"),
                    ticket.getModData().getInteger("posY"),
                    ticket.getModData().getInteger("posZ"));
            Block block = world.getBlockState(pos).getBlock();
            if (block instanceof BlockChunkLoader)
                validTickets.add(ticket);
        }
        return validTickets;
    }

}
