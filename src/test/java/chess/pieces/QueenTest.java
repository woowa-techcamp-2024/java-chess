package chess.pieces;

import static chess.pieces.PieceTestUtil.assertImpossibleMove;
import static chess.pieces.PieceTestUtil.assertMove;

import chess.board.Board;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {
    private static Piece whiteQueen;
    private static Piece blackQueen;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whiteQueen = PieceFactory.createWhiteQueen();
        blackQueen = PieceFactory.createBlackQueen();

    }

    @DisplayName("퀸의 이동가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void possiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    public static Stream<Arguments> possiblePositions() {
        return Stream.of(
                Arguments.of(Position.d4, Position.d3, whiteQueen),
                Arguments.of(Position.d4, Position.d5, whiteQueen),
                Arguments.of(Position.d4, Position.c4, whiteQueen),
                Arguments.of(Position.d4, Position.e4, whiteQueen),
                Arguments.of(Position.d4, Position.c5, whiteQueen),
                Arguments.of(Position.d4, Position.e5, whiteQueen),
                Arguments.of(Position.d4, Position.c3, whiteQueen),
                Arguments.of(Position.d4, Position.e3, whiteQueen),
                Arguments.of(Position.d4, Position.d3, blackQueen),
                Arguments.of(Position.d4, Position.d5, blackQueen),
                Arguments.of(Position.d4, Position.c4, blackQueen),
                Arguments.of(Position.d4, Position.e4, blackQueen),
                Arguments.of(Position.d4, Position.c5, blackQueen),
                Arguments.of(Position.d4, Position.e5, blackQueen),
                Arguments.of(Position.d4, Position.c3, blackQueen),
                Arguments.of(Position.d4, Position.e3, blackQueen)
        );
    }

    @DisplayName("퀸의 이동 불가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece queen) {
        board.move(src, queen);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.e3, whiteQueen),
                Arguments.of(Position.c2, Position.b4, whiteQueen)
        );
    }
}
