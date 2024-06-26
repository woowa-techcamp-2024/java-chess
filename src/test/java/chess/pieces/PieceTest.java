package chess.pieces;

import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PieceTest {

    @Test
    @DisplayName("Blank를 생성한다.")
    void getBlank() {
        var actualResult = Piece.getBlank();

        assertThat(actualResult).isInstanceOf(Blank.class);
        assertThat(actualResult.getColor()).isNull();
        assertThat(actualResult.getSymbol()).isEqualTo(Symbol.BLANK);

        assertThatThrownBy(actualResult::isBlack).isInstanceOf(RuntimeException.class).hasMessage("Blank Does not have Color");
        assertThatThrownBy(actualResult::isWhite).isInstanceOf(RuntimeException.class).hasMessage("Blank Does not have Color");
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("폰을 생성한다.")
    void createPawn(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_PAWN : Symbol.WHITE_PAWN;
        var actualResult = Piece.createPawn(color);

        assertThat(actualResult).isInstanceOf(Pawn.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("비숍을 생성한다.")
    void createBishop(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_BISHOP : Symbol.WHITE_BISHOP;
        var actualResult = Piece.createBishop(color);

        assertThat(actualResult).isInstanceOf(Bishop.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("나이트를 생성한다.")
    void createKnight(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_KNIGHT : Symbol.WHITE_KNIGHT;
        var actualResult = Piece.createKnight(color);

        assertThat(actualResult).isInstanceOf(Knight.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("룩을 생성한다.")
    void createRook(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_ROOK : Symbol.WHITE_ROOK;
        var actualResult = Piece.createRook(color);

        assertThat(actualResult).isInstanceOf(Rook.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("퀸을 생성한다.")
    void createQueen(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_QUEEN : Symbol.WHITE_QUEEN;
        var actualResult = Piece.createQueen(color);

        assertThat(actualResult).isInstanceOf(Queen.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }

    @MethodSource("colorValue")
    @ParameterizedTest
    @DisplayName("킹을 생성한다.")
    void createKing(Color color) {
        var symbol = color.equals(Color.BLACK) ? Symbol.BLACK_KING : Symbol.WHITE_KING;
        var actualResult = Piece.createKing(color);

        assertThat(actualResult).isInstanceOf(King.class)
                .extracting("color", "symbol")
                .containsExactly(color, symbol);
    }


    @MethodSource("validateColor")
    @ParameterizedTest
    @DisplayName("검정색 기물을 구분한다.")
    void validateColorIsBlack(Piece piece, boolean result) {
        assertThat(piece.isBlack()).isEqualTo(result);
    }

    @MethodSource("validateColor")
    @ParameterizedTest
    @DisplayName("흰색 기물을 구분한다.")
    void validateColorIsWhite(Piece piece, boolean result) {
        assertThat(piece.isWhite()).isEqualTo(!result);
    }

    private static Stream<Arguments> validateColor() {
        return Stream.of(
                Arguments.of(Piece.createBishop(Color.BLACK), true),
                Arguments.of(Piece.createPawn(Color.WHITE), false),
                Arguments.of(Piece.createKnight(Color.BLACK), true),
                Arguments.of(Piece.createRook(Color.WHITE), false),
                Arguments.of(Piece.createQueen(Color.BLACK), true),
                Arguments.of(Piece.createKing(Color.WHITE), false)
        );
    }

    private static Stream<Arguments> colorValue() {
        return Stream.of(
                Arguments.of(Color.BLACK),
                Arguments.of(Color.WHITE)
        );
    }

}