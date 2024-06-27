package pe.goblin.chess.domain.board;

public record Position(int row, int col) {
    Position(String posStr, int rowSize, int columnSize) {
        this(columnSize - 1 - (posStr.charAt(1) - '1'), posStr.charAt(0) - 'a');
    }
}
