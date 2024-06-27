package org.example.chess.pieces.enums;

import java.util.Arrays;
import java.util.List;
import org.example.chess.board.Position;

public enum Direction {
    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    EAST(0, 1),
    SOUTHEAST(1, 1),
    SOUTH(1, 0),
    SOUTHWEST(1, -1),
    WEST(0, -1),
    NORTHWEST(-1, -1),

    NNE(-2, 1),
    NNW(-2, -1),
    SSE(2, 1),
    SSW(2, -1),
    EEN(-1, 2),
    EES(1, 2),
    WWN(-1, -2),
    WWS(1, -2),
    ;

    private int deltaR;
    private int deltaC;

    Direction(int deltaR, int deltaC) {
        this.deltaR = deltaR;
        this.deltaC = deltaC;
    }

    public int getDeltaR() {
        return deltaR;
    }

    public int getDeltaC() {
        return deltaC;
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> kingDirection() {
        return Arrays.asList(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH);
    }

    public static List<Direction> rookDirection() {
        return Arrays.asList(NORTH, EAST, WEST, SOUTH);
    }

    public static List<Direction> BishopDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> queenDirection() {
        return Arrays.asList(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH);
    }

    public boolean isMovableDirection(Position delta) {
        return this.getDeltaR() == delta.getR() && this.getDeltaC() == delta.getC();
    }
}
