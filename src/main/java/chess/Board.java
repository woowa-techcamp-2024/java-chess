package chess;

import chess.pieces.Blank;
import chess.pieces.Piece;
import chess.pieces.values.Location;

import java.util.Arrays;

public class Board {

    private final Piece[][] board;

    public Board() {
        board = new Piece[8][];
        for (int i = 0; i < board.length; i++) {
            board[i] = fillBlank();
        }
    }

    public void initialize() {

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

    private static Piece[] fillBlank() {
        var pieces = new Piece[8];
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Blank();
        }
        return pieces;
    }

}
