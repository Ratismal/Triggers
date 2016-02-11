package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class BlockTriggerItem extends BlockEmitter implements ITileEntityProvider {

    public BlockTriggerItem() {
        super(RefBlocks.BLOCK_TRIGGER_ITEM);
        GameRegistry.registerTileEntity(TileTriggerProximity.class, RefBlocks.BLOCK_TRIGGER_ITEM);
    }



    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTriggerProximity();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        TileTriggerProximity te = (TileTriggerProximity) WorldHelper.getTileEntity(worldIn, pos);
        //TriggersMod.logger.info("Meow");
        if (!te.isNoCol() && collidingEntity instanceof EntityItem && !worldIn.isRemote) {
            //TriggersMod.logger.info("Meow2");
            te.updateEntityIsInMe();
        }
    }

}
