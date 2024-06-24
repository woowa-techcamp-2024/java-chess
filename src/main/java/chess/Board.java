package chess;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Piece> pieces;

    public Board() {
        pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Pawn findPawn(int idx) {
        var piece = pieces.get(idx);
        if (!(piece instanceof Pawn)) {
            throw new IllegalArgumentException("Piece is not a Pawn");
        }
        return (Pawn) piece;
    }
}
