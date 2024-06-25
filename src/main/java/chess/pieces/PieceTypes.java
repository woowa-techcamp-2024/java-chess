package chess.pieces;

public enum PieceTypes {
    WHITE_PAWN(Color.WHITE,Type.PAWN),
    BLACK_PAWN(Color.BLACK,Type.PAWN),
    WHITE_KNIGHT(Color.WHITE,Type.KNIGHT),
    BLACK_KNIGHT(Color.BLACK,Type.KNIGHT),
    WHITE_ROOK(Color.WHITE,Type.ROOK),
    BLACK_ROOK(Color.BLACK,Type.ROOK),
    WHITE_BISHOP(Color.WHITE,Type.BISHOP),
    BLACK_BISHOP(Color.BLACK,Type.BISHOP),
    WHITE_QUEEN(Color.WHITE,Type.QUEEN),
    BLACK_QUEEN(Color.BLACK,Type.QUEEN),
    WHITE_KING(Color.WHITE,Type.KING),
    BLACK_KING(Color.BLACK,Type.KING),
    NO_PIECE(Color.NOCOLOR,Type.NO_PIECE);
    public enum Color{
        WHITE,BLACK,NOCOLOR;
    }
    public enum Type{
        PAWN('p'),ROOK('r'),KNIGHT('n'),BISHOP('b'),QUEEN('q'),KING('k'),NO_PIECE('.');
        private final char representation;
        Type(char representation){
            this.representation = representation;
        }

        public char getRepresentation(){
            return this.representation;
        }
    }


    private final Color color;
    private final Type type;
    private final char representation;

    PieceTypes(Color color, Type type) {
        this.color = color;
        this.type = type;
        char representation = this.type.getRepresentation();
        this.representation = color.equals(Color.WHITE) ? representation : Character.toUpperCase(representation);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        return representation;
    }
}
