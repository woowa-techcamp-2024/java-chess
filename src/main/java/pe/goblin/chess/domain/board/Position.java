package pe.goblin.chess.domain.board;

public record Position(int row, int col) {
    Position(String posStr) {
        this(Board.MAX_COLS - (posStr.charAt(1) - '1'), posStr.charAt(0) - 'a');
    }
}
