package pe.goblin.chess.board;

import static pe.goblin.chess.board.Board.MAX_COLS;

public record Position(int row, int col) {
    Position(String posStr) {
        this(MAX_COLS - (posStr.charAt(1) - '1'), posStr.charAt(0) - 'a');
    }
}
