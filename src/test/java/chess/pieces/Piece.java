package chess.pieces;

public class Piece {

    private final Type type;

    private final Color color;

    private final Representation representation;

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public enum Type { PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING, NO_PIECE }

    public enum Color { WHITE, BLACK }

    public enum Representation {
        WHITE_PAWN('♙'), BLACK_PAWN('♟'),
        WHITE_KNIGHT('♘'), BLACK_KNIGHT('♞'),
        WHITE_BISHOP('♗'), BLACK_BISHOP('♝'),
        WHITE_ROOK('♖'), BLACK_ROOK('♜'),
        WHITE_QUEEN('♕'), BLACK_QUEEN('♛'),
        WHITE_KING('♔'), BLACK_KING('♚');

        private final char symbol;

        Representation(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    private Piece(Type type, Color color, Representation representation) {
        this.type = type;
        this.color = color;
        this.representation = representation;
    }

    private static Piece create(Type type, Color color) {
        return switch (type) {
            case PAWN -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_PAWN : Representation.BLACK_PAWN);
            case KNIGHT -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_KNIGHT : Representation.BLACK_KNIGHT);
            case BISHOP -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_BISHOP : Representation.BLACK_BISHOP);
            case ROOK -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_ROOK : Representation.BLACK_ROOK);
            case QUEEN -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_QUEEN : Representation.BLACK_QUEEN);
            case KING -> new Piece(type, color, (color == Color.WHITE) ? Representation.WHITE_KING : Representation.BLACK_KING);
            case NO_PIECE -> new Piece(type, null, null);
        };
    }

    public static Piece createBlank() {
        return create(Type.NO_PIECE, null);
    }

    public static Piece createWhitePawn(){
        return create(Type.PAWN, Color.WHITE);
    }

    public static Piece createBlackPawn(){
        return create(Type.PAWN, Color.BLACK);
    }

    public static Piece createWhiteKnight(){
        return create(Type.KNIGHT, Color.WHITE);
    }

    public static Piece createBlackKnight(){
        return create(Type.KNIGHT, Color.BLACK);
    }

    public static Piece createWhiteBishop(){
        return create(Type.BISHOP, Color.WHITE);
    }

    public static Piece createBlackBishop(){
        return create(Type.BISHOP, Color.BLACK);
    }

    public static Piece createWhiteRook(){
        return create(Type.ROOK, Color.WHITE);
    }

    public static Piece createBlackRook(){
        return create(Type.ROOK, Color.BLACK);
    }

    public static Piece createWhiteQueen(){
        return create(Type.QUEEN, Color.WHITE);
    }

    public static Piece createBlackQueen(){
        return create(Type.QUEEN, Color.BLACK);
    }

    public static Piece createWhiteKing(){
        return create(Type.KING, Color.WHITE);
    }

    public static Piece createBlackKing(){
        return create(Type.KING, Color.BLACK);
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Representation getRepresentation() {
        return representation;
    }

}
