package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private final List<Piece> pieces = new ArrayList<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public long count() {
        return pieces.stream()
                .filter(p -> p.getColor().hasColor())
                .count();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Piece p : pieces) {
            sb.append(p.getSymbol());
        }
        return sb.toString();
    }
}
