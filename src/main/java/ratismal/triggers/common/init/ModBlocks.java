package ratismal.triggers.common.init;

import ratismal.triggers.common.blocks.BlockData;
import ratismal.triggers.common.blocks.BlockTriggerPlayer;
import ratismal.triggers.common.blocks.BlockWirelessEmitter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ratismal.triggers.common.blocks.BlockWirelessReceiver;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ModBlocks {

    public static BlockData blockData;
    public static BlockWirelessEmitter blockWirelessEmitter;
    public static BlockWirelessReceiver blockWirelessReceiver;
    public static BlockTriggerPlayer blockTriggerPlayer;

    public static void init() {
        blockData = new BlockData();
        blockWirelessEmitter = new BlockWirelessEmitter();
        blockWirelessReceiver = new BlockWirelessReceiver();
        blockTriggerPlayer = new BlockTriggerPlayer();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockData.initModel();
        blockWirelessEmitter.initModel();
        blockWirelessReceiver.initModel();
        blockTriggerPlayer.initModel();
    }
}