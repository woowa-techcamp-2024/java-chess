package com.woowatechcamp.chess.game;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.Position;

public class ChessGame {
    private final Board board;
    private Piece.Color currentTurn;
    private boolean isGameFinished;
    private Piece.Color winner;

    public ChessGame() {
        board = new Board();
        board.initialize();
        board.print();
        currentTurn = Piece.Color.WHITE;
        isGameFinished = false;
        winner = null;
    }

    public void move(Position from, Position to) {
        if (isGameFinished) {
            throw new IllegalStateException("The game is already finished.");
        }

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
            isGameFinished = true;
            winner = currentTurn;
        } else if (isInCheck(opponentColor)) {
            System.out.println(opponentColor + " is in check!");
        } else {
            System.out.println("nothing");
        }

        if (!isGameFinished) {
            switchTurn();
        }
    }

    private void printScore() {
        System.out.println("White Score: " + board.calculatePoint(Piece.Color.WHITE));
        System.out.println("Black Score: " + board.calculatePoint(Piece.Color.BLACK));
    }

    public boolean isFinish() {
        return isGameFinished || !board.isKingAlive(Piece.Color.WHITE) || !board.isKingAlive(Piece.Color.BLACK);
    }

    public Piece.Color getWinner() {
        return winner;
    }

    private boolean isStalemate(Piece.Color color) {
        if (isInCheck(color)) {
            return false;
        }
        for (Piece piece : board.getAllPieces(color)) {
            for (Position target : piece.getPossibleMoves(board)) {
                if (testMove(piece.getPosition(), target, color)) {
                    return false;
                }
            }
        }
        return true;
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
        for (Piece piece : board.getAllPieces(color)) {
            for (Position target : piece.getPossibleMoves(board)) {
                if (testMove(piece.getPosition(), target, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isInCheck(Piece.Color color) {
        Position kingPosition = board.findKingPosition(color);
        Piece.Color opponentColor = getOpponentColor(color);
        for (Piece opponentPiece : board.getAllPieces(opponentColor)) {
            if (opponentPiece.canMoveTo(kingPosition, board)) {
                return true;
            }
        }
        return false;
    }

    public boolean testMove(Position from, Position to, Piece.Color color) {
        Piece capturedPiece = board.moveTemporarily(from, to);
        boolean validMove = !isInCheck(color);
        board.undoTemporaryMove(from, to, capturedPiece);
        return validMove;
    }

    public Piece.Color getOpponentColor(Piece.Color color) {
        return (color == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    }

    public Piece.Color getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }
}
