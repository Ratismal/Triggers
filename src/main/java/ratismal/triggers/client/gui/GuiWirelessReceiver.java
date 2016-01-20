package ratismal.triggers.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.common.network.PacketHandler;
import ratismal.triggers.common.network.messages.MessageChannelRedstone;
import ratismal.triggers.common.network.messages.MessageTriggerPlayer;
import ratismal.triggers.common.tileentity.TileTrigger;

import java.io.IOException;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiWirelessReceiver extends GuiContainer {

    final int X_SIZE = 160;
    final int Y_SIZE = 64;
    final int ID_BUTTON_FLAG = 543;
    final int ID_BUTTON_NAME = 544;
    final int ID_TEXT_FLAG = 545;
    final int ID_TEXT_NAME = 546;
    final int BUTTON_HEIGHT = 16;
    final int BUTTON_WIDTH = 52;

    final ResourceLocation background = new ResourceLocation("triggers:textures/gui/triggerplayer.png");
    TileTrigger te;

    GuiTextField textFlag;
    GuiTextField textName;

    public GuiWirelessReceiver(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerEmpty(player, world, x, y, z));

        this.xSize = X_SIZE;
        this.ySize = Y_SIZE;
        this.te = (TileTrigger) world.getTileEntity(new BlockPos(x, y, z));
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(ID_BUTTON_FLAG, guiLeft + 84, guiTop + 13, BUTTON_WIDTH, BUTTON_HEIGHT + 2, "Set Flag"));
        this.buttonList.add(new GuiButton(ID_BUTTON_NAME, guiLeft + 84, guiTop + 40, BUTTON_WIDTH, BUTTON_HEIGHT + 2, "Set Name"));

        textFlag = new GuiTextField(ID_TEXT_FLAG, fontRendererObj, guiLeft + 25, guiTop + 14, BUTTON_WIDTH, BUTTON_HEIGHT);
        textFlag.setFocused(false);
        textFlag.setMaxStringLength(4);
        textFlag.setText(String.valueOf(te.getFlag()));

        textName = new GuiTextField(ID_TEXT_NAME, fontRendererObj, guiLeft + 25, guiTop + 41, BUTTON_WIDTH, BUTTON_HEIGHT);
        textName.setFocused(false);
        textName.setMaxStringLength(8);
        if (te.getChannelName() != null) {
            textName.setText(te.getChannelName());
        }

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {

        if (textFlag.isFocused() && ((typedChar >= '0' && typedChar <= '9') || typedChar == '\b')) {

            textFlag.textboxKeyTyped(typedChar, keyCode);
        }
        if (textName.isFocused()) {
            textName.textboxKeyTyped(typedChar, keyCode);
        }
        if (!textFlag.isFocused() && !textName.isFocused())
            super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textName.mouseClicked(mouseX, mouseY, mouseButton);
        textFlag.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        //textName.getText();
        //textFlag.getText();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) {
        // fontRendererObj.drawString(I18n.format("tile.wirelessreceiver.name", new Object[0]), 4, 6, 4210752);
        //int stringheight = fontRendererObj.get
        fontRendererObj.drawString("Flag:", 34, 4, 4210752);
        fontRendererObj.drawString("Name:", 34, 32, 4210752);

        // drawString(fontRendererObj, "Flag:", 34, 4, 0);
        //  drawString(fontRendererObj, "Name:", 34, 30, 0);
        //int stringWidth = fontRendererObj.getStringWidth(analogEmitter.emitLevel + "");
        //fontRendererObj.drawString(analogEmitter.emitLevel + "", xSize / 2 - stringWidth / 2 + 3, ySize / 2 - fontRendererObj.FONT_HEIGHT / 2 + 5, Colors.RED_INT);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        this.mc.renderEngine.bindTexture(background);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        textFlag.drawTextBox();
        textName.drawTextBox();

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == ID_BUTTON_FLAG)
        {
            int flag = Integer.parseInt(textFlag.getText());
            //int flag = Integer.getInteger(textFlag.getText());
            PacketHandler.INSTANCE.sendToServer(new MessageTriggerPlayer(flag, te.getPos()));
            if (te.getChannelName() != null) {
                textName.setText(te.getChannelName());
            }
            else {
                textName.setText("");
            }
        }
        else if (button.id == ID_BUTTON_NAME)
        {
            int flag = Integer.parseInt(textFlag.getText());
            //te.setChannelName(textName.getText());
            PacketHandler.INSTANCE.sendToServer(new MessageChannelRedstone(textName.getText(), flag, te.getPos()));

        }
    }
}
