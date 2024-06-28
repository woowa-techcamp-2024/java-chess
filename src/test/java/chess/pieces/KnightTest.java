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

public class KnightTest {

    private static Piece whiteKnight;
    private static Piece blackKnight;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whiteKnight = PieceFactory.createWhiteKnight();
        blackKnight = PieceFactory.createBlackKnight();

    }

    @DisplayName("나이트의 이동가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void possiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    public static Stream<Arguments> possiblePositions() {
        return Stream.of(
                Arguments.of(Position.d4, Position.b5, whiteKnight),
                Arguments.of(Position.d4, Position.b3, whiteKnight),
                Arguments.of(Position.d4, Position.f5, whiteKnight),
                Arguments.of(Position.d4, Position.f3, whiteKnight),
                Arguments.of(Position.d4, Position.c6, whiteKnight),
                Arguments.of(Position.d4, Position.c2, whiteKnight),
                Arguments.of(Position.d4, Position.e6, whiteKnight),
                Arguments.of(Position.d4, Position.e2, whiteKnight)
        );
    }

    @DisplayName("나이트의 이동 불가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.d3, whiteKnight),
                Arguments.of(Position.c2, Position.d2, whiteKnight)
        );
    }
}
