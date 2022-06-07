package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.gamemanager.player.ASPlayer;
import dev.chicle.amongsteves.event.GameStateChangeEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class GameManager {
    @Getter
    private static GameState state;
    private static ASPlayer[] players;

    public GameManager() {
        state = GameState.IN_LOBBY;
    }

    /**
     * @param state El nuevo estado del juego
     */
    public void setState(GameState state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(getState(), state));
        getLogger().info("GameStateChangeEvent: " + getState() + " -> " + state);
        GameManager.state = state;
    }

    public static void addPlayer(ASPlayer player) {
        if (players == null) {
            players = new ASPlayer[1];
        } else {
            ASPlayer[] newPlayers = new ASPlayer[players.length + 1];
            System.arraycopy(players, 0, newPlayers, 0, players.length);
            players = newPlayers;
        }
        players[players.length - 1] = player;
    }

    public static void removePlayer(ASPlayer player) {
        if (players == null) {
            return;
        }
        ASPlayer[] newPlayers = new ASPlayer[players.length - 1];
        int i = 0;
        for (ASPlayer p : players) {
            if (p != player) {
                newPlayers[i] = p;
                i++;
            }
        }
        players = newPlayers;
    }

    public static ASPlayer[] getPlayers() {
        return players;
    }

    public static ASPlayer getPlayer(Player player) {
        if (players == null) return null;
        for (ASPlayer p : players) {
            if (p.getPlayer() == player) {
                return p;
            }
        }
        return null;
    }
}