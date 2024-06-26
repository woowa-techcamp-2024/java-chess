package com.wootecam.chess;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Position;
import com.wootecam.chess.board.ScoreCalculationRule;

public final class Fixture {
    private static final ScoreCalculationRule scoreCalculationRule = new ScoreCalculationRule();

    private Fixture() {
    }

    public static Board createBoard() {
        return new Board(scoreCalculationRule);
    }

    public static Position createPosition(String position) {
        return new Position(position);
    }
}
