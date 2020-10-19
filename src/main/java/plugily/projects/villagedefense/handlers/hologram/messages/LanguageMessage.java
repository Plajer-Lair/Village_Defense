package plugily.projects.villagedefense.handlers.hologram.messages;

/**
 * @author Plajer
 * <p>
 * Created at 17.06.2019
 */
public enum LanguageMessage {

  HOLOGRAMS_HEADER("Leaderboard-Holograms.Header", "&6&lTop %amount% in %statistic%"),
  HOLOGRAMS_FORMAT("Leaderboard-Holograms.Format", "&e%place%. &f%nickname% (%value%)"),
  HOLOGRAMS_FORMAT_EMPTY("Leaderboard-Holograms.Format-Empty", "&e%place%. &fEmpty (0)"),
  HOLOGRAMS_UNKNOWN_PLAYER("Leaderboard-Holograms.Unknown-Player", "&fUnknown Player"),
  STATISTIC_KILLS("Leaderboard-Holograms.Statistics.Kills", "&eKills"),
  STATISTIC_DEATHS("Leaderboard-Holograms.Statistics.Deaths", "&eDeaths"),
  STATISTIC_GAMES_PLAYED("Leaderboard-Holograms.Statistics.Games-Played", "&eGames Played"),
  STATISTIC_HIGHEST_WAVE("Leaderboard-Holograms.Statistics.Highest-Wave", "&eHighest Wave"),
  STATISTIC_LEVEL("Leaderboard-Holograms.Statistics.Level", "&eLevel"),
  STATISTIC_EXP("Leaderboard-Holograms.Statistics.Xp", "&eExperience");

  private final String accessor;
  private final String defaultMessage;

  LanguageMessage(String accessor, String defaultMessage) {
    this.accessor = accessor;
    this.defaultMessage = defaultMessage;
  }

  public String getAccessor() {
    return accessor;
  }

  public String getDefaultMessage() {
    return defaultMessage;
  }
}
