package ratismal.triggers.common.init;

import ratismal.triggers.common.blocks.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ModBlocks {

    public static BlockWirelessEmitter blockWirelessEmitter;
    public static BlockWirelessReceiver blockWirelessReceiver;
    //public static BlockTriggerPlayer blockTriggerPlayer;
    //public static BlockTriggerMob blockTriggerMob;
   // public static BlockTriggerItem blockTriggerItem;
    //public static BlockTriggerEntity blockTriggerEntity;
    public static BlockTriggerProximity blockTriggerProximity;
    public static BlockBarrier blockBarrier;
    public static BlockCreativeJammer blockCreativeJammer;
    public static BlockChunkLoader blockChunkLoader;
    public static BlockGlow blockGlow;
    //public static TestBlock testBlock;

    public static void init() {
        blockWirelessEmitter = new BlockWirelessEmitter();
        blockWirelessReceiver = new BlockWirelessReceiver();
        //blockTriggerPlayer = new BlockTriggerPlayer();
        //blockTriggerMob= new BlockTriggerMob();
        //blockTriggerItem = new BlockTriggerItem();
        //blockTriggerEntity = new BlockTriggerEntity();
        blockBarrier = new BlockBarrier();
        blockCreativeJammer = new BlockCreativeJammer();
        blockChunkLoader= new BlockChunkLoader();
        blockTriggerProximity = new BlockTriggerProximity();
        blockGlow = new BlockGlow();
        //testBlock = new TestBlock();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockWirelessEmitter.initModel();
        blockWirelessReceiver.initModel();
        //blockTriggerPlayer.initModel();
        //blockTriggerMob.initModel();
        //blockTriggerEntity.initModel();
        //blockTriggerItem.initModel();
        blockBarrier.initModel();
        blockCreativeJammer.initModel();
        blockChunkLoader.initModel();
        blockTriggerProximity.initModel();
        blockGlow.initModel();
        //testBlock.initModel();
    }
}