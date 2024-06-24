package chess;

public enum PawnColor {
    WHITE("white"),
    BLACK("black"),
    ;

    private final String color;

    PawnColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
