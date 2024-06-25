package chess.util;

import java.util.Objects;

public class ChessPoint {
    private final char file; // 가로축
    private final int rank;  // 세로축

    private ChessPoint(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public char getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public static ChessPoint of(char file, int rank) {
        if (!isValidFile(file) || !isValidRank(rank)) {
            throw new IllegalArgumentException("유효하지 않은 체스 좌표입니다.");
        }
        return new ChessPoint(file, rank);
    }

    public static ChessPoint of(String point) {
        if (point.length() != 2) {
            throw new IllegalArgumentException("유효하지 않은 체스 좌표입니다.");
        }
        char file = point.charAt(0);
        int rank = Character.getNumericValue(point.charAt(1));
        if (!isValidFile(file) || !isValidRank(rank)) {
            throw new IllegalArgumentException("유효하지 않은 체스 좌표입니다.");
        }
        return of(file, rank);
    }

    private static boolean isValidFile(char file) {
        return file >= 'a' && file <= 'h';
    }

    private static boolean isValidRank(int rank) {
        return rank >= 1 && rank <= 8;
    }

    @Override
    public String toString() {
        return "" + file + rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPoint that = (ChessPoint) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
