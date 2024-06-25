package woowa.camp.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowa.camp.pieces.Color;
import woowa.camp.pieces.Pawn;

public class BoardTest {

    Board board;
    Pawn white;
    Pawn black;

    @BeforeEach
    void setUp() {
        board = new Board();
        white = new Pawn(Color.PAWN_WHITE);
        black = new Pawn(Color.PAWN_BLACK);
    }

    @Test
    @DisplayName("[Success] Pawn을 체스판에 추가할 수 있다.")
    void board_add_pawn() {
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
    void findOutOfRange() {
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

    @Test
    @DisplayName("[Success] 초기화한 Board이 관리하고 있는 Pawn의 결과를 확인")
    void getPawnsResult() {
        board.initialize();
        String expectedWhitePawnsResult = "pppppppp";
        String expectedBlackPawnsResult = "PPPPPPPP";

        assertThat(board.getPawnsResult(Color.PAWN_WHITE)).isEqualTo(expectedWhitePawnsResult);
        assertThat(board.getPawnsResult(Color.PAWN_BLACK)).isEqualTo(expectedBlackPawnsResult);
    }

    @Test
    @DisplayName("[Success] 초기화한 Board가 가지고 있는 검은색 Pawn과 흰색 Pawn은 각각 8개이다.")
    void initialPawnSize() {
        board.initialize();
        int expectedPawnsCount = Board.MAX_PAWN;

        verifyInitialPawnsCount(board, expectedPawnsCount);
    }

    private void verifyInitialPawnsCount(Board board, int expectedPawnsCount) {
        assertThat(board.getPawnsResult(Color.PAWN_WHITE).length()).isEqualTo(expectedPawnsCount);
        assertThat(board.getPawnsResult(Color.PAWN_BLACK).length()).isEqualTo(expectedPawnsCount);
    }

    @Test
    @DisplayName("[Success] print 메서드 콘솔 출력 테스트")
    void print() {
        board.initialize();
        String print = board.print();
        System.out.println(print);
    }

}
