package chess.board;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;
import chess.pieces.Piece.Color;

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

    public int countPiece(Color color, Type type) {
        return (int) pieces.stream().filter(piece -> piece.getColor() == color && piece.getType() == type).count();
    }
}
