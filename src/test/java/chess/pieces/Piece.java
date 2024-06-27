package chess.pieces;

import chess.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece implements Comparable<Piece> {

    @Override
    public int compareTo(Piece o) {
        return (int) (this.getType().point - o.getType().point);
    }

    private final Type type;

    private final Color color;

    private final Representation representation;

    public abstract List<List<Position>> getPossibleMoves(Position currentPosition);

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

    public enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1),
        LEFT_UP(-1, -1), LEFT_DOWN(1, -1), RIGHT_UP(-1, 1), RIGHT_DOWN(1, 1),
        LEFT_LEFT_UP(-1, -2), LEFT_UP_UP(-2, -1), RIGHT_UP_UP(-2, 1), RIGHT_RIGHT_UP(-1, 2),
        LEFT_LEFT_DOWN(1, -2), LEFT_DOWN_DOWN(2, -1), RIGHT_DOWN_DOWN(2, 1), RIGHT_RIGHT_DOWN(1, 2);

        private final int dr, dc;
        Direction(int dr, int dc) { this.dr = dr; this.dc = dc; }
        public int getDr() { return dr; }
        public int getDc() { return dc; }
    }

    protected List<Position> getPossibleMovesOfDirection(Position curPos, Direction direction, int distance) {
        List<Position> possibleMoves = new ArrayList<>();
        Position nextPos = curPos;
        if (distance == 0){
            while (true){
                nextPos = new Position(nextPos.getRow() + direction.getDr(), nextPos.getCol() + direction.getDc());
                if (outOfBounds(nextPos)) break;
                possibleMoves.add(nextPos);
            }
            return possibleMoves;
        }
        for (int i=0; i<distance; i++){
            nextPos = new Position(nextPos.getRow() + direction.getDr(), nextPos.getCol() + direction.getDc());
            if (outOfBounds(nextPos)) break;
            possibleMoves.add(nextPos);
        }
        return possibleMoves;
    }

    private boolean outOfBounds(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        return row < 0 || row >= 8 || col < 0 || col >= 8;
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

    protected Piece(Type type, Color color, Representation representation) {
        this.type = type;
        this.color = color;
        this.representation = representation;
    }

    private static Piece create(Type type, Color color) {
        return switch (type) {
            case PAWN -> new Pawn(type, color, (color == Color.WHITE) ? Representation.WHITE_PAWN : Representation.BLACK_PAWN);
            case KNIGHT -> new Knight(type, color, (color == Color.WHITE) ? Representation.WHITE_KNIGHT : Representation.BLACK_KNIGHT);
            case BISHOP -> new Bishop(type, color, (color == Color.WHITE) ? Representation.WHITE_BISHOP : Representation.BLACK_BISHOP);
            case ROOK -> new Rook(type, color, (color == Color.WHITE) ? Representation.WHITE_ROOK : Representation.BLACK_ROOK);
            case QUEEN -> new Queen(type, color, (color == Color.WHITE) ? Representation.WHITE_QUEEN : Representation.BLACK_QUEEN);
            case KING -> new King(type, color, (color == Color.WHITE) ? Representation.WHITE_KING : Representation.BLACK_KING);
            case NO_PIECE -> new NoPiece(type, color, Representation.NO_PIECE);
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
