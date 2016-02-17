package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TestTile;
import ratismal.triggers.common.tileentity.TileWirelessReceiver;

/**
 * Created by Ratismal on 2016-02-11.
 */

public class TestBlock extends BaseBlock implements ITileEntityProvider {

    //This exist for the pure purpose of testing.

    public TestBlock() {
        super("testblock");
        GameRegistry.registerTileEntity(TestTile.class, "testblock");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TestTile();
    }

}
