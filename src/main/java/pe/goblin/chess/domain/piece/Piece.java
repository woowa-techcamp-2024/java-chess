package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.Objects;

public class Piece {
    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public static Piece of(char ch) {
        return new Piece(Character.isUpperCase(ch) ? Color.BLACK : Color.WHITE, PieceType.of(ch));
    }

    public static Piece createBlank() {
        return new Piece(Color.NOCOLOR, PieceType.NO_PIECE);
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getType() {
        return this.pieceType;
    }

    public char getRepresentation() {
        return this.color == Color.BLACK ? pieceType.getBlackRepresentation() : pieceType.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return isColor(Color.BLACK);
    }

    public boolean isWhite() {
        return isColor(Color.WHITE);
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }
}
