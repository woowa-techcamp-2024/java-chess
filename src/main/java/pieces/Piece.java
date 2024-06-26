package pieces;

import chess.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected final Color color;
    protected PieceType pieceType;
    protected Position position;

    abstract public void move(String pos);

    abstract List<Position> getAccessiblePosition();

    public static Piece of(Color color, PieceType pieceType, Position position) {
        return switch (pieceType) {
            case PAWN -> new Pawn(color, pieceType, position);
            case KNIGHT -> new Knight(color, pieceType, position);
            case ROOK -> new Rook(color, pieceType, position);
            case BISHOP -> new Bishop(color, pieceType, position);
            case QUEEN -> new Queen(color, pieceType, position);
            case KING -> new King(color, pieceType, position);
            default -> new Blank(color, pieceType, position);
        };
    }

    public Piece(Color color, PieceType pieceType, Position position) {
        this.color = color;
        this.pieceType = pieceType;
        this.position = position;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSamePieceType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Position getPosition() {
        return position;
    }

    public void updatePosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && pieceType == piece.pieceType && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType, position);
    }
}