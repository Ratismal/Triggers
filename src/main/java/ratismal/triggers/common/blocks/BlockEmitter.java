package ratismal.triggers.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ratismal.triggers.common.channels.ChannelRedstone;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class BlockEmitter extends BaseBlockTrigger {

    public BlockEmitter(String name) {
        super(name);
    }
    //Block

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        super.onBlockDestroyedByPlayer(worldIn, pos, state);
        ChannelRedstone.transmitters.remove(pos);
    }
}
