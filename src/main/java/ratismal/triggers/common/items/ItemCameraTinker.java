package ratismal.triggers.common.items;

import ratismal.triggers.common.blocks.BaseBlockTrigger;
import ratismal.triggers.common.channels.ChannelRedstone;
import ratismal.triggers.common.ref.RefItems;
import ratismal.triggers.common.tileentity.ModeProximityTrigger;
import ratismal.triggers.common.tileentity.TileTrigger;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.ChatHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class ItemCameraTinker extends BaseItem {

    public ItemCameraTinker() {
        super(RefItems.cameraTinker);
    }

    public NBTTagCompound getSafeTagCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("mode", 0);

            NBTTagCompound editCompound = new NBTTagCompound();
            tagCompound.setTag("edit", editCompound);
            NBTTagCompound linkCompound = new NBTTagCompound();
            tagCompound.setTag("link", linkCompound);
            NBTTagCompound copyCompound = new NBTTagCompound();
            tagCompound.setTag("copy", copyCompound);

            stack.setTagCompound(tagCompound);
        }
        return stack.getTagCompound();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        NBTTagCompound tagCompound = getSafeTagCompound(itemStackIn);

        if (playerIn.isSneaking()) {
            int mode = tagCompound.getInteger("mode");
            mode++;
            if (mode >= 3) {
                mode = 0;
            }
            tagCompound.setInteger("mode", mode);
            switch (mode) {
                case 0:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Edit", true);
                    break;
                case 1:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Copy", true);
                    break;
                case 2:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Reset", true);
                    break;
                default:
                    ChatHelper.sendMessageToPlayer(playerIn, "You broke something. Good job. We're all so proud.", true);
                    break;
            }
        } else {
            if (tagCompound.getInteger("mode") == 2) {
                ChannelRedstone.get(worldIn).resetBlocks(worldIn);
                ChatHelper.sendMessageToPlayer(playerIn, "Resetting triggers", true);
            } else if (tagCompound.getInteger("mode") == 1) {
                NBTTagCompound copyCompound = tagCompound.getCompoundTag("copy");
                copyCompound.setBoolean("copying", false);
                ChatHelper.sendMessageToPlayer(playerIn, "Clearing copied data", true);
            }
        }
        return itemStackIn;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {

        NBTTagCompound tagCompound = getSafeTagCompound(stack);
        switch (tagCompound.getInteger("mode")) {
            case 0:
                break;
            case 1:
                NBTTagCompound copyCompound = tagCompound.getCompoundTag("copy");
                if (worldIn.getBlockState(pos).getBlock() instanceof BaseBlockTrigger) {
                    if (copyCompound.getBoolean("copying")) {
                        TileTrigger tt = (TileTrigger) worldIn.getTileEntity(pos);
                        //NBTTagCompound tempCompound = tt.getTagCompound();
                        //tempCompound.setInteger("flag", copyCompound.getCompoundTag("backup").getInteger("flag"));
                        //tempCompound.setBoolean("goOnce", copyCompound.getCompoundTag("backup").getBoolean("goOnce"));
                        //tt.writeToNBT(tempCompound);
                        NBTTagCompound backupCompound = copyCompound.getCompoundTag("backup");
                        tt.setFlag(backupCompound.getInteger("flag"));
                        tt.setGoOnce(backupCompound.getBoolean("goOnce"));
                        if (tt instanceof TileTriggerProximity) {
                            TileTriggerProximity ttp = (TileTriggerProximity) tt;
                            if (backupCompound.hasKey("item"))
                                ttp.setStack(ItemStack.loadItemStackFromNBT(backupCompound.getCompoundTag("item")));
                            if (backupCompound.hasKey("mode"))
                                ttp.setMode(ModeProximityTrigger.get(backupCompound.getInteger("mode")));
                        }
                        // tt.markDirty();
                        ChatHelper.sendMessageToPlayer(playerIn, "Data loaded", true);
                    } else {
                        TileTrigger tileTrigger = (TileTrigger) worldIn.getTileEntity(pos);
                        copyCompound.setTag("backup", tileTrigger.getTagCompound());
                        copyCompound.setString("blockname", worldIn.getBlockState(pos).getBlock().getRegistryName());
                        copyCompound.setBoolean("copying", true);
                        ChatHelper.sendMessageToPlayer(playerIn, "Data copied", true);
                    }
                }
                break;
            case 2:
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        int mode = getSafeTagCompound(stack).getInteger("mode");
        switch (mode) {
            case 0:
                list.add(EnumChatFormatting.WHITE + "Mode: Edit");
                break;
            case 1:
                list.add(EnumChatFormatting.WHITE + "Mode: Copy");
                break;
            case 2:
                list.add(EnumChatFormatting.WHITE + "Mode: Reset");
                break;
            default:
                break;
        }
        list.add(" ");
        if (GuiScreen.isShiftKeyDown()) {
            list.add(EnumChatFormatting.WHITE + "This item lets you");
            switch (mode) {
                case 0:
                    list.add(EnumChatFormatting.WHITE + "edit a trigger block's");
                    list.add(EnumChatFormatting.WHITE + "settings");
                    break;
                case 1:
                    list.add(EnumChatFormatting.WHITE + "copy a trigger block's settings to");
                    list.add(EnumChatFormatting.WHITE + "another trigger block");
                    break;
                case 2:
                    list.add(EnumChatFormatting.WHITE + "reset all triggers to");
                    list.add(EnumChatFormatting.WHITE + "their original state");
                    break;
                default:
                    break;
            }
            list.add("");
            list.add(EnumChatFormatting.WHITE + "Right click this item while");
            list.add(EnumChatFormatting.WHITE + "sneaking to change modes");
        } else {
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GOLD + "SHIFT");
            list.add(EnumChatFormatting.WHITE + "for more information");
        }
    }
}
