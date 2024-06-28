package wootecamp.chess.board;

import wootecamp.chess.pieces.Direction;

import java.util.Objects;

public class BoardPosition {
    private final int rankPosition;
    private final int filePosition;

    public BoardPosition(final String position) {
        char rankPosition = position.charAt(1);
        char filePosition = position.charAt(0);

        this.rankPosition = parseRankToIndex(rankPosition);
        this.filePosition = parseFileToIndex(filePosition);
    }

    private BoardPosition(int filePosition, int rankPosition) {
        this.rankPosition = rankPosition;
        this.filePosition = filePosition;
    }

    //TODO : validation

    private int parseRankToIndex(final char rankPosition) {
        final char standardChar = '8';
        return standardChar - rankPosition;
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
