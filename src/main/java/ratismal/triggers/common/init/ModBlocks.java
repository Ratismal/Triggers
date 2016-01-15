package ratismal.triggers.common.init;

import ratismal.triggers.common.blocks.BlockData;
import ratismal.triggers.common.blocks.BlockWirelessEmitter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ModBlocks {

    public static BlockData blockData;
    public static BlockWirelessEmitter blockWirelessEmitter;

    public static void init() {
        blockData = new BlockData();
        blockWirelessEmitter = new BlockWirelessEmitter();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockData.initModel();
        blockWirelessEmitter.initModel();
    }
}