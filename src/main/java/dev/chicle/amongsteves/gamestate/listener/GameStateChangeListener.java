package dev.chicle.amongsteves.gamestate.listener;

import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStateChangeListener implements Listener {
    @EventHandler
    public void onGameStateChange(GameStateChangeEvent ev){
        System.out.println(ev.getOldGameState().toString());
        System.out.println(ev.getNewGameState().toString());
    }
}
