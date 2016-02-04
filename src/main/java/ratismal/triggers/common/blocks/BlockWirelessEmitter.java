package ratismal.triggers.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.ref.RefGui;
import ratismal.triggers.common.tileentity.TileWirelessEmitter;
import net.minecraft.block.*;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class BlockWirelessEmitter extends BlockEmitter implements ITileEntityProvider {
    //receives the wireless signal, emits redstone signal

    public BlockWirelessEmitter() {
        super(RefBlocks.BLOCK_WIRELESS_EMITTER);
        GameRegistry.registerTileEntity(TileWirelessEmitter.class, RefBlocks.BLOCK_WIRELESS_EMITTER);

    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessEmitter();
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        if (!worldIn.isRemote)
        {
            TileWirelessEmitter te = (TileWirelessEmitter) worldIn.getTileEntity(pos);
            if (te.isActive() && !worldIn.isBlockPowered(pos)) {
                te.setActive(false);
            } else if (!te.isActive() && worldIn.isBlockPowered(pos)) {
                te.setActive(true);
            }
            //TriggersMod.logger.info(te.isActive());
            //BlockTNT
            //BlockCommandBlock
        }
    }

}



