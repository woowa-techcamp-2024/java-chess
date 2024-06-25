package chess;

import chess.pieces.Blank;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import chess.pieces.values.Location;

import java.util.Arrays;

public class Board {

    private static final int BOARD_SIZE = 8;

    private final Piece[][] board;

    public Board() {
        board = new Piece[BOARD_SIZE][];
        for (int i = 0; i < board.length; i++) {
            board[i] = fillBlank();
        }
    }

    public void initialize() {
        fillPawn(2, Color.WHITE);
        fillPawn(7, Color.BLACK);
    }

    public void addPiece(Piece piece, Location location) {
        board[location.getX() - 1][location.getY()] = piece;
        piece.moveLocation(location);
    }

    public Piece getPiece(Location location) {
        return board[location.getX() - 1][location.getY()];
    }

    public int size() {
        var result = 0;
        for (Piece[] pieces : board) {
            result += (int) Arrays.stream(pieces).filter(piece -> !(piece instanceof Blank)).count();
        }
        return result;
    }

    public String printRow(int row) {
        var sb = new StringBuilder();
        for (Piece piece : board[row - 1]) {
            sb.append(piece);
        }
        return sb.toString();
    }

    public String print() {
        var sb = new StringBuilder();
        for (int i = board.length; i > 0; i--) {
            sb.append(printRow(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    private void fillPawn(int row, Color color) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            var pawn = new Pawn(color);
            board[row - 1][i] = pawn;
            pawn.moveLocation(Location.of(row, (char) ('a' + i)));
        }
    }

    private static Piece[] fillBlank() {
        var pieces = new Piece[8];
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Blank();
        }
        return pieces;
    }

}
