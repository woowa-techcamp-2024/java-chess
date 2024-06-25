package wootecamp.chess;

public class Pawn {
    private String color;

    public Pawn() {
        this.color = "white";
    }

    public Pawn(final String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
