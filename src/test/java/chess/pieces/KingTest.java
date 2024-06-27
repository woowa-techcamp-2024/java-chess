package chess.pieces;

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
    void possiblePositionsFromStartPosition(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    @MethodSource
    public static Stream<Arguments> possiblePositionsFromStartPosition() {
        return Stream.of(
                Arguments.of(Position.d2, Position.c1, whiteKing),
                Arguments.of(Position.d2, Position.c2, whiteKing),
                Arguments.of(Position.d2, Position.c3, whiteKing),
                Arguments.of(Position.d2, Position.d3, whiteKing),
                Arguments.of(Position.d2, Position.d1, whiteKing),
                Arguments.of(Position.d2, Position.e1, whiteKing),
                Arguments.of(Position.d2, Position.e2, whiteKing),
                Arguments.of(Position.d2, Position.e3, whiteKing),
                Arguments.of(Position.d6, Position.c5, blackKing),
                Arguments.of(Position.d6, Position.c6, blackKing),
                Arguments.of(Position.d6, Position.c7, blackKing),
                Arguments.of(Position.d6, Position.d5, blackKing),
                Arguments.of(Position.d6, Position.d7, blackKing),
                Arguments.of(Position.d6, Position.e5, blackKing),
                Arguments.of(Position.d6, Position.e6, blackKing),
                Arguments.of(Position.d6, Position.e7, blackKing)
        );
    }
}
