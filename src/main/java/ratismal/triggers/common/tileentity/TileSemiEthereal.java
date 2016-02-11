package ratismal.triggers.common.tileentity;

import net.minecraft.util.ITickable;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemEyeTransient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Ratismal on 2016-01-13.
 */

public class TileSemiEthereal extends BaseTile implements ITickable {

    int timeCount = 0;
    int delayLength = 20;

    @Override
    public void update() {
        super.update();
        if (timeCount >= delayLength) {
            //worldObj.getBlockState(pos).getBlock().notify();
            //if (worldObj.isRemote) {
                worldObj.markBlockForUpdate(pos);
            //}
            //worldObj.notify();
            timeCount = 0;
        }

        timeCount++;
    }

    public boolean is(EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return ((ItemEyeTransient) item).checkBlock(helmet, this);
        }

        return false;
    }

    public boolean isNoCol(EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return true;
        }

        return false;
    }

    public boolean is(Entity e) {
        return (e instanceof EntityPlayer) && is((EntityPlayer) e);
    }

    public boolean is() {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && is(player);
    }

    public boolean isNoCol() {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && isNoCol(player);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }
/*
    //@SideOnly(Side.CLIENT)
    @Override
    public void update() {
        if (worldObj.isRemote) {
            delayCounter--;
            if (delayCounter <= 0) {
                toggleTexture(this.getWorld(), this.getPos(), worldObj.getBlockState(this.getPos()), TriggersMod.ClientProxy.getThePlayer().inventory.armorItemInSlot(3) != null &&
                        TriggersMod.ClientProxy.getThePlayer().inventory.armorItemInSlot(3).getItem() instanceof ItemEyeTransient);
                delayCounter = 10;
            }
        }
    }

    // Counter that is decremented faster if there are more entities in the vicinity.
    // If it reaches negative we goOnce the light
    private int counter = 0;

    // To prevent counting entities every tick we delay it for 10 ticks and remember the last count we had.
    private int delayCounter = 10;

    //@SideOnly(Side.CLIENT)
    public void toggleTexture(World world, BlockPos pos, IBlockState state, boolean visible) {
        //TriggersMod.logger.info("Toggling textures");
        if (worldObj.isRemote) {
            TileSemiEthereal te = getTileEntity(world, pos);
            if (world.getBlockState(pos).getValue(BaseBlockSemiEthereal.VISIBLE) != visible) {
                TriggersMod.logger.info("Toggling texture to " + visible);
                world.setBlockState(pos, state.withProperty(BaseBlockSemiEthereal.VISIBLE, visible), 2);
            }
        }
    }


    public TileSemiEthereal getTileEntity(World world, BlockPos pos) {
        return (TileSemiEthereal) world.getTileEntity(pos);
    }
    */

}
