package pe.goblin.chess.domain.board.type;

import java.util.List;

public enum BoardType {
    CLASSIC(8, 8, List.of(
            "RNBQKBNR",
            "PPPPPPPP",
            "........",
            "........",
            "........",
            "........",
            "pppppppp",
            "rnbqkbnr"
    ));

    private final int rowSize;
    private final int colSize;
    private final List<String> initialState;

    BoardType(int rowSize, int colSize, List<String> initialState) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.initialState = initialState;
    }

    public static BoardType of(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException();
        }
        return BoardType.values()[ordinal];
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return colSize;
    }

    public List<String> getInitialState() {
        return initialState;
    }
}
