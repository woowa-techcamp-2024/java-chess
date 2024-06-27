package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Direction;

public class Position {

    private final int rank;
    private final int file;

    public Position(String fileRank) {
        if (fileRank.length() > 2) {
            throw new IllegalArgumentException(String.format("[ERROR] 위치 값의 길이가 올바르지 않습니다. file & rank: %s", fileRank));
        }

        this.file = fileRank.charAt(0) - 'a';
        this.rank = fileRank.charAt(1) - '0';
    }

    public Position(int fileIndex, int rankIndex) {
        this.file = fileIndex;
        this.rank = Board.BOARD_SIZE - rankIndex;
    }

    public int getFileIndex() {
        return file;
    }

    public int getRankIndex() {
        return Board.BOARD_SIZE - rank;
    }

    public Position moveIn(Direction direction) {
        return new Position(
                file + direction.getFileDegree(),
                Board.BOARD_SIZE - (rank + direction.getRankDegree())
        );
    }

    public boolean isValid() {
        return file >= 0 && file < Board.BOARD_SIZE && rank > 0 && rank <= Board.BOARD_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

}
