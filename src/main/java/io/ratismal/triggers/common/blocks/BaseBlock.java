package io.ratismal.triggers.common.blocks;

import io.ratismal.triggers.common.tab.CreativeTabTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class BaseBlock extends Block {

    public BaseBlock(String name, Material material) {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabTriggers.Triggers_TAB);
    }

    public BaseBlock(String name, float hardness) {
        super(Material.rock);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(hardness);
        setCreativeTab(CreativeTabTriggers.Triggers_TAB);

    }

    public BaseBlock(String name) {
        super(Material.rock);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabTriggers.Triggers_TAB);

    }

}
