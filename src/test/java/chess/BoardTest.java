package chess;

import chess.pieces.Pawn;
import chess.pieces.enums.Color;
import chess.pieces.values.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("폰을 체스 판에 추가한다")
    void create(Pawn pawn, Location location) {
        board.addPiece(pawn, location);
        assertThat(board.size()).isOne();
        assertThat(board.getPiece(location)).hasValue(pawn);
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of(new Pawn(Color.WHITE), Location.of('a', 2)),
                Arguments.of(new Pawn(Color.BLACK), Location.of('b', 4))
        );
    }
}