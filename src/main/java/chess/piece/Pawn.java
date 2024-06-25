package chess.piece;

public class Pawn {

    private final PieceColor color;

    public Pawn(final PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public Type getType() {
        return Type.PAWN;
    }
}
