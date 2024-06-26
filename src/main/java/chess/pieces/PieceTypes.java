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
        PAWN('p',1.0),
        ROOK('r',5.0),
        KNIGHT('n',2.5),
        BISHOP('b',3.0),
        QUEEN('q',9.0),
        KING('k',0.0)
        ,NO_PIECE('.',0.0);
        private final char representation;
        private final double point;
        Type(char representation,double point){
            this.representation = representation;
            this.point = point;
        }

        public char getRepresentation(){
            return this.representation;
        }
        public double getPoint(){
            return this.point;
        }
    }


    private final Color color;
    private final Type type;
    private final char representation;
    private final double point;

    PieceTypes(Color color, Type type) {
        this.color = color;
        this.type = type;
        char representation = type.getRepresentation();
        this.representation = color.equals(Color.WHITE) ? representation : Character.toUpperCase(representation);
        this.point = type.getPoint();
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

    public double getPoint(){
        return point;
    }
}
