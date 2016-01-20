package ratismal.triggers.common.blocks;

import ratismal.triggers.common.ref.RefBlocks;
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

public class BlockWirelessEmitter extends BaseBlockTrigger implements ITileEntityProvider {
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

}



