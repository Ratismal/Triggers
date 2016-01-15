package ratismal.triggers.common.tab;

import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Ratismal on 2016-01-12.
 */

public class CreativeTabTriggers {
    public static final CreativeTabs Triggers_TAB = new CreativeTabs(TriggersMod.MODID) {
        @Override
        public Item getTabIconItem() {
            return ModItems.itemEyeTransient;
        }

        @Override
        public String getTranslatedTabLabel() {
            return "Triggers";
        }
    };
}
