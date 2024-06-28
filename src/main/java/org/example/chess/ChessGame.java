package org.example.chess;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.global.Position;

public class ChessGame {
    private Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void moveTo(Position from, Position to) throws RuntimeException{
        board.moveTo(from, to);
    }

    private void validateNotEmpty(Piece piece) {
        if (piece.isBlank()) {
            throw new RuntimeException("시작 자리에 말이 존재하지 않습니다.");
        }
    }

    public void setPiece(Piece piece, Position position) {
        board.setPiece(position, piece);
    }

    public void initializeBoard() {
        board.initialize();
    }

    public void emptyBoard() {
        board.initializeEmpty();
    }
}
