package io.ratismal.triggers.common.items;

import io.ratismal.triggers.common.tab.CreativeTabTriggers;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
}
