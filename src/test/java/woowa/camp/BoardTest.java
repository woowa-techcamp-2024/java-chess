package woowa.camp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("[Success] Pawn을 체스판에 추가할 수 있다.")
    void board_add_pawn() {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        Pawn black = new Pawn(Pawn.BLACK_COLOR);

        board.add(white);
        verifyBoardSize(board, 1);
        verifyFindPawn(board, 0, white);

        board.add(black);
        verifyBoardSize(board, 2);
        verifyFindPawn(board, 1, black);
    }

    private void verifyBoardSize(Board board, int expectedBoardSize) {
        assertThat(board.size()).isEqualTo(expectedBoardSize);
    }

    private void verifyFindPawn(Board board, int findPawnIndex, Pawn expectedPawn) {
        assertThat(board.findPawn(findPawnIndex)).isEqualTo(expectedPawn);
    }

    @Test
    @DisplayName("[Exception] 체스판의 Pawn을 찾을 때 올바르지 않은 범위이면, 예외가 발생한다.")
    void temp() {
        Board board = new Board();
        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(white);
        board.add(black);

        int lowerBound = -1;
        int upperBound = board.size();

        verifyOutOfRangeFindPawn(board, lowerBound, upperBound);
    }

    private void verifyOutOfRangeFindPawn(Board board, int lowerBound, int upperBound) {
        assertThatThrownBy(() -> board.findPawn(lowerBound)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.findPawn(upperBound)).isInstanceOf(IllegalArgumentException.class);
    }

}
