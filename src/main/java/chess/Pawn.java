package chess;

public class Pawn {
    private final String color;

    public Pawn() { this.color = Color.WHITE; }

    public Pawn(final String color) {
        this.color = Color.getColor(color);
    }

    public String getColor() {
        return this.color;
    }
}