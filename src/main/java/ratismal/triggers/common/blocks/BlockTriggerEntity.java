package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.client.ref.RefGui;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class BlockTriggerEntity extends BlockEmitter implements ITileEntityProvider {

    public static final PropertyInteger MODE = PropertyInteger.create("mode", 0, 3);

    public BlockTriggerEntity() {
        super(RefBlocks.BLOCK_TRIGGER_ENTITY);
        GameRegistry.registerTileEntity(TileTriggerProximity.class, RefBlocks.BLOCK_TRIGGER_ENTITY);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTriggerProximity();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        TileTriggerProximity te = (TileTriggerProximity) WorldHelper.getTileEntity(worldIn, pos);
        //TriggersMod.logger.info("Meow");
        if (!te.is() && !worldIn.isRemote) {
            //TriggersMod.logger.info("Meow2");
            te.updateEntityIsInMe();
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
        super.onEntityCollidedWithBlock(worldIn, pos, entityIn);
        //BlockLiquid
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        // super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);

        if (!worldIn.isRemote) {
            if (playerIn.getHeldItem() != null && playerIn.getHeldItem().getItem() instanceof ItemCameraTinker) {
                NBTTagCompound tagCompound = playerIn.getHeldItem().getTagCompound();
                int mode = tagCompound.getInteger("mode");
                if (mode == 0) {
                    //TriggersMod.logger.info("Opening gui");
                    playerIn.openGui(TriggersMod.instance, RefGui.TRIGGER_PROXIMITY, worldIn, pos.getX(), pos.getY(), pos.getZ());
                    return true;
                }
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
}
