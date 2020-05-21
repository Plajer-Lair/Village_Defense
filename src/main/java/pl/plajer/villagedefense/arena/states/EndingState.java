/*
 * Village Defense - Protect villagers from hordes of zombies
 * Copyright (C) 2019  Plajer's Lair - maintained by Plajer and contributors
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

package pl.plajer.villagedefense.arena.states;

import org.bukkit.entity.Player;
import pl.plajer.villagedefense.Main;
import pl.plajer.villagedefense.api.StatsStorage;
import pl.plajer.villagedefense.arena.Arena;
import pl.plajer.villagedefense.arena.ArenaState;
import pl.plajer.villagedefense.arena.ArenaUtils;
import pl.plajer.villagedefense.handlers.language.Messages;
import pl.plajer.villagedefense.handlers.reward.Reward;
import pl.plajer.villagedefense.user.User;

/**
 * @author Plajer
 * <p>
 * Created at 03.06.2019
 */
public class EndingState implements ArenaStateHandler {

  private Main plugin;

  @Override
  public void init(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public void handleCall(Arena arena) {
    arena.getScoreboardManager().stopAllScoreboards();
    if (arena.getTimer() <= 0) {
      arena.getGameBar().setTitle(plugin.getChatManager().colorMessage(Messages.BOSSBAR_GAME_ENDED));

      for (Player player : arena.getPlayers()) {
        ArenaUtils.resetPlayerAfterGame(player);
        arena.doBarAction(Arena.BarAction.REMOVE, player);
        ArenaUtils.addStat(player, StatsStorage.StatisticType.GAMES_PLAYED);
      }
      arena.getPlayers().forEach(arena::teleportToEndLocation);
      plugin.getChatManager().broadcast(arena, Messages.COMMANDS_TELEPORTED_TO_THE_LOBBY);

      for (User user : plugin.getUserManager().getUsers(arena)) {
        user.setSpectator(false);
        user.setStat(StatsStorage.StatisticType.ORBS, 0);
      }
      plugin.getRewardsHandler().performReward(arena, Reward.RewardType.END_GAME);
      arena.setArenaState(ArenaState.RESTARTING);
    }
    ArenaUtils.stopDoorBreakListener(arena);
    arena.setTimer(arena.getTimer() - 1);
  }

}
