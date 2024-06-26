package chess.board;

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

    public Coordinate(String coordinate) {
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
}
