package pieces;

public enum PieceColor {
    WHITE("white"),
    BLACK("black"),
    NO_COLOR("no_color")
    ;

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
