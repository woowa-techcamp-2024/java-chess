package org.example.chess.Game;

import org.example.chess.board.Board;

abstract class Game {
    protected final Board board;

    Game(Board board) {
        this.board = board;
    }

    abstract double calculatePoint();
    abstract void move(String src, String dest);
}
