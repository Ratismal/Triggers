package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileCreativeJammer;

/**
 * Created by Ratismal on 2016-02-03.
 */

public class BlockCreativeJammer extends BaseBlockSemiEthereal implements ITileEntityProvider {

    public BlockCreativeJammer() {
        super(RefBlocks.BLOCK_CREATIVE_JAMMER);
        GameRegistry.registerTileEntity(TileCreativeJammer.class, RefBlocks.BLOCK_CREATIVE_JAMMER);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCreativeJammer();
    }

}
