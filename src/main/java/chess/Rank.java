package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Piece> pieces;
    private int pieceCount;

    private static final int BOARD_WIDTH = 8;

    private Rank(List<Piece> pieces) {
        validatePieceCount(pieces);
        for (Piece piece : pieces) {
            if (!piece.isBlank()) {
                pieceCount++;
            }
        }
        this.pieces = new ArrayList<>(pieces);
    }

    public int getTotalPieceCount() {
        return pieceCount;
    }

    private void decreaseRankCount() {
        pieceCount--;
    }

    public static Rank initializeRank(List<Piece> pieces) {
        return new Rank(pieces);
    }

    private void validatePieceCount(List<Piece> pieces) {
        if (pieces.size() != BOARD_WIDTH) {
            throw new IllegalArgumentException("한 랭크는 8개의 말을 가집니다.");
        }
    }

    public String printRank() {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
