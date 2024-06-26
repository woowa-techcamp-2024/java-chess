package pieces;

import chess.Position;

import java.util.Objects;

public abstract class Piece{
    private final PieceColor color;
    private final PieceType type;
    private Position position;

    public Piece(PieceColor color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public boolean isBlack()
    {
        return color.equals(PieceColor.BLACK);
    }
    public boolean isWhite()
    {
        return color.equals(PieceColor.WHITE);
    }

    public String getRepresent(){
        return PieceDescriptor.getUnicode(color, type).getUnicode();
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position){
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                ", position=" + position +
                '}';
    }
}
