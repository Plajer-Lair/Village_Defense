/*
 * Village Defense 3 - Protect villagers from hordes of zombies
 * Copyright (C) 2018  Plajer's Lair - maintained by Plajer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.plajer.villagedefense3.arena;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author TomTheDeveloper
 * <p>
 * Contains all GameStates.
 */
@Getter
@AllArgsConstructor
public enum ArenaState {
    WAITING_FOR_PLAYERS(LanguageManager.getLanguageMessage("Signs.Game-States.Waiting")),
    STARTING(LanguageManager.getLanguageMessage("Signs.Game-States.Starting")),
    IN_GAME(LanguageManager.getLanguageMessage("Signs.Game-States.In-Game")),
    ENDING(LanguageManager.getLanguageMessage("Signs.Game-States.Waiting")),
    RESTARTING(LanguageManager.getLanguageMessage("Signs.Game-States.Restarting"));

    String formattedName;
    
    public ArenaState (String value) {
        formattedName = value;
    }

    public String getFormattedName() {
        return formattedName;
    }
}
