package chess;

import java.util.Objects;

public class Position {

    public final int rankIndex, fileIndex;

    protected Position(int rankIndex, int fileIndex) {
        this.rankIndex = rankIndex;
        this.fileIndex = fileIndex;
    }

    public int rank() {
        return 1 + rankIndex;
    }

    public char file() {
        return (char) ('a' + fileIndex);
    }

    public Position add(Offset offset) {
        return new Position(rankIndex + offset.getRank(), fileIndex + offset.getFile());
    }

    public boolean isValid() {
        return 0 <= rankIndex && rankIndex < Board.LENGTH && 0 <= fileIndex && fileIndex < Board.LENGTH;
    }

    @Override
    public String toString() {
        return file() + "" + rank();
    }

    public static Position of(int rankIndex, int fileIndex) {
        return new Position(rankIndex, fileIndex);
    }

    public static Position of(char file, int rank) {
        int fileIndex = file - 'a';
        int rankIndex = rank - 1;
        return new Position(rankIndex, fileIndex);
    }

    public static Position of(String fileRank) {
        if (fileRank.length() == 2) {
            char fileChar = fileRank.charAt(0);
            char rankChar = fileRank.charAt(1);
            int file = fileChar - 'a';
            int rank = rankChar - '1';
            return new Position(rank, file);
        }
        throw new IllegalArgumentException(fileRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rankIndex == position.rankIndex && fileIndex == position.fileIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankIndex, fileIndex);
    }
}
