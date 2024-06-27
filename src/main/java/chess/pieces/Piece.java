package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Representation;
import chess.pieces.type.Type;

import java.util.Objects;

public class Piece {
    private final Color color;
    private final Type type;
    private final Position position;

    private Piece(Color color, Type type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public static Piece create(Type type, Color color, Position position) {
        return new Piece(color, type, position);
    }

    public Piece copyWithPosition(Position position) {
        return new Piece(this.color, this.type, position);
    }


    public Color getColor() {
        return color;
    }

    public String getSymbol() {
        return Representation.from(type, color).getSymbol();
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isPieceOf(Representation representation) {
        return isPieceOf(representation.getType(), representation.getColor());
    }

    public boolean isPieceOf(Type type, Color color) {
        return this.type == type && this.color == color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
