package wootecamp.chess.board;

import java.util.Objects;
import java.util.Optional;

public class BoardPosition {
    private static final int BOARD_SIZE = 8;

    private final char rank;
    private final char file;
    private final int y;
    private final int x;

    public BoardPosition(final String position) {
        this.rank = position.charAt(1);
        this.file = position.charAt(0);

        int rankIndex = parseRankToIndex(rank);
        int fileIndex = parseFileToIndex(file);

        this.x = fileIndex;
        this.y = rankIndex;

        validate(x, y);
    }

    public BoardPosition(int x, int y) {
        validate(x, y);

        this.rank = parseIndexToRank(y);
        this.file = parseIndexToFile(x);
        this.x = x;
        this.y = y;
    }

    private void validate(int x, int y) {
        if (!validatePosition(x, y)) {
            throw new IllegalArgumentException("보드를 벗어났습니다.");
        }
    }

    public boolean validatePosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    private static int parseRankToIndex(final char rank) {
        final char standardChar = '1';
        return rank - standardChar;
    }

    private static int parseFileToIndex(final char file) {
        final char standardChar = 'a';
        return file - standardChar;
    }

    private static char parseIndexToRank(final int rankIndex) {
        final char standardChar = '1';
        return (char) (rankIndex + standardChar);
    }

    private static char parseIndexToFile(final int fileIndex) {
        final char standardChar = 'a';
        return (char) (fileIndex + standardChar);
    }

    public char getRank() {
        return rank;
    }

    public char getFile() {
        return file;
    }

    public String showPosition() {
        return String.valueOf(file) + rank;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean canCreateNextPosition(final Direction direction) {
        int nextX = x + direction.getX();
        int nextY = y + direction.getY();

        return validatePosition(nextX, nextY);
    }

    public BoardPosition createNextPosition(Direction direction) {
        return new BoardPosition(this.x + direction.getX(),this.y + direction.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPosition that = (BoardPosition) o;
        return y == that.y && x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
