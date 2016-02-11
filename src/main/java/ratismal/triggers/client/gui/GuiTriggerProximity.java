package ratismal.triggers.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ratismal.triggers.client.gui.button.ButtonProximityMode;
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

    public GuiTriggerProximity(EntityPlayer player, World world, int x, int y, int z) {
        super(player, world, x, y, z);
    }
    TileTriggerProximity ttp;

    @Override
    public void initGui() {
        super.initGui();
        ttp = (TileTriggerProximity) te;
        ButtonProximityMode buttonMode = new ButtonProximityMode(ID_BUTTON_MODE, guiLeft + 156, guiTop + 54);
        buttonMode.setMode(ttp.getMode());
        this.buttonList.add(buttonMode);

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
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id) {
            case ID_BUTTON_MODE:
                ModeProximityTrigger newMode = ttp.getMode().next();
                PacketHandler.INSTANCE.sendToServer(new MessageTriggerProximity(te.getFlag(), te.getPos(), te.goOnce, newMode));
                ttp.setMode(newMode);
                ((ButtonProximityMode) button).setMode(newMode);
                LogHelper.debugInfo(newMode.toString() + " (" + newMode.getID() + ")");
                break;
        }
    }
}
