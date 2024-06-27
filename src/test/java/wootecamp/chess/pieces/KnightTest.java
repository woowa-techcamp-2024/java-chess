package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class KnightTest {
    static Stream<Arguments> provideKnightMoves() {
        return Stream.of(
                Arguments.of("b1", "c3", true),
                Arguments.of("b1", "a3", true),
                Arguments.of("b1", "b3", false)
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, destination={1}, expected={2}")
    @MethodSource("provideKnightMoves")
    @DisplayName("Test Knight Moves")
    void testKnightMoves(String source, String destination, boolean expected) {
        Piece knight = PieceFactory.createWhiteKnight();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition destPos = new BoardPosition(destination);
        MoveVector moveVector = new MoveVector(sourcePos, destPos);
        assertThat(knight.verifyMovePosition(moveVector)).isEqualTo(expected);
    }
}
