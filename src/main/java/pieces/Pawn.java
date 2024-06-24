package pieces;

public class Pawn implements Piece {

    private final String color;

    public Pawn(String color) {
        this.color = verifyColor(color);
    }

    private static String verifyColor(String color) {
        if (!(color.equals("white") || color.equals("black"))) {
            throw new IllegalArgumentException("Invalid Color Input");
        }
        return color;
    }

    public String getColor() {
        return color;
    }

    public boolean verifyPawn(final String color) {
        return this.color.equals(color);
    }
}
