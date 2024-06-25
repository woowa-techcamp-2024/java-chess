package pieces;

public enum PieceColor {
    WHITE("white"),
    BLACK("black"),
    ;

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
