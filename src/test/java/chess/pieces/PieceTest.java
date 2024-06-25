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
    void createBlank() {
        var actualResult = Piece.createBlank();

        assertThat(actualResult).isInstanceOf(Blank.class);
        assertThat(actualResult.getColor()).isNull();
        assertThat(actualResult.getSymbol()).isEqualTo(Symbol.BLANK);

        assertThatThrownBy(actualResult::isBlack).isInstanceOf(RuntimeException.class).hasMessage("Blank Does not have Color");
        assertThatThrownBy(actualResult::isWhite).isInstanceOf(RuntimeException.class).hasMessage("Blank Does not have Color");
    }

    @Test
    @DisplayName("폰을 생성한다.")
    void createPawn() {
        var blackPawn = Piece.createBlackPawn();
        var whitePawn = Piece.createWhitePawn();

        assertThat(blackPawn).isInstanceOf(Pawn.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_PAWN);
        assertThat(whitePawn).isInstanceOf(Pawn.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_PAWN);
    }


    @Test
    @DisplayName("룩을 생성한다.")
    void createRook() {
        var blackRook = Piece.createBlackRook();
        var whiteRook = Piece.createWhiteRook();

        assertThat(blackRook).isInstanceOf(Rook.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_ROOK);
        assertThat(whiteRook).isInstanceOf(Rook.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_ROOK);
    }

    @Test
    @DisplayName("비숍을 생성한다.")
    void createBishop() {
        var blackBishop = Piece.createBlackBishop();
        var whiteBishop = Piece.createWhiteBishop();

        assertThat(blackBishop).isInstanceOf(Bishop.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_BISHOP);
        assertThat(whiteBishop).isInstanceOf(Bishop.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_BISHOP);
    }

    @Test
    @DisplayName("나이트를 생성한다.")
    void createKnight() {
        var blackKnight = Piece.createBlackKnight();
        var whiteKnight = Piece.createWhiteKnight();

        assertThat(blackKnight).isInstanceOf(Knight.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_KNIGHT);
        assertThat(whiteKnight).isInstanceOf(Knight.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_KNIGHT);
    }

    @Test
    @DisplayName("퀸을 생성한다.")
    void createQueen() {
        var blackQueen = Piece.createBlackQueen();
        var whiteQueen = Piece.createWhiteQueen();

        assertThat(blackQueen).isInstanceOf(Queen.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_QUEEN);
        assertThat(whiteQueen).isInstanceOf(Queen.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_QUEEN);
    }

    @Test
    @DisplayName("킹을 생성한다.")
    void createKing() {
        var blackKing = Piece.createBlackKing();
        var whiteKing = Piece.createWhiteKing();

        assertThat(blackKing).isInstanceOf(King.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_KING);
        assertThat(whiteKing).isInstanceOf(King.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_KING);
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
                Arguments.of(Piece.createBlackPawn(), true),
                Arguments.of(Piece.createWhitePawn(), false),
                Arguments.of(Piece.createBlackRook(), true),
                Arguments.of(Piece.createWhiteRook(), false),
                Arguments.of(Piece.createBlackBishop(), true),
                Arguments.of(Piece.createWhiteBishop(), false),
                Arguments.of(Piece.createBlackKnight(), true),
                Arguments.of(Piece.createWhiteKnight(), false),
                Arguments.of(Piece.createBlackQueen(), true),
                Arguments.of(Piece.createWhiteQueen(), false),
                Arguments.of(Piece.createBlackKing(), true),
                Arguments.of(Piece.createWhiteKing(), false)
        );
    }

}