package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.gamestate.GameState;
import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import lombok.Getter;
import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getLogger;

public class GameManager {
    @Getter
    private static GameState state;

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
}