package pe.goblin.chess.domain.piece;

import pe.goblin.chess.domain.board.Direction;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Color color;
    protected final PieceType pieceType;

    protected Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
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

    abstract public List<Direction> getMovableDirections();

    abstract public int getMovableDistance();

    abstract public List<Direction> getAttackableDirections();

    public boolean isBlack() {
        return isColor(Color.BLACK);
    }

    public boolean isWhite() {
        return isColor(Color.WHITE);
    }

    public boolean isBlank() {
        return pieceType == PieceType.NO_PIECE;
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
