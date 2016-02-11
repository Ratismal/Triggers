package ratismal.triggers.common.init;

import ratismal.triggers.common.items.ItemCameraTinker;
import ratismal.triggers.common.items.ItemContainerMob;
import ratismal.triggers.common.items.ItemEyeTransient;
import ratismal.triggers.common.items.ItemPickSubtle;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Ratismal on 2016-01-11.
 */

public class ModItems {

    public static ItemEyeTransient itemEyeTransient;
    public static ItemCameraTinker itemCameraTinker;
    public static ItemPickSubtle itemPickSubtle;
    public static ItemContainerMob itemContainerMob;

    public static void init() {
        itemEyeTransient = new ItemEyeTransient();
        itemCameraTinker = new ItemCameraTinker();
        itemPickSubtle = new ItemPickSubtle();
        itemContainerMob = new ItemContainerMob();
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemCameraTinker.initModel();
        itemEyeTransient.initModel();
        itemPickSubtle.initModel();
        itemContainerMob.initModel();
    }

}
