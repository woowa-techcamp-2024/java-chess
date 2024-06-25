package chess.pieces;

public class Piece implements ChessPiece {
    private final String color;
    private final char representation;
    private final String type;

    private Piece(PieceTypes values){
        this(values.getColor(),values.getRepresentation(),values.getType());
    }

    private Piece(String color, char representation,String type) {
        this.color = color;
        this.representation = representation;
        this.type = type;
    }

    public static Piece createPiece(PieceTypes piece) {
        return new Piece(piece);
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public String getType() {
        return type;
    }
}
