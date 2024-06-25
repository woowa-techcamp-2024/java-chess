package chess;

import static chess.Board.BOARD_SIZE;
import static pieces.Piece.Color.BLACK;
import static pieces.Piece.Color.WHITE;
import static pieces.Piece.PieceType.BISHOP;
import static pieces.Piece.PieceType.BLANK;
import static pieces.Piece.PieceType.KING;
import static pieces.Piece.PieceType.KNIGHT;
import static pieces.Piece.PieceType.PAWN;
import static pieces.Piece.PieceType.QUEEN;
import static pieces.Piece.PieceType.ROOK;

import pieces.Piece;
import pieces.Piece.Color;

public class Position {

    public static final int POSITION_INPUT_SIZE = 2;
    public static final int MINIMUM_POSITION_BOUND = 0;
    public static final int MAXIMUM_POSITION_BOUND = BOARD_SIZE - 1;

    private final int row;
    private final int column;

    private Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Position of(String position) {
        int row = BOARD_SIZE - Character.getNumericValue(position.charAt(1));
        int col = position.charAt(0) - 'a';
        validatePosition(position, row, col);
        return new Position(row, col);
    }

    public static Piece getPieceByDefaultPosition(int row, int column) {
        if (row == 1) {
            return Piece.createPiece(BLACK, PAWN);
        }
        if (row == 0 && (column == 0 || column == 7)) {
            return Piece.createPiece(BLACK, ROOK);
        }
        if (row == 0 && (column == 1 || column == 6)) {
            return Piece.createPiece(BLACK, KNIGHT);
        }
        if (row == 0 && (column == 2 || column == 5)) {
            return Piece.createPiece(BLACK, BISHOP);
        }
        if (row == 0 && column == 3) {
            return Piece.createPiece(BLACK, QUEEN);
        }
        if (row == 0 && column == 4) {
            return Piece.createPiece(BLACK, KING);
        }
        if (row == 6) {
            return Piece.createPiece(WHITE, PAWN);
        }
        if (row == 7 && (column == 0 || column == 7)) {
            return Piece.createPiece(WHITE, ROOK);
        }
        if (row == 7 && (column == 1 || column == 6)) {
            return Piece.createPiece(WHITE, KNIGHT);
        }
        if (row == 7 && (column == 2 || column == 5)) {
            return Piece.createPiece(WHITE, BISHOP);
        }
        if (row == 7 && column == 3) {
            return Piece.createPiece(WHITE, QUEEN);
        }
        if (row == 7 && column == 4) {
            return Piece.createPiece(WHITE, KING);
        }
        return Piece.createPiece(Color.BLANK, BLANK);
    }

    private static void validatePosition(String position, int row, int col) {
        if (position.length() != POSITION_INPUT_SIZE || isInvalidRange(row) || isInvalidRange(col)) {
            throw new IllegalArgumentException("invalid position input, position: " + position);
        }
    }

    private static boolean isInvalidRange(int row) {
        return row < MINIMUM_POSITION_BOUND || row > MAXIMUM_POSITION_BOUND;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
