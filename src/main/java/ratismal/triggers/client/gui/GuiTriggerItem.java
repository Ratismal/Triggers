package ratismal.triggers.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.common.network.PacketHandler;
import ratismal.triggers.common.network.messages.MessageChannelRedstone;
import ratismal.triggers.common.network.messages.MessageTriggerPlayer;
import ratismal.triggers.common.tileentity.TileTrigger;

import java.io.IOException;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiTriggerItem extends GuiContainer {

    final int X_SIZE = 224;
    final int Y_SIZE = 96;
    final int ID_BUTTON_FLAG = 543;
    final int ID_BUTTON_NAME = 544;
    final int ID_TEXT_FLAG = 545;
    final int ID_TEXT_NAME = 546;
    final int TEXT_WIDTH = 78;
    final int TEXT_HEIGHT = 18;
    final int BUTTON_HEIGHT = TEXT_HEIGHT + 2;
    final int BUTTON_WIDTH = 52;


    final ResourceLocation background = new ResourceLocation("triggers:textures/gui/triggerplayer.png");
    TileTrigger te;

    GuiTextField textFlag;
    GuiTextField textName;

    public GuiTriggerItem(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerEmpty(player, world, x, y, z));
        this.xSize = X_SIZE;
        this.ySize = Y_SIZE;
        this.te = (TileTrigger) world.getTileEntity(new BlockPos(x, y, z));
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(ID_BUTTON_FLAG, guiLeft + 96, guiTop + 22, BUTTON_WIDTH, BUTTON_HEIGHT, "Set Flag"));
        this.buttonList.add(new GuiButton(ID_BUTTON_NAME, guiLeft + 96, guiTop + 53, BUTTON_WIDTH, BUTTON_HEIGHT, "Set Name"));
        //this.buttonList.add(new GuiBut)
        textFlag = new GuiTextField(ID_TEXT_FLAG, fontRendererObj, guiLeft + 10, guiTop + 23, TEXT_WIDTH, TEXT_HEIGHT);
        textFlag.setFocused(false);
        textFlag.setMaxStringLength(4);
        textFlag.setText(String.valueOf(te.getFlag()));

        textName = new GuiTextField(ID_TEXT_NAME, fontRendererObj, guiLeft + 11, guiTop + 55, TEXT_WIDTH, TEXT_HEIGHT);
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
        fontRendererObj.drawString("Flag:", 9, 13, 4210752);
        fontRendererObj.drawString("Name:", 9, 45, 4210752);

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
                TriggersMod.logger.info(te.getChannelName());
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
