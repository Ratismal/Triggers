package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
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
import ratismal.triggers.common.tileentity.TileTriggerEntity;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class BlockTriggerItem extends BlockEmitter implements ITileEntityProvider {

    public BlockTriggerItem() {
        super(RefBlocks.BLOCK_TRIGGER_ITEM);
        GameRegistry.registerTileEntity(TileTriggerEntity.class, RefBlocks.BLOCK_TRIGGER_ITEM);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTriggerEntity();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        TileTriggerEntity te = (TileTriggerEntity) WorldHelper.getTileEntity(worldIn, pos);
        //TriggersMod.logger.info("Meow");
        if (!te.isNoCol() && collidingEntity instanceof EntityItem && !worldIn.isRemote) {
            //TriggersMod.logger.info("Meow2");
            te.updateEntityIsInMe();
        }
    }

}
