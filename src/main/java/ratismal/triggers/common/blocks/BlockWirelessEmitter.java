package ratismal.triggers.common.blocks;

import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileWirelessEmitter;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class BlockWirelessEmitter extends BaseBlockTrigger implements ITileEntityProvider {
    //receives the wireless signal, emits redstone signal

    //public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    //public static final PropertyDirection VISIBLE = PropertyDirection.create("visible", EnumFacing.Plane.HORIZONTAL);

    public boolean isOn;

    public BlockWirelessEmitter() {
        super(RefBlocks.blockWirelessEmitter);
        GameRegistry.registerTileEntity(TileWirelessEmitter.class, RefBlocks.blockWirelessEmitter);

    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileWirelessEmitter();
    }

    static HashSet<BlockPos> notifiedPositions = new HashSet<BlockPos>();

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        //TriggersMod.logger.info("We're doing block changes in the block");
        if (notifiedPositions.contains(pos)) {
            return;
        }
        notifiedPositions.add(pos);

        TileWirelessEmitter te = (TileWirelessEmitter) worldIn.getTileEntity(pos);
        te.onNeighborBlockChange(worldIn, pos, state, neighborBlock);

        notifiedPositions.remove(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileWirelessEmitter te = (TileWirelessEmitter) worldIn.getTileEntity(pos);
        te.togglePowered(worldIn, pos);
        //te.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
        return true;
    }
}



