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

public class PawnTest {

    private static Piece whitePawn;
    private static Piece blackPawn;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
    }

    @BeforeAll
    static void beforeAll() {
        whitePawn = PieceFactory.createWhitePawn();
        blackPawn = PieceFactory.createBlackPawn();

    }

    @DisplayName("화이트 폰은 처음에 두칸 직진 이동 가능하다.")
    @Test
    void whitePawnPossibleToMoveTwoStepsAfterStartingPoint() {
        Position start = Position.c2;
        Position target = Position.c4;
        board.move(start, whitePawn);
        board.move(start, target);
        assertMove(board, start, target, whitePawn);
    }

    @DisplayName("검은색 폰은 처음에 두칸 직진 이동 가능하다.")
    @Test
    void blackPawnPossibleToMoveTwoStepsAfterStartingPoint() {
        Position start = Position.c7;
        Position target = Position.c5;
        board.move(start, blackPawn);
        board.move(start, target);
        assertMove(board, start, target, blackPawn);
    }


    @DisplayName("폰은 처음이 아니면 두칸 이동이 불가능하다.")
    @Test
    void impossibleToMoveTwoStepsAfterStartingPoint() {
        Position startPoint = Position.c2;
        board.move(Position.c2, whitePawn);
        board.move(Position.c2, Position.c3);
        assertImpossibleMove(board, Position.c3, Position.c5);
    }


    @DisplayName("폰이 직진 이동만 가능하다.")
    @ParameterizedTest
    @MethodSource
    void impossiblePositions(Position src, Position target, Piece pawn) {
        board.move(src, pawn);
        assertImpossibleMove(board, src, target);
    }

    @MethodSource
    public static Stream<Arguments> impossiblePositions() {
        return Stream.of(
                Arguments.of(Position.c2, Position.d3, whitePawn),
                Arguments.of(Position.c2, Position.d2, whitePawn),
                Arguments.of(Position.d7, Position.d8, blackPawn),
                Arguments.of(Position.d7, Position.c7, blackPawn)
        );
    }
}
