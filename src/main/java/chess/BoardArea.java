package chess;

public enum BoardArea {
    X(8),
    Y(8)
    ;
    private final int num;

    private BoardArea(int num) {
        this.num = num;
    }

    public int getMax(){
        return num;
    }
}
