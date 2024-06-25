package chess.pieces;

public enum PieceTypes {
    WHITE_PAWN("white","pawn",'p'),
    BLACK_PAWN("black","pawn",'P'),
    WHITE_KNIGHT("white","knight",'n'),
    BLACK_KNIGHT("black","knight",'N'),
    WHITE_ROOK("white","rook",'r'),
    BLACK_ROOK("black","rook",'R'),
    WHITE_BISHOP("white","bishop",'b'),
    BLACK_BISHOP("black","bishop",'B'),
    WHITE_QUEEN("white","queen",'q'),
    BLACK_QUEEN("black","queen",'Q'),
    WHITE_KING("white","king",'k'),
    BLACK_KING("black","king",'K'),
    ;

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
