package wootecamp.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.board.MoveVector;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BishopTest {
    static Stream<Arguments> provideBishopMoves() {
        return Stream.of(
                Arguments.of("c1", "e3", true),
                Arguments.of("c1", "a3", true),
                Arguments.of("c1", "c3", false),
                Arguments.of("c1", "e1", false)
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, target={1}, expected={2}")
    @MethodSource("provideBishopMoves")
    @DisplayName("Test Bishop Moves")
    void testBishopMoves(String source, String target, boolean expected) {
        Piece bishop = PieceFactory.createWhiteBishop();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition destPos = new BoardPosition(target);
        MoveVector moveVector = new MoveVector(sourcePos, destPos);
        assertThat(bishop.verifyMovePosition(moveVector)).isEqualTo(expected);
    }
}
