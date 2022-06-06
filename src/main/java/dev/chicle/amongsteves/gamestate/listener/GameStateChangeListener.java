package dev.chicle.amongsteves.gamestate.listener;

import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getLogger;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev){
        getLogger().info("GameStateChangeEvent: " + ev.getOldGameState().name() + " -> " + ev.getNewGameState().name());
    }
}
