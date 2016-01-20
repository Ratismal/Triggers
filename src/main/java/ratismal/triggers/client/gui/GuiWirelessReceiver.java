package ratismal.triggers.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.gui.GuiSlider;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ratismal.triggers.common.container.ContainerEmpty;
import ratismal.triggers.common.tileentity.TileTrigger;
import ratismal.triggers.common.tileentity.TileWirelessReceiver;

import java.io.IOException;

/**
 * Created by Ratismal on 2016-01-15.
 */

public class GuiWirelessReceiver extends GuiContainer {

    final ResourceLocation background = new ResourceLocation("triggers:textures/gui/triggerplayer.png");
    TileTrigger te;

    GuiTextField textFlag;
    GuiTextField textName;

    public GuiWirelessReceiver(EntityPlayer player, World world, int x, int y, int z) {
        super(new ContainerEmpty(player, world, x, y, z));

        this.xSize = 160;
        this.ySize = 64;
        this.te = (TileTrigger) world.getTileEntity(new BlockPos(x, y, z));
    }

    @Override
    public void initGui() {
        super.initGui();

        this.buttonList.add(new GuiButton(543, guiLeft + 84, guiTop + 13, 52, 18, "Set Flag"));
        this.buttonList.add(new GuiButton(544, guiLeft + 84, guiTop + 40, 52, 18, "Set Name"));

        textFlag = new GuiTextField(545, fontRendererObj, guiLeft + 25, guiTop + 14, 52, 16);
        textFlag.setFocused(false);
        textFlag.setMaxStringLength(4);
        textFlag.setText(String.valueOf(te.getFlag()));

        textName = new GuiTextField(546, fontRendererObj, guiLeft + 25, guiTop + 41, 52, 16);
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

        if (button.id == 543)
        {
            int flag = Integer.parseInt(textFlag.getText());
            //int flag = Integer.getInteger(textFlag.getText());
            te.setFlag(flag);
            if (te.getChannelName() != null) {
                textName.setText(te.getChannelName());
            }
        }
        else if (button.id == 544)
        {
            te.setChannelName(textName.getText());
        }
    }
}
