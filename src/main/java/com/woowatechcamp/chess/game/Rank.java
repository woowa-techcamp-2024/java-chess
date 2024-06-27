package com.woowatechcamp.chess.game;

import com.woowatechcamp.chess.pieces.Piece;
import com.woowatechcamp.chess.pieces.PieceFactory;
import com.woowatechcamp.chess.pieces.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.woowatechcamp.utils.StringUtils.appendNewLine;

public class Rank {
    private static final int ROW_LENGTH = 8;
    private final List<Piece> pieces;

    public Rank(Piece... pieces) {
        validatePieces(pieces);
        this.pieces = Arrays.asList(pieces);
    }

    private void validatePieces(Piece[] pieces) {
        if (pieces.length != ROW_LENGTH) {
            throw new IllegalArgumentException("Invalid pieces length");
        }
    }

    public static Rank createBlankRank(int yPos) {
        return new Rank(
                PieceFactory.createBlank(new Position("a" + yPos)), PieceFactory.createBlank(new Position("b" + yPos)),
                PieceFactory.createBlank(new Position("c" + yPos)), PieceFactory.createBlank(new Position("d" + yPos)),
                PieceFactory.createBlank(new Position("e" + yPos)), PieceFactory.createBlank(new Position("f" + yPos)),
                PieceFactory.createBlank(new Position("g" + yPos)), PieceFactory.createBlank(new Position("h" + yPos))
        );
    }

    public int getPieceCount() {
        return (int) pieces.stream()
                .filter(Piece::isNotBlank)
                .count();
    }

    public int getPieceCount(Piece.Type type, Piece.Color color) {
        return (int) pieces.stream()
                .filter(piece -> piece.isSameTypeAndColor(type, color))
                .count();
    }

    public void setPiece(Piece piece) {
        pieces.set(piece.getPosition().getXPos(), piece);
    }

    public Piece getPiece(int xPos) {
        return pieces.get(xPos);
    }

    public boolean isSameColorPawn(Piece.Color color, int xPos) {
        return pieces.get(xPos).isSameTypeAndColor(Piece.Type.PAWN, color);
    }

    public double calculatePoint(Piece.Color color) {
        return pieces.stream()
                .filter(piece -> piece.getColor() == color)
                .mapToDouble(piece -> piece.getType().getPoint())
                .sum();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(pieces);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Piece piece : pieces) {
            result.append(piece.toString());
        }
        return appendNewLine(result.toString());
    }
}
