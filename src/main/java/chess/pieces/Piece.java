package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;
import chess.pieces.type.Representation;
import chess.pieces.type.Type;

import java.util.Objects;

public class Piece {
    private final Representation representation;

    private Piece(Representation representation) {
        this.representation = representation;
    }

    public static Piece create(Type type, Color color) {
        return new Piece(Representation.from(type, color));
    }

//    // King이라고 일단 가정
//    public boolean canMove(Piece target) {
//        Position targetPosition = target.position;
//
//        int ty = Math.abs(this.position.getFile() - targetPosition.getFile());
//        int tx = Math.abs(this.position.getRank() - targetPosition.getRank());
//
//        return ty <= 1 && tx <= 1;
//    }

    public String getName() {
        return representation.name();
    }

    public Color getColor() {
        return representation.getColor();
    }

    public String getSymbol() {
        return representation.getSymbol();
    }

    public Type getType() {
        return representation.getType();
    }

    public boolean isPieceOf(Representation representation) {
        return this.representation == representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;

        return representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(representation);
    }

    @Override
    public String toString() {
        return "Piece " + hashCode() + " " +
                "representation=" + representation;
    }
}
