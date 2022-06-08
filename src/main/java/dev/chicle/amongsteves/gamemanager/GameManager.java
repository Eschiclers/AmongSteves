package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import dev.chicle.amongsteves.gamemanager.player.PlayerRole;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class GameManager {
    private Plugin plugin;
    static World world = Bukkit.getServer().getWorlds().get(0);

    @Getter
    private static GameState state;
    private static List<ASPlayer> players = new ArrayList<>();

    // Ajustes del juego
    @Getter private static int minPlayers;
    @Getter private static int maxImpostors;

    public GameManager(Plugin plugin) {
        this.plugin = plugin;
        state = GameState.IN_LOBBY;
        minPlayers = plugin.getConfig().getInt("minPlayers", 4);
        maxImpostors = plugin.getConfig().getInt("maxImpostors", 1);
    }

    /**
     * @param state El nuevo estado del juego
     */
    public static void setState(GameState state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(getState(), state));
        getLogger().info("GameStateChangeEvent: " + getState() + " -> " + state);
        GameManager.state = state;
    }

    public static void addPlayer(ASPlayer player) {
        players.add(player);
    }

    public static void removePlayer(ASPlayer player) {
        players.remove(player);
    }

    public static List<ASPlayer> getPlayers() {
        return players;
    }

    public static ASPlayer getPlayer(Player player) {
        for (ASPlayer p : players) {
            if (p.getPlayer().equals(player)) {
                return p;
            }
        }
        return null;
    }

    public static void startGame() {
        GameManager.setState(GameState.IN_GAME);

        world.setDifficulty(Difficulty.PEACEFUL);

        String impostorTitle = ChatColor.RED + PlayerRole.IMPOSTOR.name();
        String impostorSubtitle = "Acaba con todos los tripulantes";

        List<ASPlayer> impostors = new ArrayList<>();

        Random random = new Random();

        while (impostors.size() < GameManager.getMaxImpostors()) {
            ASPlayer impostor = GameManager.getPlayers().get(random.nextInt(GameManager.getPlayers().size()));
            if (impostors.contains(impostor)) continue;
            impostors.add(impostor);
            impostor.setRole(PlayerRole.IMPOSTOR);
            impostor.getPlayer().sendTitle(impostorTitle, impostorSubtitle, 10, 20, 10);
        }

        String crewmateTitle = ChatColor.AQUA + PlayerRole.CREWMATE.name();
        String crewmateSubtitle = "Hay " + ChatColor.RED + impostors.size() + (impostors.size() > 1 ? " impostores " : " impostor ") + ChatColor.WHITE + "entre nosotros";

        for (ASPlayer player : GameManager.getPlayers()) {
            if (player.getRole() == PlayerRole.NONE) {
                player.setRole(PlayerRole.CREWMATE);
                player.getPlayer().sendTitle(crewmateTitle, crewmateSubtitle, 10, 20, 10);
            }
            player.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
    }
}