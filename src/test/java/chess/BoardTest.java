package chess;

import chess.pieces.Rook;
import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("체스 판을 초기화한다.")
    void initialization() {
        var expectedBoard = """
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

        assertThat(board.size()).isEqualTo(32);
        assertThat(board.print()).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("체스 판에서 말을 찾는다.")
    void getPiece() {
        board.initialize();
        var actualResult = board.getPiece("a1");

        assertThat(actualResult).isInstanceOf(Rook.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_ROOK);
    }

}