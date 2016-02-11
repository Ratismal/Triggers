package ratismal.triggers.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ratismal.triggers.common.tileentity.ModeProximityTrigger;
import ratismal.triggers.common.tileentity.TileTriggerProximity;
import ratismal.triggers.common.utils.LogHelper;

/**
 * Created by Ratismal on 2016-02-10.
 */

public class ButtonProximityMode extends GuiButton {

    final ResourceLocation icon1 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_1.png");
    final ResourceLocation icon2 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_2.png");
    final ResourceLocation icon3 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_3.png");
    final ResourceLocation icon4 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_4.png");

    private ModeProximityTrigger mode = ModeProximityTrigger.PLAYER;

    public ButtonProximityMode(int buttonId, int x, int y) {
        super(buttonId, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        int posX;
        if (this.visible) {
            switch (mode) {
                case PLAYER:
                    posX = 0;
                    break;
                case MOB:
                    posX = 16;
                    break;
                case ITEM:
                    posX = 32;
                    break;
                case ENTITY:
                    posX = 48;
                    break;
                default:
                    posX = 0;
                    break;
            }
            //LogHelper.debugInfo("Rendering a " + mode.toString() + " button.");
            //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            //GlStateManager.enableBlend();
            //GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            //GlStateManager.blendFunc(770, 771);
            mc.getTextureManager().bindTexture(icon1);

            this.drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, posX, 0, 16, 16);
        }
    }

    public void setMode(ModeProximityTrigger mode) {
        this.mode = mode;
    }
}
