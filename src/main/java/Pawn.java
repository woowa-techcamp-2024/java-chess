public class Pawn {

    private static final String BLACK = "black";
    private static final String WHITE = "white";

    private String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = Pawn.WHITE;
    }

    public String getColor() {
        return this.color;
    }


}