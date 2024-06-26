package chess;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {

    private final List<Piece> pieces;
    private int pieceCount;

    private static final int BOARD_WIDTH = 8;

    public int getTotalPieceCount() {
        return pieceCount;
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.isSameColorAndType(color, type))
                .count();
    }

    public static Rank initializeRank(List<Piece> pieces) {
        return new Rank(pieces);
    }

    public String printRank() {
        return pieces.stream()
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public Piece getPieceByIndex(int index) {
        return pieces.get(index);
    }

    public double calculateRankPoint(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    protected void setPiece(int widthIndex, Piece piece) {
        pieces.set(widthIndex, piece);
        pieceCount = calculatePieceCount();
    }

    private Rank(List<Piece> pieces) {
        validatePieceCount(pieces);
        for (Piece piece : pieces) {
            if (!piece.isBlank()) {
                pieceCount++;
            }
        }
        this.pieces = new ArrayList<>(pieces);
    }

    private int calculatePieceCount() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }


    private void validatePieceCount(List<Piece> pieces) {
        if (pieces.size() != BOARD_WIDTH) {
            throw new IllegalArgumentException("한 랭크는 8개의 말을 가집니다.");
        }
    }

    public List<Piece> getAllPieces() {
        return pieces.stream()
                .filter(piece -> !piece.isBlank())
                .toList();
    }
}
