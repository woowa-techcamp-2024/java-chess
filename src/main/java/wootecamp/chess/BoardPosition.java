package wootecamp.chess;

public class BoardPosition {
    private final int rankPosition;
    private final int filePosition;

    public BoardPosition(final String position) {
        char rankPosition = position.charAt(1);
        char filePosition = position.charAt(0);

        this.rankPosition = parseRankToIndex(rankPosition);
        this.filePosition = parseFileToIndex(filePosition);
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
}
