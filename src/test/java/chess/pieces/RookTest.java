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

class RookTest {
    private static Piece whiteRook;
    private static Piece blackRook;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whiteRook = PieceFactory.createWhiteRook();
        blackRook = PieceFactory.createBlackRook();

    }

    @DisplayName("룩의 이동가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void possiblePositions(Position src, Position target, Piece king) {
        board.move(src, king);
        board.move(src, target);
        assertMove(board, src, target, king);
    }

    public static Stream<Arguments> possiblePositions() {
        return Stream.of(
                Arguments.of(Position.d4, Position.d3, whiteRook),
                Arguments.of(Position.d4, Position.d5, whiteRook),
                Arguments.of(Position.d4, Position.c4, whiteRook),
                Arguments.of(Position.d4, Position.e4, whiteRook),
                Arguments.of(Position.d4, Position.d3, blackRook),
                Arguments.of(Position.d4, Position.d5, blackRook),
                Arguments.of(Position.d4, Position.c4, blackRook),
                Arguments.of(Position.d4, Position.e4, blackRook)
        );
    }

    @DisplayName("룩의 이동 불가능한 곳들을 확인한다")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece rook) {
        board.move(src, rook);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.b3, whiteRook),
                Arguments.of(Position.c2, Position.b1, whiteRook)
        );
    }
}
