package org.example.chess.Game;

import org.example.chess.board.Board;

abstract class Rule {
    protected final Board board;

    Rule(Board board) {
        this.board = board;
    }

    abstract double calculatePoint();
    abstract void move(String src, String dest);
}
