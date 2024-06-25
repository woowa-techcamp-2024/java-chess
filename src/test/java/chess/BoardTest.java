package chess;

import chess.pieces.Piece;
import chess.pieces.Rook;
import chess.pieces.enums.Color;
import chess.pieces.enums.Symbol;
import chess.pieces.enums.Type;
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
        board.initialize();
        var actualResult = board.getPiece("a1");

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
        board.addPiece(Location.from("a1"), Piece.createRook(Color.WHITE));
        board.addPiece(Location.from("b1"), Piece.createKnight(Color.WHITE));
        board.addPiece(Location.from("c3"), Piece.createPawn(Color.WHITE));
        board.addPiece(Location.from("c4"), Piece.createPawn(Color.WHITE));
        board.addPiece(Location.from("c5"), Piece.createPawn(Color.WHITE));
        var actualWhiteScore = board.calculateScoreByColor(Color.WHITE);

        assertThat(actualWhiteScore).isEqualTo(9);
    }

    private static Stream<Arguments> findPieceWithTypeAndColor() {
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