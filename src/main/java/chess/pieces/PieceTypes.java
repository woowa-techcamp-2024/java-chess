package chess.pieces;

import java.util.List;

public enum PieceTypes {
    WHITE_PAWN(Color.WHITE, Type.PAWN, Direction.whitePawnDirection(), false),
    BLACK_PAWN(Color.BLACK, Type.PAWN, Direction.blackPawnDirection(), false),
    WHITE_KNIGHT(Color.WHITE, Type.KNIGHT, Direction.knightDirection(), false),
    BLACK_KNIGHT(Color.BLACK, Type.KNIGHT, Direction.knightDirection(), false),
    WHITE_ROOK(Color.WHITE, Type.ROOK, Direction.linearDirection(), true),
    BLACK_ROOK(Color.BLACK, Type.ROOK, Direction.linearDirection(), true),
    WHITE_BISHOP(Color.WHITE, Type.BISHOP, Direction.diagonalDirection(), true),
    BLACK_BISHOP(Color.BLACK, Type.BISHOP, Direction.diagonalDirection(), true),
    WHITE_QUEEN(Color.WHITE, Type.QUEEN, Direction.everyDirection(), true),
    BLACK_QUEEN(Color.BLACK, Type.QUEEN, Direction.everyDirection(), true),
    WHITE_KING(Color.WHITE, Type.KING, Direction.everyDirection(), false),
    BLACK_KING(Color.BLACK, Type.KING, Direction.everyDirection(), false),
    NO_PIECE(Color.NOCOLOR, Type.NO_PIECE, Direction.noDirection(), false);

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0), NO_PIECE('.', 0.0);
        private final char representation;
        private final double point;

        Type(char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getRepresentation() {
            return this.representation;
        }

        public double getPoint() {
            return this.point;
        }
    }


    private final Color color;
    private final Type type;
    private final char representation;
    private final Course course;

    PieceTypes(Color color, Type type, List<Direction> direction, boolean recursive) {
        this.color = color;
        this.type = type;
        char representation = type.getRepresentation();
        this.representation = color.equals(Color.WHITE) ? representation : Character.toUpperCase(representation);
        this.course = new Course(direction, recursive);
    }

    public Color getColor() {
        return color;
    }

    public PieceTypes fromRepresentation(char representation) {
        for (PieceTypes pieceTypes : values()) {
            if (pieceTypes.getRepresentation() == representation) {
                return pieceTypes;
            }
        }
        return null;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        return representation;
    }

    public Course getCourse() {
        return course;
    }
}
