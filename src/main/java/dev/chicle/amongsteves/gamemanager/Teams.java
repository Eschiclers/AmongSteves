package dev.chicle.amongsteves.gamemanager;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class Teams {
    private static final Scoreboard score = Bukkit.getScoreboardManager().getMainScoreboard();

    public static void registerTeams() {
        Team playersT = score.getTeam("players");
        playersT.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        playersT.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        playersT.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.NEVER);
    }
}
