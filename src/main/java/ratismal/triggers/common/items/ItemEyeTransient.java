package ratismal.triggers.common.items;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumChatFormatting;
import ratismal.triggers.common.ref.RefItems;
import ratismal.triggers.common.tab.CreativeTabTriggers;
import ratismal.triggers.common.tileentity.TileSemiEthereal;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class ItemEyeTransient extends ItemArmor {

    public ItemEyeTransient() {
        super(ArmorMaterial.GOLD, 1, 0);
        setRegistryName(RefItems.eyeTransient);
        setUnlocalizedName(RefItems.eyeTransient);
        setCreativeTab(CreativeTabTriggers.Triggers_TAB);
        setMaxStackSize(1);
        GameRegistry.registerItem(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        final ModelResourceLocation inactiveModel = new ModelResourceLocation(getRegistryName() + "_0", "inventory");
        final ModelResourceLocation activeModel = new ModelResourceLocation(getRegistryName() + "_1", "inventory");

        ModelBakery.registerItemVariants(this, inactiveModel, activeModel);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                if (isActive(stack)) {
                    return activeModel;
                } else {
                    return inactiveModel;
                }
            }
        });
    }

    private boolean isActive(ItemStack stack) {
        if (!getTagCompoundSafe(stack).hasKey("active")) {
            getTagCompoundSafe(stack).setBoolean("active", false);
        }
        return getTagCompoundSafe(stack).getBoolean("active");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (isActive(stack)) {
                getTagCompoundSafe(stack).setBoolean("active", false);
            } else {
                getTagCompoundSafe(stack).setBoolean("active", true);
            }
        }
        return stack;
    }

    private NBTTagCompound getTagCompoundSafe(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            stack.setTagCompound(tagCompound);
        }
        return tagCompound;
    }

    public boolean checkBlock(ItemStack stack) {
        return getTagCompoundSafe(stack).getBoolean("active");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        boolean active = getTagCompoundSafe(stack).getBoolean("active");

        if (active)
            list.add(EnumChatFormatting.WHITE + "Mode: Bypass");
        else
            list.add(EnumChatFormatting.WHITE + "Mode: Normal");

        list.add(" ");
        if (GuiScreen.isShiftKeyDown()) {
            list.add(EnumChatFormatting.WHITE + "Wearing this item lets");
            list.add(EnumChatFormatting.WHITE + "you see semi-ethereal");
            list.add(EnumChatFormatting.WHITE + "blocks.");
            if (active) {
                list.add(EnumChatFormatting.WHITE + "You will not activate");
                list.add(EnumChatFormatting.WHITE + "triggers.");
            } else {
                list.add(EnumChatFormatting.WHITE + "You will activate");
                list.add(EnumChatFormatting.WHITE + "triggers as normal.");
            }
            list.add("");
            list.add(EnumChatFormatting.WHITE + "Right click this item while");
            list.add(EnumChatFormatting.WHITE + "sneaking to change modes");
        } else {
            list.add(EnumChatFormatting.WHITE + "Hold " + EnumChatFormatting.GOLD + "SHIFT");
            list.add(EnumChatFormatting.WHITE + "for more information");
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (isActive(stack))
            return "triggers:textures/models/armor/eye_1.png";
        else
            return "triggers:textures/models/armor/eye_0.png";

    }
}
