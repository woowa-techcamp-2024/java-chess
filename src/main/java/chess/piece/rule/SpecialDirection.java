package chess.piece.rule;

import java.util.List;

public enum SpecialDirection implements Moveable {
    KNIGHT_NNE(1, 2),
    KNIGHT_NNW(-1, 2),
    KNIGHT_SSE(1, -2),
    KNIGHT_SSW(-1, -2),
    KNIGHT_EEN(2, 1),
    KNIGHT_EES(2, -1),
    KNIGHT_WWN(-2, 1),
    KNIGHT_WWS(-2, -1),
    PAWN(0, 1);

    private final int xDegree;
    private final int yDegree;

    SpecialDirection(final int xDegree, final int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    @Override
    public int getXDegree() {
        return xDegree;
    }

    @Override
    public int getYDegree() {
        return yDegree;
    }

    public static List<Moveable> getKnightDirection() {
        return List.of(KNIGHT_NNE, KNIGHT_NNW, KNIGHT_SSE, KNIGHT_SSW, KNIGHT_EEN, KNIGHT_EES, KNIGHT_WWN, KNIGHT_WWS);
    }

    public static List<Moveable> getPawnDirection() {
        return List.of(PAWN);
    }
}
