package org.example.chess;

import org.example.chess.pieces.global.Position;

public class Chess {
    private ChessGame chessGame;
    private ChessView chessView;

    public Chess(ChessGame chessGame, ChessView chessView) {
        this.chessGame = chessGame;
        this.chessView = chessView;
    }

    public void move(String from, String to) throws RuntimeException {
        chessGame.moveTo(Position.of(from), Position.of(to));
    }

    public void show() {
        chessView.showBoard();
    }
}
