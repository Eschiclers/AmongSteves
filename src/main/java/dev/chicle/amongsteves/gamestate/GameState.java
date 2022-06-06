package dev.chicle.amongsteves.gamestate;

import java.util.HashMap;
import java.util.Map;

public enum GameState {
    IN_LOBBY("in_lobby", "En el lobby"),
    IN_GAME("in_game", "En partida"),
    IN_DISCUSSION("in_discussion", "En discusión"),
    IN_VOTE("in_vote", "En votación"),
    FINISHED("finished", "Finalizada");

    private final String name;
    private final String label;

    GameState(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    private static final Map<String, GameState> byName = new HashMap<>();

    static {
        for(GameState state : values()) {
            byName.put(state.getName(), state);
        }
    }

    public static GameState byName(String name) {
        return byName.get(name);
    }

    @Override
    public String toString() {
        return label;
    }



}