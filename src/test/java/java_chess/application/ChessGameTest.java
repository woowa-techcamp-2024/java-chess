package java_chess.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import java_chess.chess.Board;
import java_chess.chess.pieces.Piece;
import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Type;
import java_chess.chess.pieces.values.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("현재 게임의 점수를 계산합니다.")
    void calculateScoreByColor() {
        board.addPiece(Location.of(1, 'a'), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.of(2, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.of(3, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.of(4, 'a'), Piece.generatePiece(Type.PAWN, Color.WHITE));

        // Act
        var actualResult = chessGame.calculateScoreByColor(Color.WHITE);

        // Assert
        assertThat(actualResult).isEqualTo(6.5);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("말 이동에 성공합니다.")
    void movePieceSuccess(String locationFrom, Piece piece, String LocationTo) {
        // Arrange
        board.addPiece(Location.from(locationFrom), piece);
        // Act
        chessGame.movePiece(Location.from(locationFrom), Location.from(LocationTo));

        // Assert
        assertAll(
            () -> assertThat(board.getPiece(Location.from(locationFrom))).isEqualTo(
                Piece.getBlank()),
            () -> assertThat(board.getPiece(Location.from(LocationTo))).isEqualTo(
                piece)
        );
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("말 이동에 실패합니다.")
    void movePieceFail(String locationFrom, Piece piece, String LocationTo) {
        // Arrange
        var from = Location.from(locationFrom);
        var to = Location.from(LocationTo);

        // Arrange
        board.addPiece(Location.from(locationFrom), piece);
        // Act & Assert
        assertThatThrownBy(
            () -> chessGame.movePiece(from, to))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("This piece cannot move to the location.");
    }

    static Stream<Arguments> movePieceSuccess() {
        return Stream.of(
            Arguments.of("a2", Piece.generatePiece(Type.PAWN, Color.WHITE), "a4"),
            Arguments.of("a2", Piece.generatePiece(Type.PAWN, Color.WHITE), "a3"),
            Arguments.of("a2", Piece.generatePiece(Type.ROOK, Color.WHITE), "h2"),
            Arguments.of("a2", Piece.generatePiece(Type.ROOK, Color.WHITE), "a8"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "a3"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "b4"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "d4"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "e3"),
            Arguments.of("c2", Piece.generatePiece(Type.BISHOP, Color.WHITE), "d3"),
            Arguments.of("c2", Piece.generatePiece(Type.BISHOP, Color.WHITE), "b3"),
            Arguments.of("c2", Piece.generatePiece(Type.QUEEN, Color.WHITE), "b1"),
            Arguments.of("c2", Piece.generatePiece(Type.QUEEN, Color.WHITE), "h2"),
            Arguments.of("c2", Piece.generatePiece(Type.KING, Color.WHITE), "b1")
        );
    }

    static Stream<Arguments> movePieceFail() {
        return Stream.of(
            Arguments.of("a2", Piece.generatePiece(Type.PAWN, Color.WHITE), "a5"),
            Arguments.of("a2", Piece.generatePiece(Type.PAWN, Color.WHITE), "b3"),
            Arguments.of("a2", Piece.generatePiece(Type.ROOK, Color.WHITE), "h3"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "a4"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "b5"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "d5"),
            Arguments.of("c2", Piece.generatePiece(Type.KNIGHT, Color.WHITE), "e4"),
            Arguments.of("c2", Piece.generatePiece(Type.BISHOP, Color.WHITE), "d4"),
            Arguments.of("c2", Piece.generatePiece(Type.BISHOP, Color.WHITE), "b4"),
            Arguments.of("c2", Piece.generatePiece(Type.QUEEN, Color.WHITE), "h3")
        );
    }


}