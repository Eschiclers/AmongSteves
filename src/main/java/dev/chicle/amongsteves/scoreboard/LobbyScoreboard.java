package dev.chicle.amongsteves.scoreboard;

import dev.chicle.amongsteves.gamemanager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class LobbyScoreboard {
    private String title = "AmongSteves";
    private static Scoreboard board;
    private String spacer = "".repeat(34);
    private String divider = "+" + "-".repeat(32) + "+";

    // Título del board
    Objective obj;

    // Divisores y espaciadores
    Score divider1;
    Score divider2;
    Score spacer1;

    // Líneas dinámicas
    static Team gameState;
    static Team onlineCount;


    public LobbyScoreboard() {
        board = Bukkit.getScoreboardManager().getNewScoreboard();

        obj = board.registerNewObjective("Title", "dummy", this.title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        divider1 = obj.getScore(divider + ChatColor.RESET);
        divider1.setScore(15);

        // Estado de la partida
        gameState = board.registerNewTeam("gameState");
        gameState.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        gameState.setPrefix(ChatColor.GRAY + "> " + ChatColor.DARK_AQUA + GameManager.getState().getLabel());
        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(14);

        // Primer espaciador
        spacer1 = obj.getScore(spacer + ChatColor.RESET);
        spacer1.setScore(13);

        // Contador de jugadores
        onlineCount = board.registerNewTeam("onlineCount");
        onlineCount.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        onlineCount.setPrefix(ChatColor.GRAY + "> " + ChatColor.DARK_AQUA + "Jugadores " + ChatColor.RED + GameManager.getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(12);

        // Segundo divisor
        divider2 = obj.getScore(divider + ChatColor.RESET + ChatColor.RESET);
        divider2.setScore(1);
    }

    public static Scoreboard getScoreboard() {
        return board;
    }

    public static void updateScoreboard() {
        gameState.setPrefix(ChatColor.GRAY + "> xd " + ChatColor.DARK_AQUA + GameManager.getState().getLabel());
        onlineCount.setPrefix(ChatColor.GRAY + "> " + ChatColor.DARK_AQUA + "Jugadores " + ChatColor.RED + GameManager.getPlayers().size() + ChatColor.GRAY + "/" + ChatColor.GOLD + Bukkit.getMaxPlayers());
    }
}

