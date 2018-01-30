package me.boomboompower.togglechat.gui.custom;

import me.boomboompower.togglechat.gui.modern.ModernButton;
import me.boomboompower.togglechat.gui.modern.ModernGui;

import java.awt.*;

public class CustomToggleMain extends ModernGui {

    @Override
    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new ModernButton(0, this.width / 2 - 75, this.height / 2 - 27, 150, 20, "Create a Custom Toggle"));
        this.buttonList.add(new ModernButton(1, this.width / 2 - 75, this.height / 2 - 3, 150, 20, "Modify a Custom Toggle"));
        this.buttonList.add(new ModernButton(2, this.width / 2 - 75, this.height / 2 + 21, 150, 20, "Test a Custom Toggle"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        drawCenteredString("The hub of all Custom Toggles", this.width / 2, this.height / 2 - 51, Color.WHITE.getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void buttonPressed(ModernButton button) {
        switch (button.getId()) {
            case 0:
                this.mc.displayGuiScreen(new CustomToggleCreate(this));
                break;
            case 1:
                this.mc.displayGuiScreen(new CustomToggleSelect(SelectType.MODIFY));
                break;
            case 2:
                this.mc.displayGuiScreen(new CustomToggleSelect(SelectType.TEST));
                break;
        }
    }

    public enum SelectType {
        MODIFY,
        CREATE,
        TEST
    }
}