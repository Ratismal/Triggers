package io.ratismal.triggers.common.init;

import io.ratismal.triggers.common.blocks.BaseBlockSemiEthereal;
import io.ratismal.triggers.common.blocks.BlockData;
import io.ratismal.triggers.common.blocks.BlockWirelessReceiver;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ModBlocks {

    public static BlockData blockData;
    public static BlockWirelessReceiver blockWirelessReceiver;

    public static void init() {
        blockData = new BlockData();
        blockWirelessReceiver = new BlockWirelessReceiver();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockData.initModel();
        blockWirelessReceiver.initModel();
    }
}