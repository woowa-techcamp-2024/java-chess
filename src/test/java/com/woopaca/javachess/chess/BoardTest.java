package com.woopaca.javachess.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woopaca.javachess.chess.utils.StringUtils.appendNewLine;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 테스트")
public class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("체스판을 초기화하면 흰색 폰과 검은색 폰이 추가된다.")
    @Test
    void initialize() {
        board.initialize();
        assertThat(board.getWhitePawnsResult()).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(board.getBlackPawnsResult()).isEqualTo("♟♟♟♟♟♟♟♟");

        System.out.println(board.print());
    }

    @DisplayName("체스판의 모든 기물의 상태를 볼 수 있다.")
    @Test
    void print() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);

        String blankRow = appendNewLine("........");
        assertThat(board.showBoard()).isEqualTo(
                appendNewLine("♜♞♝♛♚♝♞♜") +
                        appendNewLine("♟♟♟♟♟♟♟♟") +
                        blankRow + blankRow + blankRow + blankRow +
                        appendNewLine("♙♙♙♙♙♙♙♙") +
                        appendNewLine("♖♘♗♕♔♗♘♖")
        );
    }

}
