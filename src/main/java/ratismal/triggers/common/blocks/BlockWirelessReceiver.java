package ratismal.triggers.common.blocks;

import net.minecraft.block.BlockLever;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.ref.RefGui;
import ratismal.triggers.common.tileentity.TileWirelessReceiver;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class BlockWirelessReceiver extends BaseBlockTrigger implements ITileEntityProvider {


    public BlockWirelessReceiver() {
        super(RefBlocks.BLOCK_WIRELESS_RECEIVER);
        GameRegistry.registerTileEntity(TileWirelessReceiver.class, RefBlocks.BLOCK_WIRELESS_RECEIVER);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessReceiver();
    }

    @Override
    public int getStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        TileWirelessReceiver te = (TileWirelessReceiver) WorldHelper.getTileEntity(worldIn, pos);
        //BlockLever
        if (te != null)
            return te.getPowerLevel();
        else
            return 0;
    }

    @Override
    public int getWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
        TileWirelessReceiver te = (TileWirelessReceiver) WorldHelper.getTileEntity(worldIn, pos);
        if (te != null)
            return te.getPowerLevel();
        else
            return 0;
    }

}
