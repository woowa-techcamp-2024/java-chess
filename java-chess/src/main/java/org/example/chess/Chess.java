package org.example.chess;

import org.example.chess.pieces.global.Position;

public class Chess {
    private ChessGame chessGame;
    private ChessView chessView;

    public Chess(ChessGame chessGame, ChessView chessView) {
        this.chessGame = chessGame;
        this.chessView = chessView;
    }

    public void move(Position from, Position to) throws RuntimeException {
        chessGame
    }
}
