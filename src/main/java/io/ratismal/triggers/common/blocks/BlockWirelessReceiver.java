package io.ratismal.triggers.common.blocks;

import io.ratismal.triggers.common.ref.RefBlocks;
import io.ratismal.triggers.common.tileentity.TileWirelessReceiver;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class BlockWirelessReceiver extends BaseBlockSemiEthereal implements ITileEntityProvider {
    //receives the wireless signal, emits redstone signal

    //public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    //public static final PropertyDirection VISIBLE = PropertyDirection.create("visible", EnumFacing.Plane.HORIZONTAL);


    public BlockWirelessReceiver() {
        super(RefBlocks.wirelessReceiverBlock);
        GameRegistry.registerTileEntity(TileWirelessReceiver.class, RefBlocks.wirelessReceiverBlock);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessReceiver();
    }


}


