package io.ratismal.triggers.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ItemFirst extends Item {

    public ItemFirst() {
        setRegistryName("firstitem");        // The unique name (within your mod) that identifies this item
        setUnlocalizedName("firstitem");     // Used for localization (en_US.lang)
        GameRegistry.registerItem(this);
    }

}