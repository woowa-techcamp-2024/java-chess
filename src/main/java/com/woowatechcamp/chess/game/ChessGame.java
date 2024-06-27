package com.woowatechcamp.chess.game;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Position;

import java.util.List;

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

        Piece.Color opponentColor = getOpponentColor(currentTurn);
        if (isCheckmate(opponentColor)) {
            System.out.println(currentTurn + " wins by checkmate!");
        } else if (isInCheck(opponentColor)) {
            System.out.println(opponentColor + " is in check!");
        } else {
            System.out.println("nothing");
        }

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

    public boolean isCheckmate(Piece.Color color) {
        if (!isInCheck(color)) {
            return false;
        }
        for (Piece piece : getAllPieces(color)) {
            for (Position target : getAllPossibleMoves(piece)) {
                if (canMoveWithoutCheck(piece.getPosition(), target)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isInCheck(Piece.Color color) {
        Position kingPosition = board.findKingPosition(color);
        for (Piece opponentPiece : getAllPieces(getOpponentColor(color))) {
            if (opponentPiece.canMoveTo(kingPosition, board)) {
                return true;
            }
        }
        return false;
    }

    private boolean canMoveWithoutCheck(Position from, Position to) {
        board.move(from, to);
        boolean inCheck = isInCheck(board.findPiece(to).getColor());
        board.undoMove(from, to);
        return !inCheck;
    }

    private List<Piece> getAllPieces(Piece.Color color) {
        return board.getAllPieces(color);
    }

    private List<Position> getAllPossibleMoves(Piece piece) {
        return piece.getPossibleMoves(board);
    }

    public Piece.Color getOpponentColor(Piece.Color color) {
        return (color == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public Piece.Color getCurrentTurn() {
        return currentTurn;
    }
}
