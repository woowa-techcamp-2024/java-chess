public class Pawn {

    private final String color;

    public Pawn(String white) {
        this.color = white;
    }

    public String getColor() {
        return color;
    }

    public boolean verifyPawn(final String color) {
        return color.equals(this.color);
    }
}
