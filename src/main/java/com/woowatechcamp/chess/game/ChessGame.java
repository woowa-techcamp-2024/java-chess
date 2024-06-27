package com.woowatechcamp.chess.game;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Position;

public class ChessGame {
    private final Board board;
    private Piece.Color currentTurn;

    public ChessGame() {
        board = new Board();
        board.initialize();
        board.print();
        currentTurn = Piece.Color.WHITE;
    }

    public void move(Position from, Position to) {
        Piece piece = board.findPiece(from);
        if (piece == null) {
            throw new IllegalArgumentException("No piece at the given position");
        }
        if (piece.getColor() != currentTurn) {
            throw new IllegalArgumentException("It's not your turn");
        }
        board.move(from, to);
        board.print();
        printScore();
        switchTurn();
    }

    private void printScore() {
        System.out.println("White Score: " + board.calculatePoint(Piece.Color.WHITE));
        System.out.println("Black Score: " + board.calculatePoint(Piece.Color.BLACK));
    }

    public boolean isFinish() {
        return !board.isKingAlive(Piece.Color.WHITE) || !board.isKingAlive(Piece.Color.BLACK);
    }

    private void switchTurn() {
        currentTurn = (currentTurn == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public Piece getPieceAt(Position position) {
        return board.findPiece(position);
    }

    public double calculateScore(Piece.Color color) {
        return board.calculatePoint(color);
    }
}
