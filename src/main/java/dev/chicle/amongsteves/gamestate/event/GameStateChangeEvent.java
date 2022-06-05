package dev.chicle.amongsteves.gamestate.event;

import dev.chicle.amongsteves.gamestate.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private static GameState oldGameState;
    private static GameState newGameState;

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    public GameStateChangeEvent(GameState oldGameState, GameState newGameState){
        this.oldGameState = oldGameState;
        this.newGameState = newGameState;
    }

    public GameState getOldGameState() {
        return oldGameState;
    }

    public GameState getNewGameState() {
        return newGameState;
    }

    @Override
    public HandlerList getHandlers(){
        return HANDLERS;
    }


}
