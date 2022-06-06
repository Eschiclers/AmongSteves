package dev.chicle.amongsteves.gamestate;

import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public enum GameState {
    /**
     * La partida se encuentra en el lobby
     */
    IN_LOBBY("En el lobby"),
    /**
     * La partida ya ha empezado
     */
    IN_GAME("En partida"),
    /**
     * La partida se encuentra en discusión. Justo antes de las votaciones.
     */
    IN_DISCUSSION("En discusión"),
    /**
     * La partida se encuentra en las votaciones.
     */
    IN_VOTE("En votación"),
    /**
     * La partida acaba de terminar.
     */
    FINISHED("Finalizada");

    private final String label;

    GameState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static GameState byName(String name) {
        for(GameState state : values()) {
            if(state.name().equalsIgnoreCase(name)) {
                return state;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return label;
    }

}