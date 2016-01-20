package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.ref.RefGui;
import ratismal.triggers.common.tileentity.TileTriggerPlayer;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class BlockTriggerPlayer extends BaseBlockTrigger implements ITileEntityProvider{

    public BlockTriggerPlayer() {
        super(RefBlocks.BLOCK_TRIGGER_PLAYER);
        GameRegistry.registerTileEntity(TileTriggerPlayer.class, RefBlocks.BLOCK_TRIGGER_PLAYER);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTriggerPlayer();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        TileTriggerPlayer te = (TileTriggerPlayer) WorldHelper.getTileEntity(worldIn, pos);
        //TriggersMod.logger.info("Meow");
        if (!te.isNoCol() && collidingEntity instanceof EntityPlayer) {
            //TriggersMod.logger.info("Meow2");
            te.updatePlayerIsInMe();
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem() != null && playerIn.getHeldItem().getItem() instanceof ItemCameraTinker) {
                NBTTagCompound tagCompound = playerIn.getHeldItem().getTagCompound();
                int mode = tagCompound.getInteger("mode");
                if (mode == 0) {
                    //TriggersMod.logger.info("Opening gui");
                    playerIn.openGui(TriggersMod.instance, RefGui.WIRELESS_RECEIVER, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
        return true;
    }

}
