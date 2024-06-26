package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rank {
    private final List<Piece> rank;

    public Rank(final List<Piece> rank) { this.rank = rank; }

    public Piece get(int index) {
        return rank.get(index);
    }

    public int getCount() {
        return (int) rank.stream().filter(piece -> !Objects.equals(piece.getType(), Type.NO_PIECE)).count();
    }

    public String showRank() {
        StringBuilder stringBuilder = new StringBuilder();
        rank.forEach(piece -> {
            if (piece.isWhite()) stringBuilder.append(piece.getType().getWhiteRepresentation());
            else if (piece.isBlack()) stringBuilder.append(piece.getType().getBlackRepresentation());
            else stringBuilder.append(piece.getType().getRepresentation());
        });
        return stringBuilder.toString();
    }

    public Rank move(final int x, final Piece piece, final Position position) {
        List<Piece> newRank = new ArrayList<>(rank);
        newRank.set(x, Piece.createMovedPiece(piece, position));
        return new Rank(newRank);
    }
}
