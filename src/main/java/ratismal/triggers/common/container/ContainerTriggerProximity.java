package ratismal.triggers.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import ratismal.triggers.common.tileentity.TileTriggerProximity;

/**
 * Created by Ratismal on 2016-02-15.
 */

public class ContainerTriggerProximity extends ContainerEmpty {

    private TileTriggerProximity te;

    /*
     * SLOTS:
     *
     * Tile Entity 0-8 ........ 0  - 8
     * Player Inventory 9-35 .. 9  - 35
     * Player Inventory 0-8 ... 36 - 44
     */

    public ContainerTriggerProximity(EntityPlayer player, World world, BlockPos pos) {
        super(player, world, pos.getX(), pos.getY(), pos.getZ());
        IInventory playerInv =  player.inventory;

        this.te = (TileTriggerProximity) world.getTileEntity(pos);

        //this.addSlotToContainer(new Slot(te, 0, 62, 17));

        // Player Inventory, Slot 9-35, Slot IDs 9-35
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 33 + x * 18, 111 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 36-44
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 33 + x * 18, 169));
        }
    }

}
