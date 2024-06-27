package chess.board;

import chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final List<Piece> pieces;

    private static final int BOARD_WIDTH = 8;

    public List<Piece> getPieces() {
        return pieces;
    }

    public int getPieceCount(Piece.Color color, Piece.Type type) {
        return (int) pieces.stream()
                .filter(piece -> piece.isSameColorAndType(color, type))
                .count();
    }

    public static Rank initializeRank(List<? extends Piece> pieces) {
        return new Rank(pieces);
    }

    public Piece getPieceByIndex(int index) {
        return pieces.get(index);
    }

    public List<Piece> getAllPieces(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> !piece.isBlank())
                .filter(piece -> piece.getColor().equals(color))
                .toList();
    }

    protected void setPiece(int widthIndex, Piece piece) {
        pieces.set(widthIndex, piece);
    }

    private Rank(List<? extends Piece> pieces) {
        validatePieceCount(pieces);
        this.pieces = new ArrayList<>(pieces);
    }

    private int calculatePieceCount() {
        return (int) pieces.stream()
                .filter(piece -> !piece.isBlank())
                .count();
    }


    private void validatePieceCount(List<? extends Piece> pieces) {
        if (pieces.size() != BOARD_WIDTH) {
            throw new IllegalArgumentException("한 랭크는 8개의 말을 가집니다.");
        }
    }
}
