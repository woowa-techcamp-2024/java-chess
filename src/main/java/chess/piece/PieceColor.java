package chess.piece;

public enum PieceColor {
    WHITE("white"), BLACK("black");

    public final String color;

    PieceColor(final String color) {
        this.color = color;
    }
}
