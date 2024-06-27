package pe.goblin.chess.domain.board;

// TODO: Board는 BoardType에 따라 크기가 다르므로 Board.MAX_COLS 걷어내야한다.
public record Position(int row, int col) {
    Position(String posStr) {
        this(Board.MAX_COLS - (posStr.charAt(1) - '1'), posStr.charAt(0) - 'a');
    }
}
