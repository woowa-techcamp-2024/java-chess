package chess.board;

import java.util.Objects;

public class Coordinate {

    private final int rankIndex;
    private final int widthIndex;

    private static final int BOARD_WIDTH = 8;
    private static final int RANK_HEIGHT = 8;
    private static final int WIDTH_INDEX = 0;
    private static final int RANK_INDEX = 1;

    public int getRankIndex() {
        return rankIndex;
    }

    public int getWidthIndex() {
        return widthIndex;
    }

    public static Coordinate of(String coordinate) {
        return new Coordinate(coordinate);
    }

    public static Coordinate of(int rankIndex, int widthIndex) {
        return new Coordinate(rankIndex, widthIndex);
    }

    private Coordinate(int rankIndex, int widthIndex) {
        this.rankIndex = validateRankIndex(rankIndex);
        this.widthIndex = validateWidthIndex(widthIndex);
    }

    private int validateRankIndex(int rankIndex) {
        if(rankIndex < 0 || rankIndex >= RANK_HEIGHT) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return rankIndex;
    }

    private int validateWidthIndex(int widthIndex) {
        if(widthIndex < 0 || widthIndex >= BOARD_WIDTH) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return widthIndex;
    }

    private Coordinate(String coordinate) {
        validateCoordinate(coordinate);
        this.rankIndex = convertRankIndex(coordinate.charAt(RANK_INDEX));
        this.widthIndex = convertWidthIndex(coordinate.charAt(WIDTH_INDEX));
    }

    private void validateCoordinate(String coordinate) {
        if(coordinate == null || coordinate.isBlank()) {
            throw new IllegalArgumentException("좌표를 입력해주세요.");
        }
        if(coordinate.length() != 2) {
            throw new IllegalArgumentException("좌표는 2글자여야 합니다.");
        }
    }

    private int convertRankIndex(char rank) {
        if(rank >= 'a') {
            throw new IllegalArgumentException("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
        }
        int convertedRank = rank - '1';

        if(convertedRank < 0 || convertedRank >= RANK_HEIGHT) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return RANK_HEIGHT - convertedRank - 1;
    }

    private int convertWidthIndex(char width) {
        if(width < 'a') {
            throw new IllegalArgumentException("좌표는 알파벳과 숫자의 순서로 이루어져야 합니다.");
        }

        int convertedWidth = width - 'a';

        if(convertedWidth < 0 || convertedWidth >= BOARD_WIDTH) {
            throw new IllegalArgumentException("범위를 넘어선 좌표입니다.");
        }
        return convertedWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return rankIndex == that.rankIndex && widthIndex == that.widthIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankIndex, widthIndex);
    }
}
