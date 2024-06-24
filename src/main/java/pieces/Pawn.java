package pieces;

public class Pawn implements Piece {

    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
