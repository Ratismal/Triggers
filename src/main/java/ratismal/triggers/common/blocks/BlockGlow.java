package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileSemiEthereal;
import ratismal.triggers.common.tileentity.TileTriggerProximity;

/**
 * Created by Ratismal on 2016-02-15.
 */

public class BlockGlow extends BaseBlockSemiEthereal {

    public BlockGlow() {
        super(RefBlocks.BLOCK_GLOW);
        setLightLevel(1F);
    }

}
