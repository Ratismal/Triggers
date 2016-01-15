package ratismal.triggers.common.tileentity;

import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.items.ItemEyeTransient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-13.
 */

public class TileSemiEthereal extends TileEntity /* implements ITickable */ {

    public enum Property {
        VISIBLE,
        SOLID,
    }


    @SideOnly(Side.CLIENT)
    public float visibility;

    public boolean is(Property what, EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return ((ItemEyeTransient) item).checkBlock(what, helmet, this);
        }

        return false;
    }

    public boolean isNoCol(Property what, EntityPlayer player) {
        //if (what == Property.SOLID) return true;

        ItemStack helmet = player.inventory.armorItemInSlot(3);

        if (helmet == null) return false;

        Item item = helmet.getItem();

        if (item instanceof ItemEyeTransient) {
            return true;
        }

        return false;
    }

    public boolean is(Property what, Entity e) {
        return (e instanceof EntityPlayer) && is(what, (EntityPlayer) e);
    }

    public boolean is(Property what) {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && is(what, player);
    }

    public boolean isNoCol(Property what) {
        EntityPlayer player = TriggersMod.ClientProxy.getThePlayer();
        return player != null && isNoCol(what, player);
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
    // If it reaches negative we toggle the light
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
