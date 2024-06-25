package chess;

import chess.pieces.Piece;
import chess.pieces.values.Location;

import java.util.*;

public class Board {

    private static final char[] ROWS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    private final Map<Character, Piece[]> board;

    public Board() {
        board = new HashMap<>();
        for (char row : ROWS) {
            board.put(row, new Piece[8]);
        }
    }

    public void addPiece(Piece piece, Location location) {
        var row = board.get(location.getX());
        row[location.getY() - 1] = piece;
        piece.moveLocation(location);
    }

    public Optional<Piece> getPiece(Location location) {
        var row = board.get(location.getX());
        return Optional.ofNullable(row[location.getY() - 1]);
    }

    public int size() {
        var result = 0;
        for (Piece[] pieces : board.values()) {
            result += (int) Arrays.stream(pieces).filter(Objects::nonNull).count();
        }
        return result;
    }

}
