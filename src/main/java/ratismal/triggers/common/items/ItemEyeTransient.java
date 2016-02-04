package ratismal.triggers.common.items;

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

    public boolean checkBlock(ItemStack stack, TileSemiEthereal te) {
        return getTagCompoundSafe(stack).getBoolean("active");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (getTagCompoundSafe(stack).getBoolean("active"))
            list.add("Mode: Bypass");
        else
            list.add("Mode: Normal");
    }

}
