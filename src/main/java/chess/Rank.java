package chess;

import chess.pieces.Piece;
import chess.pieces.Representations;

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

    public long count(Representations representations) {
        return pieces.stream()
                .filter(p -> p.isPieceOf(representations))
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
