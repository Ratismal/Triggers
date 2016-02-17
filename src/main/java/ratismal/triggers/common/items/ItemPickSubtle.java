package ratismal.triggers.common.items;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import ratismal.triggers.common.blocks.BaseBlockSemiEthereal;
import ratismal.triggers.common.ref.RefItems;
import ratismal.triggers.common.tab.CreativeTabTriggers;
import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-13.
 */

public class ItemPickSubtle extends BaseItem {

    public ItemPickSubtle() {
        super(RefItems.pickSubtle);
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return false;
    }

    @Override
    public boolean canItemEditBlocks() {
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (block instanceof BaseBlockSemiEthereal) {
            worldIn.destroyBlock(pos, true);
        }

        return super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (GuiScreen.isShiftKeyDown()) {

            list.add(EnumChatFormatting.WHITE + "Right click on");
            list.add(EnumChatFormatting.WHITE + "semi-ethereal blocks");
            list.add(EnumChatFormatting.WHITE + "with this item to");
            list.add(EnumChatFormatting.WHITE + "remove them.");

        } else {
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GOLD + "SHIFT");
            list.add(EnumChatFormatting.WHITE + "for more information");
        }
    }
}
