package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;

import java.awt.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RookTest {
    static Stream<Arguments> provideRookMoves() {
        return Stream.of(
                Arguments.of("a1", "a5", true),
                Arguments.of("a1", "e1", true),
                Arguments.of("a1", "e5", false)
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, destination={1}, expected={2}")
    @MethodSource("provideRookMoves")
    @DisplayName("Test Rook Moves")
    void testRookMoves(String source, String destination, boolean expected) {
        Piece rook = PieceFactory.createWhiteRook();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition destPos = new BoardPosition(destination);
        MoveVector moveVector = new MoveVector(sourcePos, destPos);
        assertThat(rook.verifyMovePosition(moveVector)).isEqualTo(expected);
    }
}
