package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.enums.Color;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("폰을 체스 판에 추가한다")
    void create(Pawn pawn) {
        board.addPiece(pawn);
        assertThat(board.size()).isOne();
        assertThat(board.findPawn(0)).isEqualTo(pawn);
    }

    @Test
    @DisplayName("폰이 아닌 말은 findPawn으로 찾을 수 없다")
    void findPawnFail() {
        var piece = new Piece() {
        };
        board.addPiece(piece);
        assertThatThrownBy(() -> board.findPawn(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Piece is not a Pawn");
    }

    private static Stream<Arguments> create() {
        return Stream.of(
            Arguments.of(new Pawn(Color.WHITE)),
            Arguments.of(new Pawn(Color.BLACK))
        );
    }
}