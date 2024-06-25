package chess.pieces;

public class Piece {

    private final Name name;

    private final Color color;

    private final Representation representation;

    public enum Name { PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING }

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

    private Piece(Name name, Color color, Representation representation) {
        this.name = name;
        this.color = color;
        this.representation = representation;
    }

    private static Piece create(Name name, Color color) {
        return switch (name) {
            case PAWN -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_PAWN : Representation.BLACK_PAWN);
            case KNIGHT -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_KNIGHT : Representation.BLACK_KNIGHT);
            case BISHOP -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_BISHOP : Representation.BLACK_BISHOP);
            case ROOK -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_ROOK : Representation.BLACK_ROOK);
            case QUEEN -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_QUEEN : Representation.BLACK_QUEEN);
            case KING -> new Piece(name, color, (color == Color.WHITE) ? Representation.WHITE_KING : Representation.BLACK_KING);
            default -> throw new IllegalArgumentException("Unknown chess piece");
        };
    }

    public static Piece createWhitePawn(){
        return create(Name.PAWN, Color.WHITE);
    }

    public static Piece createBlackPawn(){
        return create(Name.PAWN, Color.BLACK);
    }

    public static Piece createWhiteKnight(){
        return create(Name.KNIGHT, Color.WHITE);
    }

    public static Piece createBlackKnight(){
        return create(Name.KNIGHT, Color.BLACK);
    }

    public static Piece createWhiteBishop(){
        return create(Name.BISHOP, Color.WHITE);
    }

    public static Piece createBlackBishop(){
        return create(Name.BISHOP, Color.BLACK);
    }

    public static Piece createWhiteRook(){
        return create(Name.ROOK, Color.WHITE);
    }

    public static Piece createBlackRook(){
        return create(Name.ROOK, Color.BLACK);
    }

    public static Piece createWhiteQueen(){
        return create(Name.QUEEN, Color.WHITE);
    }

    public static Piece createBlackQueen(){
        return create(Name.QUEEN, Color.BLACK);
    }

    public static Piece createWhiteKing(){
        return create(Name.KING, Color.WHITE);
    }

    public static Piece createBlackKing(){
        return create(Name.KING, Color.BLACK);
    }

    public Color getColor() {
        return color;
    }

    public Name getName() {
        return name;
    }

    public Representation getRepresentation() {
        return representation;
    }

}
