package com.woopaca.javachess.chess;

import com.woopaca.javachess.pieces.Piece.Color;
import com.woopaca.javachess.pieces.Piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woopaca.javachess.utils.StringUtils.appendNewLine;
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

    @DisplayName("체스판의 기물 개수를 조회할 수 있다.")
    @Test
    void piecesCount() {
        board.initialize();

        assertThat(board.getPiecesCount(Color.WHITE, Type.PAWN)).isEqualTo(8);
        assertThat(board.getPiecesCount(Color.WHITE, Type.ROOK)).isEqualTo(2);
        assertThat(board.getPiecesCount(Color.WHITE, Type.KING)).isEqualTo(1);
        assertThat(board.getPiecesCount(Color.BLACK, Type.PAWN)).isEqualTo(8);
        assertThat(board.getPiecesCount(Color.BLACK, Type.ROOK)).isEqualTo(2);
        assertThat(board.getPiecesCount(Color.BLACK, Type.KING)).isEqualTo(1);
    }

}
