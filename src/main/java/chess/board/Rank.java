package chess.board;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private List<Piece> pieces = new ArrayList<>();

    public Rank(List<Piece> pieceList) {
        pieces.addAll(pieceList);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        pieces.forEach(piece -> builder.append(piece.getRepresentation()));
        return builder.toString();
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
