/*
 *     Copyright (C) 2020 Isophene
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

package me.boomboompower.togglechat.toggles.custom.conditions;

import me.boomboompower.togglechat.toggles.custom.ToggleCondition;

/**
 * This code was created by OrangeMarshall and used with permission by boomboompower.
 * <p>
 * Full credit to OrangeMarshall
 *
 * @author OrangeMarshall
 */
public class ConditionStartsWith extends ToggleCondition {

    public ConditionStartsWith(String input) {
        super(input);
    }

    @Override
    public Boolean apply(String input) {
        return input.startsWith(getText());
    }

    @Override
    public ConditionType getConditionType() {
        return ConditionType.STARTSWITH;
    }
}
