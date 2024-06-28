package java_chess.chess.pieces.enums;

public enum Color {
    BLACK, WHITE, NONE;

    public Color oppositeColor() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> NONE;
        };
    }
}
