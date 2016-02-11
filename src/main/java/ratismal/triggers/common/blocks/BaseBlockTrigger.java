package ratismal.triggers.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.client.ref.RefGui;

/**
 * Created by Ratismal on 2016-01-14.
 */

public class BaseBlockTrigger extends BaseBlockSemiEthereal {

    public BaseBlockTrigger (String name) {
        super(name);
        
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        // super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);

        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem() != null && playerIn.getHeldItem().getItem() instanceof ItemCameraTinker) {
                NBTTagCompound tagCompound = playerIn.getHeldItem().getTagCompound();
                int mode = tagCompound.getInteger("mode");
                if (mode == 0) {
                    //TriggersMod.logger.info("Opening gui");
                    playerIn.openGui(TriggersMod.instance, RefGui.TRIGGER_BASE, worldIn, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

}
