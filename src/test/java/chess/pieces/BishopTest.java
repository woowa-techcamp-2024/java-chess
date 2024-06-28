package chess.pieces;

import static chess.pieces.PieceTestUtil.assertImpossibleMove;
import static chess.pieces.PieceTestUtil.assertMove;

import chess.board.Board;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopTest {
    private static Piece whiteBishop;
    private static Piece blackBishop;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whiteBishop = PieceFactory.createWhiteBishop();
        blackBishop = PieceFactory.createBlackBishop();

    }

    @DisplayName("비숍의 이동가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void possiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    @MethodSource
    public static Stream<Arguments> possiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.b3, whiteBishop),
                Arguments.of(Position.c2, Position.a4, whiteBishop),
                Arguments.of(Position.c2, Position.d3, whiteBishop),
                Arguments.of(Position.c2, Position.b1, whiteBishop),
                Arguments.of(Position.c2, Position.d1, whiteBishop),
                Arguments.of(Position.c6, Position.b5, blackBishop),
                Arguments.of(Position.c6, Position.a4, blackBishop),
                Arguments.of(Position.c6, Position.d7, blackBishop),
                Arguments.of(Position.c6, Position.b5, blackBishop),
                Arguments.of(Position.c6, Position.d7, blackBishop)
        );
    }

    @DisplayName("비숍의 이동 불가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece bishop) {
        board.move(src, bishop);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.b2, whiteBishop),
                Arguments.of(Position.c2, Position.d2, whiteBishop),
                Arguments.of(Position.c2, Position.c1, whiteBishop),
                Arguments.of(Position.c2, Position.c3, whiteBishop),
                Arguments.of(Position.c6, Position.c5, blackBishop),
                Arguments.of(Position.c6, Position.c7, blackBishop)
        );
    }

    @Test
    void test() {
        board.move(Position.c2, blackBishop);
        board.move(Position.c2, Position.a4);
    }
}
