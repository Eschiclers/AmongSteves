package dev.chicle.amongsteves.gamemanager;

import dev.chicle.amongsteves.gamestate.GameState;
import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import org.bukkit.Bukkit;

public class GameManager {
    private static GameState state;

    public GameManager() {
        state = GameState.IN_LOBBY;
    }

    public void setState(GameState state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(getState(), state));
        GameManager.state = state;
    }

    public GameState getState() {
        return state;
    }
}