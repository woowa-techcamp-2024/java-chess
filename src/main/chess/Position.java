package chess;

import java.util.Objects;

public class Position {

    public final int r, c;

    protected Position(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int rank() {
        return 1 + r;
    }

    public char file() {
        return (char) ('a' + c);
    }

    @Override
    public String toString() {
        return file() + "" + rank();
    }

    public static Position of(int r, int c) {
        return new Position(r, c);
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
        return r == position.r && c == position.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}
