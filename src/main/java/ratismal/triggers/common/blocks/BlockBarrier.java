package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileSemiEthereal;
import ratismal.triggers.common.tileentity.TileTriggerProximity;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-03.
 */

public class BlockBarrier extends BaseBlockSemiEthereal implements ITileEntityProvider {

    public BlockBarrier() {
        super(RefBlocks.BLOCK_BARRIER);
        GameRegistry.registerTileEntity(TileSemiEthereal.class, RefBlocks.BLOCK_BARRIER);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTriggerProximity();
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        //super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        AxisAlignedBB axisalignedbb = this.getCollisionBoundingBox(worldIn, pos, state);
        TileSemiEthereal te = getTileEntity(worldIn, pos);
        if (te != null && !te.is(collidingEntity)) {
            AxisAlignedBB aabb = AxisAlignedBB.fromBounds(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
            if (aabb != null && aabb.intersectsWith(mask)) list.add(aabb);
            //TriggersMod.logger.info("Adding collision box");
            //list.add(mask);
            //toggleTexture(worldIn, pos, state, true);

        }
    }
}
