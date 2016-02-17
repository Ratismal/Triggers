package ratismal.triggers.common.items;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import ratismal.triggers.common.ref.RefItems;
import ratismal.triggers.common.utils.LogHelper;

import java.util.List;

/**
 * Created by Ratismal on 2016-02-10.
 */

public class ItemContainerMob extends BaseItem {

    public ItemContainerMob() {
        super(RefItems.containerMob);
    }

    public NBTTagCompound getSafeTagCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            LogHelper.debugInfo("Generating a new tag compound for ContainerMob!");
            NBTTagCompound tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
        }
        return stack.getTagCompound();
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target) {
        if (target.worldObj.isRemote) {
            return false;
        }
        LogHelper.debugInfo("Player interacted with entity using ContainerMob...");
        if (target instanceof EntityPlayer) {
            LogHelper.debugInfo("...but was REJECTED! (0)");
            return true;
        }
        NBTTagCompound tagCompound = getSafeTagCompound(stack);
        NBTTagCompound mobCompound = new NBTTagCompound();
        target.writeEntityToNBT(mobCompound);
        tagCompound.setString("mobName", target.getName());
        tagCompound.setTag("mob", mobCompound);
        stack.setTagCompound(tagCompound);
        playerIn.setCurrentItemOrArmor(0, stack);
        //playerIn.inventory.ad
        //stack.
        LogHelper.debugInfo("...and set the ContainerMob's NBT data!");


        return true;
        //}
        //return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced) {
        NBTTagCompound tagCompound = getSafeTagCompound(stack);
        if (tagCompound.hasKey("mobName")) {
            list.add(EnumChatFormatting.WHITE + "Current mob: " + EnumChatFormatting.DARK_PURPLE + tagCompound.getString("mobName"));
        } else {
            list.add(EnumChatFormatting.WHITE + "Currently empty");
        }
        list.add("");
        if (GuiScreen.isShiftKeyDown()) {
            list.add(EnumChatFormatting.WHITE + "Stores a mob's information");
            list.add(EnumChatFormatting.WHITE + "for use in the ProximityTrigger.");
        } else {
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GOLD + "SHIFT");
            list.add(EnumChatFormatting.WHITE + "for more information");
        }

    }
}
