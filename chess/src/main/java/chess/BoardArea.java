package chess;

public enum BoardArea {
    COL(8),
    ROW(8)
    ;
    private final int num;

    BoardArea(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
