package chess;

import static chess.Board.BOARD_SIZE;
import static pieces.Color.BLACK;
import static pieces.PieceType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pieces.Color;
import pieces.PieceType;

public enum DirectionType {
    NORTH(-1, 0),
    NORTHEAST(-1, 1),
    EAST(0, 1),
    SOUTHEAST(1, 1),
    SOUTH(1, 0),
    SOUTHWEST(1, -1),
    WEST(0, -1),
    NORTHWEST(-1, -1),

    NN(-2, 0),
    SS(2, 0),
    NNE(-2, 1),
    NNW(-2, -1),
    SSE(2, 1),
    SSW(2, -1),
    EEN(-1, 2),
    EES(1, 2),
    WWN(-1, -2),
    WWS(1, -2);

    private final int x;
    private final int y;

    DirectionType(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static DirectionType findByPath(Position source, Position destination, PieceType pieceType) {
        if (pieceType == PAWN || pieceType == KNIGHT || pieceType == KING) {

        }
        for (DirectionType directionType : DirectionType.values()) {
            Position temp = source;
            while (Position.isInvalidRange(temp.getRow(), temp.getColumn())) {
                temp = temp.addDirection(directionType);
                if ()
            }
        }
        return Arrays.stream(values())
            .filter(directionType -> directionType.getX() == destination.getRow() - source.getRow()
                && directionType.getY() == destination.getColumn() - source.getColumn())
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public static List<DirectionType> rookDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<DirectionType> bishopDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<DirectionType> queenDirection() {
        List<DirectionType> directionTypes = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
        }
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<DirectionType> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<DirectionType> pawnDirection(Color color) {
        return color == BLACK ? blackPawnDirection() : whitePawnDirection();
    }

    public static List<DirectionType> kingDirection() {
        return Arrays.asList(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST);
    }

    private static List<DirectionType> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    private static List<DirectionType> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }
}
