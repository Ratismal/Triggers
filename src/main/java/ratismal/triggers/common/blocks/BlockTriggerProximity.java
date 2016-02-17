package ratismal.triggers.common.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureCompass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.client.ref.RefGui;
import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.items.ItemContainerMob;
import ratismal.triggers.common.ref.RefBlocks;
import ratismal.triggers.common.tileentity.ModeProximityTrigger;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.LogHelper;
import ratismal.triggers.common.utils.WorldHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-01.
 */

public class BlockTriggerProximity extends BlockEmitter implements ITileEntityProvider {

    public static final PropertyEnum MODE = PropertyEnum.create("mode", ModeProximityTrigger.class);

    public BlockTriggerProximity() {
        super(RefBlocks.BLOCK_TRIGGER_PROXIMITY);
        this.setDefaultState(this.blockState.getBaseState().withProperty(MODE, ModeProximityTrigger.PLAYER).withProperty(VISIBLE, false));
        GameRegistry.registerTileEntity(TileTriggerProximity.class, RefBlocks.BLOCK_TRIGGER_PROXIMITY);
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        LogHelper.debugInfo("Creating new TileTriggerProximity");
        return new TileTriggerProximity();
    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List<AxisAlignedBB> list, Entity collidingEntity) {
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        TileTriggerProximity te = (TileTriggerProximity) WorldHelper.getTileEntity(worldIn, pos);
        //TriggersMod.logger.info("Meow");
        if (!is(collidingEntity) && !worldIn.isRemote) {

            switch (te.getMode()) {
                case PLAYER:
                    if (collidingEntity instanceof EntityPlayer)
                        te.updateEntityIsInMe();
                    break;
                case MOB:
                    if (collidingEntity instanceof EntityLiving) {
                        if (te.getStack() != null) {
                            EntityLiving entityMob = (EntityLiving) collidingEntity;
                            if (te.getStack().getItem() instanceof ItemContainerMob) {
                                if (entityMob.getName().equals(te.getStack().getTagCompound().getString("mobName"))) {
                                    LogHelper.debugInfo("Mobs matched!");
                                    te.updateEntityIsInMe();
                                } else {
                                    LogHelper.debugInfo("Mobs didn't match!");
                                    return;
                                }
                            }
                        }
                        te.updateEntityIsInMe();
                    }
                    break;
                case ITEM:
                    if (collidingEntity instanceof EntityItem) {
                        if (te.getStack() != null) {
                            EntityItem entityItem = (EntityItem) collidingEntity;
                            if (entityItem.getEntityItem().getItem() == te.getStack().getItem()) {
                                LogHelper.debugInfo("Items matched!");
                                te.updateEntityIsInMe();
                            } else {
                                LogHelper.debugInfo("Items did not match!");
                                return;
                            }
                        }
                        te.updateEntityIsInMe();
                    }
                    break;
                case ENTITY:
                    te.updateEntityIsInMe();
                    break;
            }
            //TriggersMod.logger.info("Meow2");
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

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, VISIBLE, MODE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        //LogHelper.debugInfo("Meta = " + meta + ". Thus, MODE = " + (meta & 3) + " and VISIBLE = " + ((meta & 8) != 0));
        return getDefaultState()
                .withProperty(MODE, ModeProximityTrigger.get(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        ModeProximityTrigger type = (ModeProximityTrigger) state.getValue(MODE);
        meta += type.getID();
        //LogHelper.debugInfo("Type from meta: " + type.getName() + ". Total meta: " + meta);
        return meta;
    }

}
