package chess.piece;

import chess.util.Direction;

import java.util.List;

public class Knight extends Piece {
    public static final char WHITE_REPRESENTATION = '♘';
    public static final char BLACK_REPRESENTATION = '♞';
    public static final double DEFAULT_POINT = 2.5;

    private Knight(Color color, char representation) {
        super(color, representation, Direction.knightDirection(), 1);
    }

    public static Knight create(Color color) {
        return color == Color.WHITE ? createWhite() : createBlack();
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
