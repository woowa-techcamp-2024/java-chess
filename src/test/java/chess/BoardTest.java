package chess;

import chess.pieces.Pawn;
import chess.pieces.enums.Color;
import chess.pieces.values.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        assertThat(board.getPiece(location)).isEqualTo(pawn);
    }

    @Test
    @DisplayName("체스 판을 초기화한다.")
    void initialization() {
        var expectedBoard = """
                □□□□□□□□
                ♟♟♟♟♟♟♟♟
                □□□□□□□□
                □□□□□□□□
                □□□□□□□□
                □□□□□□□□
                ♙♙♙♙♙♙♙♙
                □□□□□□□□
                """;
        board.initialize();

        assertThat(board.size()).isEqualTo(16);
        assertThat(board.printRow(7)).isEqualTo("♟♟♟♟♟♟♟♟");
        assertThat(board.printRow(2)).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(board.print()).isEqualTo(expectedBoard);
    }

    private static Stream<Arguments> create() {
        return Stream.of(
                Arguments.of(new Pawn(Color.WHITE), Location.of(2, 'a')),
                Arguments.of(new Pawn(Color.BLACK), Location.of(4, 'b'))
        );
    }
}