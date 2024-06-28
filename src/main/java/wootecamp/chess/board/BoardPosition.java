package wootecamp.chess.board;

import wootecamp.chess.pieces.Direction;

import java.util.Objects;

public class BoardPosition {
    private static final int BOARD_SIZE = 8;

    private final int rankPosition;
    private final int filePosition;

    public BoardPosition(final String position) {
        char rankPosition = position.charAt(1);
        char filePosition = position.charAt(0);

        int rankIndex = parseRankToIndex(rankPosition);
        int fileIndex = parseFileToIndex(filePosition);

        validation(rankIndex, fileIndex);

        this.rankPosition = rankIndex;
        this.filePosition = fileIndex;
    }

    private BoardPosition(int filePosition, int rankPosition) {
        validation(rankPosition, filePosition);
        this.rankPosition = rankPosition;
        this.filePosition = filePosition;
    }

    private void validation(int filePosition, int rankPosition) {
        if (filePosition < 0 || filePosition >= BOARD_SIZE || rankPosition < 0 || rankPosition >= BOARD_SIZE) {
            throw new RuntimeException("보드를 벗어났습니다.");
        }
    }

    private int parseRankToIndex(final char rankPosition) {
        final char standardChar = '1';
        return rankPosition - standardChar;
    }

    private int parseFileToIndex(final char filePosition) {
        final char standardChar = 'a';
        return filePosition - standardChar;
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
