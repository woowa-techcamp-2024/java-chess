package chess.pieces;

import static chess.pieces.PieceTypes.*;

public class Piece implements ChessPiece {
    private final Color color;
    private final char representation;
    private final Type type;

    private Piece(PieceTypes values){
        this(values.getColor(),values.getRepresentation(),values.getType());
    }

    private Piece(Color color, char representation, Type type) {
        this.color = color;
        this.representation = representation;
        this.type = type;
    }

    public static Piece createPiece(PieceTypes piece) {
        return new Piece(piece);
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public Type getType() {
        return type;
    }

    public boolean isBlack(){
        return Color.BLACK.equals(color);
    }

    public boolean isWhite(){
        return Color.WHITE.equals(color);
    }
}
