package chess.pieces;

public enum PieceTypes {
    WHITE_PAWN("white","pawn",'p'),
    BLACK_PAWN("black","pawn",'P');

    private final String color;
    private final String type;
    private final char representation;

    PieceTypes(String color, String type, char representation) {
        this.color = color;
        this.type = type;
        this.representation = representation;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public char getRepresentation() {
        return representation;
    }
}
