package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.pieces.Piece;
import chess.pieces.Rook;
import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import chess.pieces.enums.Type;
import chess.pieces.values.Location;
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

    void init() {
        board.addPiece(Location.from("a1"), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.from("b1"), Piece.generatePiece(Type.KNIGHT, Color.WHITE));
        board.addPiece(Location.from("c3"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c4"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c5"), Piece.generatePiece(Type.PAWN, Color.WHITE));
    }

    @Test
    @DisplayName("체스 판을 초기화한다.")
    void initialization() {
        // Act
        board.initialize();

        // Assert
        assertAll(
            () -> assertThat(board.size()).isEqualTo(32),
            () -> assertThat(board.getPiece(Location.from("a1"))).isInstanceOf(Rook.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_ROOK),
            () -> assertThat(board.getPiece(Location.from("b1"))).isInstanceOf(Piece.class)
                .extracting("color", "symbol")
                .containsExactly(Color.WHITE, Symbol.WHITE_KNIGHT),
            () -> assertThat(board.getPiece(Location.from("a8"))).isInstanceOf(Piece.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_ROOK),
            () -> assertThat(board.getPiece(Location.from("b8"))).isInstanceOf(Piece.class)
                .extracting("color", "symbol")
                .containsExactly(Color.BLACK, Symbol.BLACK_KNIGHT)
        );
    }

    @MethodSource("pieceProvider")
    @ParameterizedTest
    @DisplayName("체스 판에 말을 추가한다")
    void addPiece(Location location, Piece piece) {
        // Act
        board.addPiece(location, piece);

        // Assert
        assertAll(
            () -> assertThat(board.size()).isEqualTo(1),
            () -> assertThat(board.getPiece(location)).isEqualTo(piece)
        );
    }

    @MethodSource("pieceProvider")
    @ParameterizedTest
    @DisplayName("체스 판에서 말을 찾는다.")
    void getPiece(Location location, Piece piece) {
        // Arrange
        board.addPiece(location, piece);

        // Act
        var actualResult = board.getPiece(location);

        // Assert
        assertThat(actualResult).isEqualTo(piece);
    }

    @MethodSource("pieceProvider")
    @ParameterizedTest
    @DisplayName("체스 판에서 말을 제거한다.")
    void removePiece(Location location, Piece piece) {
        // Arrange
        board.addPiece(location, piece);

        // Act
        board.removePiece(location);

        // Assert
        assertAll(
            () -> assertThat(board.size()).isZero(),
            () -> assertThat(board.getPiece(location)).isEqualTo(Piece.getBlank())
        );
    }

    @Test
    @DisplayName("잘못된 위치의 말은 제거할 수 없다.")
    void removePieceWithInvalidLocation() {
        // Arrange
        var locationStr = "a1";
        var expectedLocation = Location.from(locationStr);

        // Act & Assert
        assertThatThrownBy(() -> board.removePiece(expectedLocation))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("location: " + locationStr + " does not have a piece.");
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("체스 판에서 타입과 색에 맞는 말의 갯수를 샌다..")
    void countPieceWithTypeAndColor(Type expectedType, Color expectedColor, int expectedResult) {
        board.initialize();

        var actualResult = board.countPiecesByTypeAndColor(expectedType, expectedColor);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("체스 판에서 Rank 정보를 찾는다.")
    void getPiecesWithRow() {
        board.initialize();

        var actualResult = board.getPiecesWithRow(1);
        assertThat(actualResult).extracting("symbol")
            .containsExactly(Symbol.WHITE_ROOK, Symbol.WHITE_KNIGHT, Symbol.WHITE_BISHOP,
                Symbol.WHITE_QUEEN, Symbol.WHITE_KING, Symbol.WHITE_BISHOP, Symbol.WHITE_KNIGHT,
                Symbol.WHITE_ROOK);
    }

    @Test
    @DisplayName("체스 판에서 색에 맞는 말을 정렬한다.")
    void getSortedPiecesByColor() {
        init();
        var actualWhitePieces = board.getSortedPiecesByColor(Color.WHITE);

        assertThat(actualWhitePieces).extracting("symbol")
            .containsExactly(Symbol.WHITE_ROOK, Symbol.WHITE_KNIGHT, Symbol.WHITE_PAWN,
                Symbol.WHITE_PAWN, Symbol.WHITE_PAWN);
    }

    @Test
    @DisplayName("체스 판에서 색에 맞는 말을 역순으로 정렬한다.")
    void getPiecesByColorReverse() {
        init();
        var actualWhitePieces = board.getSortedPiecesByColor(Color.WHITE, true);

        assertThat(actualWhitePieces).extracting("symbol")
            .containsExactly(Symbol.WHITE_PAWN, Symbol.WHITE_PAWN, Symbol.WHITE_PAWN,
                Symbol.WHITE_KNIGHT, Symbol.WHITE_ROOK);
    }

    @Test
    @DisplayName("체스 판에서 색이 일치하는 말들의 위치와 말을 갖고 온다.")
    void getLocationsAndPiecesByColor() {
        // Arrange
        init();
        // Act
        var actualResult = board.getLocationsAndPiecesByColor(Color.WHITE);
        // Assert
        assertThat(actualResult).hasSize(5);
    }

    @Test
    @DisplayName("체스 판에서 색과 타입이 일치하는 말들의 위치와 말을 갖고 온다.")
    void getLocationsAndPiecesByColorAndType() {
        // Arrange
        init();
        // Act
        var actualResult = board.getLocationsAndPiecesByColorAndType(Color.WHITE, Type.PAWN);
        // Assert
        assertThat(actualResult).hasSize(3);
    }

    static Stream<Arguments> pieceProvider() {
        return Stream.of(
            Arguments.of(Location.from("a1"), Piece.generatePiece(Type.ROOK, Color.WHITE)),
            Arguments.of(Location.from("b1"), Piece.generatePiece(Type.KNIGHT, Color.WHITE)),
            Arguments.of(Location.from("c3"), Piece.generatePiece(Type.PAWN, Color.WHITE)),
            Arguments.of(Location.from("c4"), Piece.generatePiece(Type.PAWN, Color.WHITE)),
            Arguments.of(Location.from("c5"), Piece.generatePiece(Type.PAWN, Color.WHITE))
        );
    }

    static Stream<Arguments> countPieceWithTypeAndColor() {
        return Stream.of(
            Arguments.of(Type.ROOK, Color.WHITE, 2),
            Arguments.of(Type.QUEEN, Color.WHITE, 1),
            Arguments.of(Type.KING, Color.BLACK, 1),
            Arguments.of(Type.KNIGHT, Color.BLACK, 2),
            Arguments.of(Type.PAWN, Color.BLACK, 8),
            Arguments.of(Type.BISHOP, Color.WHITE, 2)
        );
    }

}