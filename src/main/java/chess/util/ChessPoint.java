package chess.util;

public record ChessPoint(char file, int rank) {
    public ChessPoint {
        if (!isValidFile(file) || !isValidRank(rank)) {
            throw new IllegalArgumentException("유효하지 않은 체스 좌표입니다.");
        }
    }

    public static ChessPoint of(char file, int rank) {
        return new ChessPoint(file, rank);
    }

    public static ChessPoint of(String point) {
        if (point.length() != 2) {
            throw new IllegalArgumentException("유효하지 않은 체스 좌표입니다.");
        }
        char file = point.charAt(0);
        int rank = Character.getNumericValue(point.charAt(1));
        return new ChessPoint(file, rank);
    }

    private static boolean isValidFile(char file) {
        return file >= 'a' && file <= 'h';
    }

    private static boolean isValidRank(int rank) {
        return rank >= 1 && rank <= 8;
    }

    public ChessPoint move(Direction direction, int distance) {
        return new ChessPoint((char) (file + direction.getXDegree() * distance), rank + direction.getYDegree() * distance);
    }

    public boolean isMoveable(Direction direction, int distance) {
        return isValidFile((char) (file + direction.getXDegree() * distance)) && isValidRank(rank + direction.getYDegree() * distance);
    }
}
