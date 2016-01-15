package ratismal.triggers.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class BlockFirst extends Block {

    public BlockFirst() {
        super(Material.rock);
        setUnlocalizedName("firstblock");     // Used for localization (en_US.lang)
        setRegistryName("firstblock");        // The unique name (within your mod) that identifies this block
        GameRegistry.registerBlock(this);
    }

}