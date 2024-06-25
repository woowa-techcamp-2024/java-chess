package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.pieces.Pawn;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private static Pawn whitePawn;

    private static Pawn blackPawn;

    @BeforeAll
    static void beforeAll() {
        whitePawn = new Pawn(Pawn.WHITE_COLOR);
        blackPawn = new Pawn(Pawn.BLACK_COLOR);
    }

    @DisplayName("체스판에 흰색/검은색 폰을 추가한다.")
    @Test
    void create() {
        Board board = new Board();

        AtomicInteger index = new AtomicInteger();
        List<Pawn> pawns = List.of(whitePawn, blackPawn);

        pawns.forEach(pawn -> {
            board.add(pawn);
            assertBoard(board, index.getAndIncrement(), pawn);
        });

    }

    void assertBoard(Board board, int index, Pawn pawn) {
        assertEquals(index + 1, board.size());
        assertEquals(pawn, board.findPawn(index));
    }
}
