/*
 *     Copyright (C) 2017 boomboompower
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.boomboompower.togglechat.toggles.defaults;

import me.boomboompower.togglechat.gui.modern.ModernButton;
import me.boomboompower.togglechat.gui.utils.GuiUtils;
import me.boomboompower.togglechat.toggles.ToggleBase;

import java.util.regex.Pattern;

public class TypeSpectator extends ToggleBase {

    private Pattern spectatorPattern = Pattern.compile("\\[SPECTATOR\\] (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    private boolean showSpecChat = true;

    @Override
    public String getName() {
        return "Spectator";
    }

    @Override
    public boolean shouldToggle(String message) {
        return this.spectatorPattern.matcher(message).matches();
    }

    @Override
    public boolean isEnabled() {
        return this.showSpecChat;
    }

    @Override
    public void setToggled(boolean enabled) {
        this.showSpecChat = enabled;
    }

    @Override
    public void onClick(ModernButton button) {
        this.showSpecChat = !this.showSpecChat;
        button.displayString = String.format(getDisplayName(), isEnabled() ? GuiUtils.ENABLED : GuiUtils.DISABLED);
    }
}