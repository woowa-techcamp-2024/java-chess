package chess.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rank {
    private List<Piece> rank = new ArrayList<>();

    public Rank(final List<Piece> rank) { this.rank = rank; }

    public int getCount() {
        return (int) rank.stream().filter(piece -> !Objects.equals(piece.getType(), Type.NO_PIECE)).count();
    }

    public int getCount(final Color color, final Type type) {
        return (int) rank.stream().filter(
                piece -> Objects.equals(color, piece.getColor()) && Objects.equals(type, piece.getType())).count();
    }

    public Piece getPiece(int index) {
        return rank.get(index);
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
}
