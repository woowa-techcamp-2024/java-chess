package com.wootecam.chess.common;

public enum Order {
    ASC,
    DESC;

    public boolean isAsc() {
        return this == ASC;
    }

    public boolean isDesc() {
        return this == DESC;
    }
}
