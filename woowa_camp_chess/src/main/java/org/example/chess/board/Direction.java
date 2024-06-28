package org.example.chess.board;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(-1, 0),
    NORTHEAST(-1, -1),
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
    WWS(1, -2);

    private int xDegree;
    private int yDegree;

    private Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static List<Direction> getDirectionsByTypeAndColor(PieceType pieceType, Piece.Color color) {
        return switch (pieceType) {
            case PAWN -> color == Piece.Color.WHITE ? List.of(NORTH) : List.of(SOUTH);
            case ROOK -> List.of(NORTH, SOUTH, EAST, WEST);
            case KNIGHT -> List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
            case BISHOP -> List.of(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
            case QUEEN -> List.of(NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
            case KING -> List.of(NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
            default -> throw new IllegalArgumentException("Unknown PieceType: " + pieceType);
        };
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
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
}
