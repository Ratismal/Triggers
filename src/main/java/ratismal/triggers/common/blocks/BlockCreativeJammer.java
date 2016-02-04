package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileCreativeJammer;
import ratismal.triggers.common.tileentity.TileSemiEthereal;
import ratismal.triggers.common.tileentity.TileTriggerEntity;

/**
 * Created by Ratismal on 2016-02-03.
 */

public class BlockCreativeJammer extends BaseBlockSemiEthereal implements ITileEntityProvider {

    public BlockCreativeJammer() {
        super(RefBlocks.BLOCK_CREATIVE_JAMMER);
        GameRegistry.registerTileEntity(TileCreativeJammer.class, RefBlocks.BLOCK_CREATIVE_JAMMER);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCreativeJammer();
    }

}
