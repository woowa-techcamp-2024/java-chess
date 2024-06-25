package pieces;

public enum PieceColor {
    WHITE("WHITE"),
    BLACK("BLACK"),
    NO_COLOR("NO_COLOR")
    ;

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
