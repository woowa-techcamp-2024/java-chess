package chess.piece;

import chess.util.Direction;

public class Bishop extends Piece {
    public static final char WHITE_REPRESENTATION = '♗';
    public static final char BLACK_REPRESENTATION = '♝';
    public static final double DEFAULT_POINT = 3;

    private Bishop(Color color, char representation) {
        super(color, representation, Direction.diagonalDirection(), MAX_MOVE_DISTANCE);
    }

    public static Bishop create(Color color) {
        return color == Color.WHITE ? createWhite() : createBlack();
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
