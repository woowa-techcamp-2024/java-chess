package wootecamp.chess.board;

import java.util.Objects;

public class BoardPosition {
    private static final int BOARD_SIZE = 8;

    private final char rank;
    private final char file;
    private final int rankPosition;
    private final int filePosition;

    public BoardPosition(final String position) {
        this.rank = position.charAt(1);
        this.file = position.charAt(0);

        int rankIndex = parseRankToIndex(rank);
        int fileIndex = parseFileToIndex(file);

        validation(rankIndex, fileIndex);

        this.rankPosition = rankIndex;
        this.filePosition = fileIndex;
    }

    private BoardPosition(int filePosition, int rankPosition) {
        validation(rankPosition, filePosition);

        this.rank = parseIndexToRank(rankPosition);
        this.file = parseIndexToFile(filePosition);
        this.rankPosition = rankPosition;
        this.filePosition = filePosition;
    }

    private void validation(int filePosition, int rankPosition) {
        if (filePosition < 0 || filePosition >= BOARD_SIZE || rankPosition < 0 || rankPosition >= BOARD_SIZE) {
            throw new IllegalArgumentException("보드를 벗어났습니다.");
        }
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

    public int getRankPosition() {
        return rankPosition;
    }

    public int getFilePosition() {
        return filePosition;
    }

    public BoardPosition createNextPosition(Direction direction) {
        return new BoardPosition(this.filePosition + direction.getX(), this.rankPosition + direction.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPosition that = (BoardPosition) o;
        return rankPosition == that.rankPosition && filePosition == that.filePosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankPosition, filePosition);
    }
}
