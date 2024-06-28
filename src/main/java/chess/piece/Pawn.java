package chess.piece;

import chess.util.MoveRule;

import java.util.*;

public class Pawn extends Piece {
    public static final char WHITE_REPRESENTATION = '♙';
    public static final char BLACK_REPRESENTATION = '♟';
    public static final double DEFAULT_POINT = 1;

    private Pawn(Color color, char representation) {
        super(color, representation, List.of(), 0, List.of(MoveRule.PawnMove));
    }

    public static Pawn create(Color color) {
        return color == Color.WHITE ? createWhite() : createBlack();
    }

    public static Pawn createWhite() {
        return new Pawn(Color.WHITE, WHITE_REPRESENTATION);
    }

    public static Pawn createBlack() {
        return new Pawn(Color.BLACK, BLACK_REPRESENTATION);
    }

    @Override
    public double getDefaultPoint() {
        return DEFAULT_POINT;
    }
}
