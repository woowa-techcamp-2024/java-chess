package chess;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("체스 판을 초기화한다.")
    void initialization() {
        var expectedBoard = """
            ♜♞♝♛♚♝♞♜
            ♟♟♟♟♟♟♟♟
            □□□□□□□□
            □□□□□□□□
            □□□□□□□□
            □□□□□□□□
            ♙♙♙♙♙♙♙♙
            ♖♘♗♕♔♗♘♖
            """;
        board.initialize();

        assertThat(board.size()).isEqualTo(32);
        assertThat(board.print()).isEqualTo(expectedBoard);
    }

    @Test
    @DisplayName("체스 판에서 말을 찾는다.")
    void getPiece() {
        var expectedLocationStr = "a1";
        board.addPiece(Location.from(expectedLocationStr),
            Piece.generatePiece(Type.ROOK, Color.WHITE));
        var actualResult = board.getPiece(expectedLocationStr);

        assertThat(actualResult).isInstanceOf(Rook.class)
            .extracting("color", "symbol")
            .containsExactly(Color.WHITE, Symbol.WHITE_ROOK);
    }

    @MethodSource
    @ParameterizedTest
    @DisplayName("체스 판에서 타입과 색에 맞는 말을 찾는다.")
    void findPieceWithTypeAndColor(Type expectedType, Color expectedColor, int expectedResult) {
        board.initialize();

        var actualResult = board.countPiecesByTypeAndColor(expectedType, expectedColor);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("체스 판에서 말의 점수를 계산한다.")
    void calculateScoreByColor() {
        board.addPiece(Location.from("a1"), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.from("b1"), Piece.generatePiece(Type.KNIGHT, Color.WHITE));
        board.addPiece(Location.from("c3"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c4"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c5"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        var actualWhiteScore = board.calculateScoreByColor(Color.WHITE);

        assertThat(actualWhiteScore).isEqualTo(9);
    }

    @Test
    @DisplayName("체스 판에서 색에 맞는 말을 정렬한다.")
    void getPiecesByColor() {
        board.addPiece(Location.from("a1"), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.from("b1"), Piece.generatePiece(Type.KNIGHT, Color.WHITE));
        board.addPiece(Location.from("c3"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c4"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c5"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        var actualWhitePieces = board.getPiecesByColor(Color.WHITE);

        assertThat(actualWhitePieces).extracting("symbol")
            .containsExactly(Symbol.WHITE_ROOK, Symbol.WHITE_KNIGHT, Symbol.WHITE_PAWN,
                Symbol.WHITE_PAWN, Symbol.WHITE_PAWN);
    }

    @Test
    @DisplayName("체스 판에서 색에 맞는 말을 역순으로 정렬한다.")
    void getPiecesByColorReverse() {
        board.addPiece(Location.from("a1"), Piece.generatePiece(Type.ROOK, Color.WHITE));
        board.addPiece(Location.from("b1"), Piece.generatePiece(Type.KNIGHT, Color.WHITE));
        board.addPiece(Location.from("c3"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c4"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        board.addPiece(Location.from("c5"), Piece.generatePiece(Type.PAWN, Color.WHITE));
        var actualWhitePieces = board.getPiecesByColor(Color.WHITE, true);

        assertThat(actualWhitePieces).extracting("symbol")
            .containsExactly(Symbol.WHITE_PAWN, Symbol.WHITE_PAWN, Symbol.WHITE_PAWN,
                Symbol.WHITE_KNIGHT, Symbol.WHITE_ROOK);
    }

    static Stream<Arguments> findPieceWithTypeAndColor() {
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