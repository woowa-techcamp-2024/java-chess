package chess;

import chess.board.Board;
import chess.board.Position;
import chess.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    Rule rule;
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        rule = new Rule(board);
    }

    @Test
    @DisplayName("프로모션 여부 확인")
    void isPromotion() {
        board.add(Position.of("a8"), Pawn.create(PieceColor.WHITE));
        board.add(Position.of("d1"), Pawn.create(PieceColor.BLACK));
        board.add(Position.of("b7"), Pawn.create(PieceColor.BLACK));
        board.add(Position.of("c1"), Rook.create(PieceColor.BLACK));

        assertAll(() -> assertTrue(rule.isPromotion(Position.of("a8"))),
                () -> assertTrue(rule.isPromotion(Position.of("d1"))),
                () -> assertFalse(rule.isPromotion(Position.of("b1"))),
                () -> assertFalse(rule.isPromotion(Position.of("c1")))
        );
    }

    @Test
    @DisplayName("프로모션을 진행한다.")
    void promotion() {
        board.add(Position.of("a8"), Pawn.create(PieceColor.WHITE));

        rule.promotion(Position.of("a8"), "ROOK");

        assertEquals(board.findPiece(Position.of("a8")).getType(), Type.ROOK);
    }

    @Test
    @DisplayName("체크메이트를 확인한다.")
    void isCheckmate() {
        board.add(Position.of("b1"), King.create(PieceColor.BLACK));
        board.add(Position.of("b2"), Queen.create(PieceColor.WHITE));
        board.add(Position.of("b3"), King.create(PieceColor.WHITE));

        assertTrue(rule.isCheckmate(PieceColor.WHITE));
    }

    @Test
    @DisplayName("체크메이트를 확인한다.(초기 상황 체크메이트가 아닌 경우)")
    void isNotCheckmate_init() {
        board.initialize();

        assertFalse(rule.isCheckmate(PieceColor.WHITE));
        assertFalse(rule.isCheckmate(PieceColor.BLACK));
    }

    @Test
    @DisplayName("체크메이트를 확인한다.(체크메이트가 아닌 경우)")
    void isNotCheckmate() {
        board.add(Position.of("b1"), King.create(PieceColor.BLACK));
        board.add(Position.of("d4"), Queen.create(PieceColor.WHITE));
        board.add(Position.of("b3"), King.create(PieceColor.WHITE));

        assertFalse(rule.isCheckmate(PieceColor.WHITE));
        assertFalse(rule.isCheckmate(PieceColor.BLACK));
    }

    @Test
    @DisplayName("체크 여부를 확인한다.")
    void isCheck() {
        board.add(Position.of("b1"), King.create(PieceColor.BLACK));
        board.add(Position.of("b2"), Queen.create(PieceColor.WHITE));
        board.add(Position.of("b3"), King.create(PieceColor.WHITE));

        assertTrue(rule.isCheck(PieceColor.WHITE));
        assertFalse(rule.isCheck(PieceColor.BLACK));
    }

    @Test
    @DisplayName("체크 여부를 확인한다. (킹이 체크당하지 않은 경우)")
    void isNotCheck() {
        board.add(Position.of("b1"), King.create(PieceColor.BLACK));
        board.add(Position.of("b8"), Queen.create(PieceColor.WHITE));
        board.add(Position.of("b3"), King.create(PieceColor.WHITE));
        board.add(Position.of("b2"), Rook.create(PieceColor.WHITE));

        assertFalse(rule.isCheck(PieceColor.BLACK));
    }
}
