package chess;

public class Pawn {
    String color;

    public String getColor() {
        return color;
    }

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = "white";
    }
}
