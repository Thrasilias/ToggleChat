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
import me.boomboompower.togglechat.gui.modern.ModernGui;
import me.boomboompower.togglechat.toggles.ToggleBase;
import me.boomboompower.togglechat.utils.ChatColor;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeBuildBattle extends ToggleBase {

    private Pattern battlePattern = Pattern.compile("(?<battle>.*\\w) (?<rank>\\[.+] )?(?<player>\\S{1,16}): (?<message>.*)");

    public boolean showBuildBattle = true;

    @Override
    public String getName() {
        return "Build Battle";
    }

    @Override
    public String getDisplayName() {
        return "Build battle: %s";
    }

    // Rookie [MVP+] boomboompower: tt

    @Override
    public boolean shouldToggle(String message) {
        Matcher matcher = this.battlePattern.matcher(ChatColor.stripColor(message));

        return matcher.matches() && validBattleRank(matcher.group("battle"));
    }

    @Override
    public boolean isEnabled() {
        return this.showBuildBattle;
    }

    @Override
    public void setToggled(boolean enabled) {
        this.showBuildBattle = enabled;
    }

    @Override
    public void onClick(ModernButton button) {
        this.showBuildBattle = !this.showBuildBattle;
        button.setText(String.format(getDisplayName(), ModernGui.getStatus(isEnabled())));
    }

    @Override
    public LinkedList<String> getDescription() {
        return asLinked(
                "Turns all build battle",
                "chat on or off",
                "",
                "You can now play build",
                "battle chat free!"
        );
    }

    private boolean validBattleRank(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        switch (input) {
            case "Rookie":
            case "Untrained":
            case "Amateur":
            case "Apprentice":
            case "Experienced":
            case "Seasoned":
            case "Trained":
            case "Skilled":
            case "Talented":
            case "Professional":
            case "Expert":
            case "Master":
            case "#1 Builder":
                return true;
            default:
                return false;
        }
    }
}