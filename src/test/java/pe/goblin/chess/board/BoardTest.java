package pe.goblin.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pe.goblin.chess.piece.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스 판 위에 말이 없는 상태에서 시작한다.")
    void create_empty() {
        assertEquals(0, board.size());
    }

    @Test
    @DisplayName("Pawn을 체스 판에 추가할 수 있다.")
    void create() throws ExceedPawnException {
        Piece white = Piece.createWhitePawn();
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Piece black = Piece.createBlackPawn();
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Nested
    @DisplayName("Pawn을 추가할 때마다 Pawn의 수가 맞는지 확인한다.")
    class create_exceed_then_throw {
        @Test
        @DisplayName("white pawn을 추가하는 경우")
        void in_case_of_white_pawn() throws ExceedPawnException {
            for (int i = Board.MIN_ROWS; i < Board.MAX_ROWS; i++) {
                board.add(Piece.createWhitePawn());
            }
            assertThrows(Exception.class, () -> board.add(Piece.createWhitePawn()));
        }

        @Test
        @DisplayName("black pawn을 추가하는 경우")
        void in_case_of_black_pawn() throws ExceedPawnException {
            for (int i = Board.MIN_ROWS; i < Board.MAX_ROWS; i++) {
                board.add(Piece.createBlackPawn());
            }
            assertThrows(Exception.class, () -> board.add(Piece.createBlackPawn()));
        }
    }

    @Test
    @DisplayName("Board는 초기화할 때 흰색 Pawn과 검은색 Pawn을 생성해 저장할 수 있다.")
    void initialize() {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("체스판의 현재 상태를 콘솔 화면에 출력한다")
    void print() {
        board.initialize();
        String result = board.print();
        System.out.println(result);
    }
}
