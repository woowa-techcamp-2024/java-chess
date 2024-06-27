package chess.board;

public class Position {

    private int rank;
    private int file;
    private final int BOARD_SIZE = 8;

    public Position(String position) {
        rank = parseRank(position);
        file = parseFile(position);
    }

    public int parseRank(String position) {
        return BOARD_SIZE - Character.getNumericValue(position.charAt(1));
    }

    public int parseFile(String position) {
        return position.charAt(0) - 'a';
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }
}
