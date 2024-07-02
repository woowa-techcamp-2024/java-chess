package org.example.chess;

import org.example.chess.pieces.global.Position;

public class Chess {
    private MoveActioner moveActioner;
    private ChessView chessView;
    private MoveVerifier moveVerifier;

    public Chess(MoveActioner moveActioner, ChessView chessView, MoveVerifier moveVerifier) {
        this.moveActioner = moveActioner;
        this.chessView = chessView;
        this.moveVerifier = moveVerifier;
    }

    public void move(String from, String to) {
        Position p1 = Position.of(from);
        Position p2 = Position.of(to);
        if (moveVerifier.isMovable(p1, p2)) {
            moveActioner.moveTo(Position.of(from), Position.of(to));
        }
    }

    public void show() {
        chessView.showBoard();
    }
}
