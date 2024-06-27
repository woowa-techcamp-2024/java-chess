package chess.piece;

import chess.Board;
import chess.util.ChessPoint;
import chess.util.Direction;
import chess.util.MoveRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    public static final char WHITE_REPRESENTATION = '♖';
    public static final char BLACK_REPRESENTATION = '♜';
    public static final double DEFAULT_POINT = 5;

    private Rook(Color color, char representation) {
        super(color, representation, Direction.linearDirection(), MAX_MOVE_DISTANCE);
    }

    public static Rook create(Color color) {
        return color == Color.WHITE ? createWhite() : createBlack();
    }

    public static Rook createWhite() {
        return new Rook(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Rook createBlack() {
        return new Rook(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
