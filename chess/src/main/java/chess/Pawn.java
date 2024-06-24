package chess;

public class Pawn {
    private final PawnColor color;

    public Pawn(PawnColor color) {
        this.color = color;
    }

    public Pawn() {
        this.color = PawnColor.WHITE;
    }

    public PawnColor getColor() {
        return color;
    }
}
