package ratismal.triggers.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import ratismal.triggers.common.tileentity.ModeProximityTrigger;

/**
 * Created by Ratismal on 2016-02-15.
 */

public class ButtonItemInput extends GuiButton {

    final ResourceLocation icon1 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_1.png");
    final ResourceLocation icon2 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_2.png");
    final ResourceLocation icon3 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_3.png");
    final ResourceLocation icon4 = new ResourceLocation("triggers:textures/gui/proximitytrigger/icon_4.png");

    private ItemStack stack;

    public ButtonItemInput(int buttonId, int x, int y) {
        super(buttonId, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        int posX;
        if (this.visible) {

            //LogHelper.debugInfo("Rendering a " + mode.toString() + " button.");
            //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            //GlStateManager.enableBlend();
            //GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            //GlStateManager.blendFunc(770, 771);

            //this.drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, posX, 0, 16, 16);
            if (stack != null)
                mc.getRenderItem().renderItemIntoGUI(stack, this.xPosition + 2, this.yPosition + 2);
        }
    }

    public void setItem(ItemStack stack) {
        this.stack = stack;
    }

}
