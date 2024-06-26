package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import chess.pieces.enums.Type;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PieceTest {

    @Test
    @DisplayName("Blank를 생성한다.")
    void getBlank() {
        var actualResult = Piece.getBlank();

        assertThat(actualResult).isInstanceOf(Blank.class);
        assertThat(actualResult.getColor()).isEqualTo(Color.NONE);
        assertThat(actualResult.getSymbol()).isEqualTo(Symbol.BLANK);
    }

    @MethodSource("create")
    @ParameterizedTest
    @DisplayName("기물을 생성한다.")
    void create(Type type, Color color, Symbol symbol) {
        var actualResult = Piece.generatePiece(type, color);

        assertThat(actualResult).isInstanceOf(type.getClazz())
            .extracting("color", "symbol")
            .containsExactly(color, symbol);
    }

    private static Stream<Arguments> create() {
        return Stream.of(
            Arguments.of(Type.BISHOP, Color.BLACK, Symbol.BLACK_BISHOP),
            Arguments.of(Type.PAWN, Color.WHITE, Symbol.WHITE_PAWN),
            Arguments.of(Type.KNIGHT, Color.BLACK, Symbol.BLACK_KNIGHT),
            Arguments.of(Type.ROOK, Color.WHITE, Symbol.WHITE_ROOK),
            Arguments.of(Type.QUEEN, Color.BLACK, Symbol.BLACK_QUEEN),
            Arguments.of(Type.KING, Color.WHITE, Symbol.WHITE_KING)
        );
    }

}