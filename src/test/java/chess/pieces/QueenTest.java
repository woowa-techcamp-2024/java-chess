package chess.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.ChessGame.initializeCmdToPos;
import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @BeforeAll
    static void setUp() {
        initializeCmdToPos();
    }

    @ParameterizedTest
    @DisplayName("Queen 기물의 이동 구현")
    @MethodSource("position")
    void moveQueen(String source, String target) {
        Piece queen = Piece.of(Queen.class, Piece.Color.WHITE, source);

        queen.move(queen.getPosition().toString(), target);
        assertEquals(queen.getPosition().toString(), target);
    }

    static Stream<Arguments> position() {
        return Stream.of(
                Arguments.arguments("a1", "a2")
        );
    }

}
