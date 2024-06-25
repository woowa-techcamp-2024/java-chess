package com.woowatechcamp.chess;

import java.util.Arrays;

public enum GameState {
    START("start"),
    END("end");

    private final String state;

    GameState(String state) {
        this.state = state;
    }

    public static GameState fromString(String state) {
        if (state == null || state.isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }
        return Arrays.stream(GameState.values())
                .filter(value -> value.state.equalsIgnoreCase(state))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such state: " + state));
    }
}
