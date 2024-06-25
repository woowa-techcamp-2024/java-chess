package chess.pieces;

public class Pawn {

    public static final String BLACK_COLOR = "black";
    public static final String WHITE_COLOR = "white";

    private String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = Pawn.WHITE_COLOR;
    }

    public String getColor() {
        return this.color;
    }


}