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

package me.boomboompower.togglechat;

import me.boomboompower.togglechat.toggles.ToggleBase;
import me.boomboompower.togglechat.utils.ChatColor;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class ToggleEvents {

    public ToggleEvents() {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onChatReceive(ClientChatReceivedEvent event) {
        String unformattedText = ChatColor.stripColor(event.message.getUnformattedText());
        try {
            if (!containsWhitelisted(unformattedText)) {
                for (ToggleBase type : ToggleBase.getToggles().values()) {
                    if (type.shouldToggle(unformattedText) && !type.isEnabled()) {
                        event.setCanceled(true);
                        break;
                    }
                }
            }
        } catch (Exception e1) {
            System.out.println("Issue has been encountered: " + (e1.getMessage() != null ? e1.getMessage() : e1.getCause()));
        }
    }

    private boolean containsWhitelisted(String message) {
        final boolean[] contains = {false};
        ToggleChatMod.getInstance().getWhitelist().forEach(s -> {
        if (containsIgnoreCase(message, s)) {
            contains[0] = true;
        }});
        return contains[0];
    }

    private boolean containsIgnoreCase(String message, String contains) {
        return Pattern.compile(Pattern.quote(contains), Pattern.CASE_INSENSITIVE).matcher(message).find();
    }
}