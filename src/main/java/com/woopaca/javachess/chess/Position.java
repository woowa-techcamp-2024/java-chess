package com.woopaca.javachess.chess;

public class Position {

    private final int file;
    private final int rank;

    public Position(String fileRank) {
        if (fileRank.length() > 2) {
            throw new IllegalArgumentException(String.format("[ERROR] 위치 값의 길이가 올바르지 않습니다. file & rank: %s", fileRank));
        }

        this.file = fileRank.charAt(0) - 'a';
        this.rank = fileRank.charAt(1) - '1';
        validateFileAndRank();
    }

    public Position(int fileIndex, int rankIndex) {
        this.file = fileIndex;
        this.rank = rankIndex;

        validateFileAndRank();
    }

    private void validateFileAndRank() {
        if (file < 0 || file >= Board.BOARD_SIZE || rank < 0 || rank >= Board.BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] File 또는 Rank 값이 올바르지 않습니다. file: %c, rank: %c", 'a' + file, '0' + rank));
        }
    }

    public int getFileIndex() {
        return file;
    }

    public int getRankIndex() {
        return Board.BOARD_SIZE - rank - 1;
    }

}
