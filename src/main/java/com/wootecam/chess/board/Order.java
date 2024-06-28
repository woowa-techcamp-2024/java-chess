package com.wootecam.chess.board;

public enum Order {
    ASC,
    DESC;

    public boolean isAsc() {
        return this == ASC;
    }
}
