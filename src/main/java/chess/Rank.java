package chess;

import java.util.List;
import pieces.Color;
import pieces.Piece;
import pieces.PieceType;

public record Rank(List<Piece> pieces) {

    public int totalPieceCount() {
        return (int) pieces.stream()
            .filter(piece -> !piece.isSameColor(Color.BLANK))
            .count();
    }

    public int getPieceCountByPieceType(PieceType pieceType) {
        return (int) pieces.stream()
            .filter(piece -> piece.getPieceType() == pieceType)
            .count();
    }

    public Piece getPiece(int column) {
        return pieces.get(column);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        pieces.forEach(piece -> stringBuilder.append(piece.getPieceType().getRepresentation(piece.getColor())));
        return stringBuilder.toString();
    }
}
