package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PawnTest {
    static Stream<Arguments> provideWhitePawnMoves() {
        return Stream.of(
                Arguments.of("b2", "b3", true),
                Arguments.of("b2", "c3", true),
                Arguments.of("b2", "b1", false),
                Arguments.of("b2", "c2", false)
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, destination={1}, expected={2}")
    @MethodSource("provideWhitePawnMoves")
    @DisplayName("Test White Pawn Moves")
    void testWhitePawnMoves(String source, String target, boolean expected) {
        Piece pawn = PieceFactory.createWhitePawn();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition destPos = new BoardPosition(target);
        MoveVector moveVector = new MoveVector(sourcePos, destPos);
        assertThat(pawn.verifyMovePosition(moveVector)).isEqualTo(expected);
    }

    static Stream<Arguments> provideBlackPawnMoves() {
        return Stream.of(
                Arguments.of("b7", "b6", true),
                Arguments.of("b7", "c6", true),
                Arguments.of("b1", "c3", false),
                Arguments.of("b1", "c3", false)
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, destination={1}, expected={2}")
    @MethodSource("provideBlackPawnMoves")
    @DisplayName("Test Black Pawn Moves")
    void testBlackPawnMoves(String source, String target, boolean expected) {
        Piece pawn = PieceFactory.createBlackPawn();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition destPos = new BoardPosition(target);
        MoveVector moveVector = new MoveVector(sourcePos, destPos);
        assertThat(pawn.verifyMovePosition(moveVector)).isEqualTo(expected);
    }
}
