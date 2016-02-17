package ratismal.triggers.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ratismal.triggers.client.gui.button.ButtonItemInput;
import ratismal.triggers.client.gui.button.ButtonProximityMode;
import ratismal.triggers.common.container.ContainerTriggerProximity;
import ratismal.triggers.common.network.PacketHandler;
import ratismal.triggers.common.network.messages.MessageTriggerBase;
import ratismal.triggers.common.network.messages.MessageTriggerProximity;
import ratismal.triggers.common.tileentity.ModeProximityTrigger;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.LogHelper;

import java.io.IOException;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiTriggerProximity extends GuiTriggerBase {

    final int ID_BUTTON_MODE = 548;
    final int ID_BUTTON_INPUT = 549;
    final int X_SIZE = 224;
    final int Y_SIZE = 193;

    TileTriggerProximity ttp;
    boolean selecting = false;

    ButtonItemInput buttonInput;

    ItemStack stack;

    final ResourceLocation background = new ResourceLocation("triggers:textures/gui/triggerproximity.png");


    public GuiTriggerProximity(EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z, new ContainerTriggerProximity(player, world, new BlockPos(x, y, z)));
    }

    @Override
    public ResourceLocation getBackground() {
        return background;
    }

    @Override
    public int getX_SIZE() {
        return X_SIZE;
    }

    @Override
    public int getY_SIZE() {
        return Y_SIZE;
    }


    @Override
    public void initGui() {
        super.initGui();
        ttp = (TileTriggerProximity) te;
        ButtonProximityMode buttonMode = new ButtonProximityMode(ID_BUTTON_MODE, guiLeft + 156, guiTop + 54);
        buttonMode.setMode(ttp.getMode());
        this.buttonList.add(buttonMode);

        buttonInput = new ButtonItemInput(ID_BUTTON_INPUT, guiLeft + 180, guiTop + 54);
        buttonInput.setItem(ttp.getStack());
        switch (ttp.getMode()) {
            case MOB:
                buttonInput.visible = true;
                break;
            case ITEM:
                buttonInput.visible = true;
                break;
            default:
                buttonInput.visible = false;
                break;
        }
        this.buttonList.add(buttonInput);
    }


    @Override
    public void updateScreen() {
        super.updateScreen();
        //textName.getText();
        //textFlag.getText();
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        super.drawGuiContainerForegroundLayer(param1, param2);
        fontRendererObj.drawString("Mode:", 156, 45, TEXT_COLOUR_CODE);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
        super.drawGuiContainerBackgroundLayer(var1, var2, var3);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        Slot slot = getSlotUnderMouse();
        if (selecting) {
            slot = getSlotUnderMouse();
            if (slot != null) {
                stack = slot.getStack();
                LogHelper.debugInfo("You selected " + stack.getDisplayName());

                buttonInput.setItem(stack);
                ttp.setStack(stack);
                PacketHandler.INSTANCE.sendToServer(new MessageTriggerProximity(te.getFlag(), te.getPos(), te.goOnce, ttp.getMode(), stack));
            } else {
                super.mouseClicked(mouseX, mouseY, mouseButton);
            }
            selecting = false;
            //selecting = false;
        } else {
            if (slot == null)
                super.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id) {
            case ID_BUTTON_MODE:
                ModeProximityTrigger newMode = ttp.getMode().next();
                PacketHandler.INSTANCE.sendToServer(new MessageTriggerProximity(te.getFlag(), te.getPos(), te.goOnce, newMode, stack));
                ttp.setMode(newMode);
                ((ButtonProximityMode) button).setMode(newMode);
                switch (ttp.getMode()) {
                    case MOB:
                        buttonInput.visible = true;
                        break;
                    case ITEM:
                        buttonInput.visible = true;
                        break;
                    default:
                        buttonInput.visible = false;
                        break;
                }
                //LogHelper.debugInfo(newMode.toString() + " (" + newMode.getID() + ")");
                break;
            case ID_BUTTON_INPUT:
                if (!selecting) {
                    LogHelper.debugInfo("Now select an item.");
                } else {
                    stack = null;
                    LogHelper.debugInfo("Item is now null");

                    buttonInput.setItem(stack);
                    ttp.setStack(stack);
                    PacketHandler.INSTANCE.sendToServer(new MessageTriggerProximity(te.getFlag(), te.getPos(), te.goOnce, ttp.getMode(), stack));
                }
                selecting = !selecting;
                break;
        }
    }
}
