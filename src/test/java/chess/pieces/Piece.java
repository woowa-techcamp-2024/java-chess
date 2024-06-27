package chess.pieces;

import java.util.Objects;

public class Piece implements Comparable<Piece> {

    @Override
    public int compareTo(Piece o) {
        return (int) (this.getType().point - o.getType().point);
    }

    private final Type type;

    private final Color color;

    private final Representation representation;

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public enum Type { PAWN(1.0), KNIGHT(2.5), BISHOP(3.0), ROOK(5.0), QUEEN(9.0), KING(0.0), NO_PIECE(0.0);
        private final double point;
        Type(double point) { this.point = point; }
        public double getPoint() { return point; }
    }



    public enum Color { WHITE, BLACK, NO_COLOR }

    public enum Representation {
        WHITE_PAWN('♙'), BLACK_PAWN('♟'),
        WHITE_KNIGHT('♘'), BLACK_KNIGHT('♞'),
        WHITE_BISHOP('♗'), BLACK_BISHOP('♝'),
        WHITE_ROOK('♖'), BLACK_ROOK('♜'),
        WHITE_QUEEN('♕'), BLACK_QUEEN('♛'),
        WHITE_KING('♔'), BLACK_KING('♚'),
        NO_PIECE('.');

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
            case NO_PIECE -> new Piece(type, color, Representation.NO_PIECE);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return representation == piece.representation;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(representation);
    }

    public static Piece createBlank() {
        return create(Type.NO_PIECE, Color.NO_COLOR);
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
