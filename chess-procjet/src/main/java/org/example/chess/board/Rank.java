package org.example.chess.board;

import static org.example.chess.pieces.Piece.*;

import java.util.ArrayList;
import java.util.List;
import org.example.chess.pieces.Piece;

public class Rank {

    private final List<Piece> pieces = new ArrayList<>();

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void changePiece(int col, Piece piece) {
        pieces.set(col, piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public int countWhitePiecesWithType(Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.isWhite() && piece.getType() == type)
                .count();
    }

    public int countBlackPiecesWithType(Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.isBlack() && piece.getType() == type)
                .count();
    }

    public double calculateRankPoint(Color color) {
        double points = 0.0;
        for (Piece piece : pieces) {
            if (piece.getColor() == color) {
                points += piece.getType().getDefaultPoint();
            }
        }
        return points;
    }
}
