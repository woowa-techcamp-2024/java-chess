package application;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessViewTest {

    @Test
    @DisplayName("체스판을 출력한다.")
    void printBoard() {
        var board = new Board();
        var chessView = new ChessView(board);
        var expectedBoardString = """
            ♜♞♝♛♚♝♞♜
            ♟♟♟♟♟♟♟♟
            □□□□□□□□
            □□□□□□□□
            □□□□□□□□
            □□□□□□□□
            ♙♙♙♙♙♙♙♙
            ♖♘♗♕♔♗♘♖
            """;
        board.initialize();

        var actualResult = chessView.printBoard();
        assertThat(actualResult).isEqualTo(expectedBoardString);

    }
}