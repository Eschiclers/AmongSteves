package dev.chicle.amongsteves.gamestate;

import dev.chicle.amongsteves.gamestate.event.GameStateChangeEvent;
import org.bukkit.Bukkit;

public enum GameState {
    IN_LOBBY, IN_GAME, IN_DISCUSSION, IN_VOTING, FINISHED;

    private static GameState _currentState = IN_LOBBY;

    public static void setState(GameState state) {
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(getState(), state));
        GameState._currentState = state;
    }

    public static GameState getState() {
        return _currentState;
    }

    @Override
    public String toString() {
        switch (this) {
            case IN_LOBBY:
                return "En lobby";
            case IN_GAME:
                return "En juego";
            case IN_DISCUSSION:
                return "En discusión";
            case IN_VOTING:
                return "En votación";
            case FINISHED:
                return "Finalizado";
            default:
                return "";
        }
    }
}