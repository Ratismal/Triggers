package ratismal.triggers.common.items;

import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.blocks.BaseBlockTrigger;
import ratismal.triggers.common.tab.CreativeTabTriggers;
import ratismal.triggers.common.tileentity.TileTrigger;
import ratismal.triggers.common.utils.ChatHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class ItemCameraTinker extends Item {
    public ItemCameraTinker() {
        setRegistryName("cameratinker");
        setUnlocalizedName("cameratinker");
        setCreativeTab(CreativeTabTriggers.Triggers_TAB);
        GameRegistry.registerItem(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public void initItem(ItemStack stack) {
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

    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        initItem(itemStackIn);

        NBTTagCompound tagCompound = itemStackIn.getTagCompound();

        if (playerIn.isSneaking()) {
            int mode = tagCompound.getInteger("mode");
            mode++;
            if (mode >= 3) {
                mode = 0;
            }
            tagCompound.setInteger("mode", mode);
            switch (mode) {
                case 0:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Edit");
                    break;
                case 1:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Link");
                    break;
                case 2:
                    ChatHelper.sendMessageToPlayer(playerIn, "Mode: Copy");
                    break;
                default:
                    ChatHelper.sendMessageToPlayer(playerIn, "You broke something. Good job. We're all so proud.");
                    break;
            }
        } else {
            if (tagCompound.getInteger("mode") == 1) {
                NBTTagCompound linkCompound = tagCompound.getCompoundTag("link");
                linkCompound.setBoolean("linking", false);
                ChatHelper.sendMessageToPlayer(playerIn, "Clearing link target data");
            } else if (tagCompound.getInteger("mode") == 2) {
                NBTTagCompound copyCompound = tagCompound.getCompoundTag("copy");
                copyCompound.setBoolean("copying", false);
                ChatHelper.sendMessageToPlayer(playerIn, "Clearing copied data");
            }
        }

        return itemStackIn;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        initItem(stack);

        NBTTagCompound tagCompound = stack.getTagCompound();

        switch (tagCompound.getInteger("mode")) {
            case 0:
                break;
            case 1:
                NBTTagCompound linkCompound = tagCompound.getCompoundTag("link");

                if (worldIn.getBlockState(pos).getBlock() instanceof BaseBlockTrigger) {
                    linkCompound.setInteger("targetX", pos.getX());
                    linkCompound.setInteger("targetY", pos.getY());
                    linkCompound.setInteger("targetZ", pos.getZ());
                    linkCompound.setBoolean("linking", true);
                } else {
                    if (linkCompound.getBoolean("linking")) {
                        TileTrigger tileTrigger = (TileTrigger) worldIn.getTileEntity(new BlockPos(
                                linkCompound.getInteger("targetX"),
                                linkCompound.getInteger("targetY"),
                                linkCompound.getInteger("targetZ")));
                        tileTrigger.addTarget(pos);
                        ChatHelper.sendMessageToPlayer(playerIn, "Block linked.");
                        tileTrigger.markDirty();
                    } else {
                        ChatHelper.sendMessageToPlayer(playerIn, "You must select a trigger block first.");
                    }
                }
                break;
            case 2:
                NBTTagCompound copyCompound = tagCompound.getCompoundTag("copy");
                    if (worldIn.getBlockState(pos).getBlock() instanceof BaseBlockTrigger) {
                        if (copyCompound.getBoolean("copying")) {

                        } else {
                            TriggersMod.logger.info("Copying");

                            TileTrigger tileTrigger = (TileTrigger) worldIn.getTileEntity(pos);
                            copyCompound.setTag("backup", tileTrigger.getTagCompound());
                            copyCompound.setString("blockname", worldIn.getBlockState(pos).getBlock().getRegistryName());
                            copyCompound.setBoolean("copying", true);
                        }
                    }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        initItem(stack);
        int mode = stack.getTagCompound().getInteger("mode");
        switch (mode) {
            case 0:
                list.add("Mode: Edit");
                break;
            case 1:
                list.add("Mode: Link");
                break;
            case 2:
                list.add("Mode: Copy");
                break;
            default:
                break;
        }
        list.add(" ");
        if (GuiScreen.isShiftKeyDown()) {
            switch (mode) {
                case 0:
                    list.add("Edit a trigger block's");
                    list.add("settings");
                    break;
                case 1:
                    list.add("Link a trigger block's output to ");
                    list.add("another location");
                    break;
                case 2:
                    list.add("Copy a trigger block's settings to");
                    list.add("another trigger block");
                    break;
                default:
                    break;
            }
            list.add("Right click this item while sneaking to change modes");
        } else {
            list.add("Hold " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + " for more information");
        }
    }
}
