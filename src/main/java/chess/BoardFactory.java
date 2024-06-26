package chess;

import chess.piece.*;

public class BoardFactory {

    private static final int boardSize = 8;
    private static final int rowNum = 9;
    private static final int whiteInitRowWithoutPawn = rowNum - 8;
    private static final int blackInitRowWithoutPawn = rowNum - 1;

    private BoardFactory() {
    }

    public static void createPawn(final Board board) {
        for (int col = 1; col <= boardSize; col++) {
            board.add(Position.of(rowNum - 2, col), Pawn.create(PieceColor.BLACK));
            board.add(Position.of(rowNum - 7, col), Pawn.create(PieceColor.WHITE));
        }
    }

    public static void createBishop(final Board board) {
        for (int col = 3; col <= boardSize; col += 3) {
            board.add(Position.of(blackInitRowWithoutPawn, col), Bishop.create(PieceColor.BLACK));
            board.add(Position.of(whiteInitRowWithoutPawn, col), Bishop.create(PieceColor.WHITE));
        }
    }

    public static void createQueen(final Board board) {
        int queenCol = 4;

        board.add(Position.of(blackInitRowWithoutPawn, queenCol), Queen.create(PieceColor.BLACK));
        board.add(Position.of(whiteInitRowWithoutPawn, queenCol), Queen.create(PieceColor.WHITE));
    }

    public static void createKing(final Board board) {
        int kingCol = 5;

        board.add(Position.of(blackInitRowWithoutPawn, kingCol), King.create(PieceColor.BLACK));
        board.add(Position.of(whiteInitRowWithoutPawn, kingCol), King.create(PieceColor.WHITE));
    }

    public static void createKnight(final Board board) {
        for (int col = 2; col <= boardSize; col += 5) {
            board.add(Position.of(blackInitRowWithoutPawn, col), Knight.create(PieceColor.BLACK));
            board.add(Position.of(whiteInitRowWithoutPawn, col), Knight.create(PieceColor.WHITE));
        }
    }

    public static void createRook(final Board board) {
        for (int col = 1; col <= boardSize; col += 7) {
            board.add(Position.of(blackInitRowWithoutPawn, col), Rook.create(PieceColor.BLACK));
            board.add(Position.of(whiteInitRowWithoutPawn, col), Rook.create(PieceColor.WHITE));
        }
    }
}
