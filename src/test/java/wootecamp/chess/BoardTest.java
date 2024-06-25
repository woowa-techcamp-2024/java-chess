package wootecamp.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wootecamp.chess.pieces.Pawn;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 생성한다.")
    void create() {
        Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
        verifyAddPawn(whitePawn, 1, 0);

        Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);
        verifyAddPawn(blackPawn, 2, 1);
    }

    void verifyAddPawn(Pawn pawn, int expectedBoardSize, int pawnIndex) {
        board.add(pawn);
        assertThat(board.size()).isEqualTo(expectedBoardSize);
        assertThat(board.findPawn(pawnIndex)).isEqualTo(pawn);
    }
}
