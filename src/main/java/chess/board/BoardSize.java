package chess.board;

public enum BoardSize {
    RANK(8), FILE(8);

    private final int size;

    private BoardSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
