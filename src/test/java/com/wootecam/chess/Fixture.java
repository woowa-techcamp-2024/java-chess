package com.wootecam.chess;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;

public final class Fixture {

    private Fixture() {
    }

    public static Board createBoard() {
        return new Board();
    }

    public static Position createPosition(String position) {
        return new Position(position);
    }
}
