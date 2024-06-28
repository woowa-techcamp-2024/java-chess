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

class KingTest {
    private static Piece whiteKing;
    private static Piece blackKing;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whiteKing = PieceFactory.createWhiteKing();
        blackKing = PieceFactory.createBlackKing();

    }

    @DisplayName("킹의 이동가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void possiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    public static Stream<Arguments> possiblePositions() {
        return Stream.of(
                Arguments.of(Position.d4, Position.d3, whiteKing),
                Arguments.of(Position.d4, Position.d5, whiteKing),
                Arguments.of(Position.d4, Position.c4, whiteKing),
                Arguments.of(Position.d4, Position.e4, whiteKing),
                Arguments.of(Position.d4, Position.c5, whiteKing),
                Arguments.of(Position.d4, Position.e5, whiteKing),
                Arguments.of(Position.d4, Position.c3, whiteKing),
                Arguments.of(Position.d4, Position.e3, whiteKing),
                Arguments.of(Position.d4, Position.d3, blackKing),
                Arguments.of(Position.d4, Position.d5, blackKing),
                Arguments.of(Position.d4, Position.c4, blackKing),
                Arguments.of(Position.d4, Position.e4, blackKing),
                Arguments.of(Position.d4, Position.c5, blackKing),
                Arguments.of(Position.d4, Position.e5, blackKing),
                Arguments.of(Position.d4, Position.c3, blackKing),
                Arguments.of(Position.d4, Position.e3, blackKing)
        );
    }

    @DisplayName("킹의 이동 불가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.a4, whiteKing),
                Arguments.of(Position.c2, Position.c2, whiteKing),
                Arguments.of(Position.c2, Position.d4, whiteKing),
                Arguments.of(Position.c2, Position.e2, whiteKing)
        );
    }
}
