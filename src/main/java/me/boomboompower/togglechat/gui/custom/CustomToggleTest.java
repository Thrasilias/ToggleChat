package me.boomboompower.togglechat.gui.custom;

import me.boomboompower.togglechat.gui.modern.ModernGui;
import me.boomboompower.togglechat.gui.modern.ModernTextBox;
import me.boomboompower.togglechat.toggles.custom.TypeCustom;
import me.boomboompower.togglechat.utils.ChatColor;

import net.minecraft.client.renderer.GlStateManager;

import org.lwjgl.input.Keyboard;

import java.awt.*;

public class CustomToggleTest extends ModernGui {

    private ModernTextBox text;

    private TypeCustom custom;

    public CustomToggleTest(TypeCustom customIn) {

        this.custom = customIn;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.textList.clear();

        Keyboard.enableRepeatEvents(true);

        this.textList.add(this.text = new ModernTextBox(this.width / 2 - 150, this.height / 2 - 10, 300, 20));

        this.text.setForceOldTheme(true);
        this.text.setMaxStringLength(1000);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        drawCenteredString(this.fontRendererObj, String.format("Testing %s", ChatColor.GOLD + this.custom._getName()), this.width / 2, this.height / 2 - 85, Color.WHITE.getRGB());

        double upSize = 1.5;

        GlStateManager.pushMatrix();
        GlStateManager.scale(upSize, upSize, 0);
        if (this.text.getText().isEmpty()) {
            drawCenteredString(this.fontRendererObj, "Write in the box below to test your custom toggle!", (int) ((this.width / 2) / upSize), (int) ((this.height / 2 - 50) / upSize), Color.WHITE.getRGB());
        } else if (this.custom.shouldToggle(this.text.getText())) {
            drawCenteredString(this.fontRendererObj, ChatColor.GREEN + "Will be toggled", (int) ((this.width / 2) / upSize), (int) ((this.height / 2 - 50) / upSize), Color.WHITE.getRGB());
        } else {
            drawCenteredString(this.fontRendererObj, ChatColor.RED + "Will not be toggled", (int) ((this.width / 2) / upSize), (int) ((this.height / 2 - 50) / upSize), Color.WHITE.getRGB());
        }
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        this.text.updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
}