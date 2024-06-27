package chess.pieces;

import java.util.Arrays;
import java.util.List;

public abstract class Piece {
    private final Color color;
    private final Type type;

    // verifyMovePosition은 Piece가 유효한 좌표를 이동할 수 있는 지 검증하는 책임만 가지고
    // 주변과의 상호작용은 ChessGame에서 담당하도록 변경
    public abstract boolean verifyMoveCoordinate(String source, String dest);

    public final Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return color.equals(Color.BLACK) ? Character.toUpperCase(type.getRepresentation()) : type.getRepresentation();
    }

    public double getDefaultPoint() {
        return type.getPoint();
    }

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public boolean isBlack() {
        return this.color.equals(Color.BLACK);
    }

    public boolean isWhite() {
        return this.color.equals(Color.WHITE);
    }

    public boolean isSameColorAndType(Color color, Type type) {
        return this.color.equals(color) && this.type.equals(type);
    }

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isSameType(Type type) {
        return this.type.equals(type);
    }

    public Type getType() {
        return type;
    }

    public boolean isBlank() {
        return this.type.equals(Type.NO_PIECE);
    }

    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0),
        ROOK('r', 5.0),
        KNIGHT('n', 2.5),
        BISHOP('b', 3.0),
        QUEEN('q', 9.0),
        KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;


        public double getPoint() {
            return defaultPoint;
        }

        public char getRepresentation() {
            return representation;
        }

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }
    }
    public enum Direction {
        NORTH(0, -1),
        NORTHEAST(1, -1),
        EAST(1, 0),
        SOUTHEAST(1, 1),
        SOUTH(0, 1),
        SOUTHWEST(-1, 1),
        WEST(-1, 0),
        NORTHWEST(-1, -1),

        NNE(1, -2),
        NNW(-1, -2),
        SSE(1, 2),
        SSW(-1, 2),
        EEN(2, -1),
        EES(2, 1),
        WWN(-2, -1),
        WWS(-2, 1);

        private int xDegree;
        private int yDegree;

        Direction(int xDegree, int yDegree) {
            this.xDegree = xDegree;
            this.yDegree = yDegree;
        }

        public int getXDegree() {
            return xDegree;
        }

        public int getYDegree() {
            return yDegree;
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

}
