package wootecamp.chess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.pieces.Piece;
import wootecamp.chess.pieces.PieceFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class GameTest {
    private Board board;
    private Game game;

    @BeforeEach
    void init() {
        board = new Board();
        game = new Game(board);
    }

    static Stream<Arguments> provideValidMoves() {
        return Stream.of(
                Arguments.of("e2", "e4"), // Pawn move
                Arguments.of("g1", "f3"), // Knight move
                Arguments.of("b1", "c3")  // Knight move
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, target={1}")
    @MethodSource("provideValidMoves")
    @DisplayName("Test valid moves")
    void testValidMoves(String source, String target) {
        game.start();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition targetPos = new BoardPosition(target);

        Piece sourcePiece = board.findPiece(sourcePos);
        game.move(sourcePos, targetPos);

        assertThat(board.findPiece(targetPos)).isEqualTo(sourcePiece);
        assertThat(board.findPiece(sourcePos)).isEqualTo(PieceFactory.createEmptyPiece());
    }

    static Stream<Arguments> provideInvalidMoves() {
        return Stream.of(
                Arguments.of("e2", "e5"), // Invalid Pawn move
                Arguments.of("b1", "b3"), // Invalid Knight move
                Arguments.of("e1", "e2")  ,// 다른 아군 기물이 있는 위치로의 이동 테스트
                Arguments.of("a1", "a6")  // 점프 불가능한 기물의 점프 테스트
        );
    }

    @ParameterizedTest(name = "{index} => source={0}, target={1}")
    @MethodSource("provideInvalidMoves")
    @DisplayName("Test invalid moves")
    void testInvalidMoves(String source, String target) {
        game.start();

        BoardPosition sourcePos = new BoardPosition(source);
        BoardPosition targetPos = new BoardPosition(target);

        Piece sourcePiece = board.findPiece(sourcePos);
        Piece targetPiece = board.findPiece(targetPos);

        assertThatThrownBy(() -> game.move(sourcePos, targetPos)).isInstanceOf(RuntimeException.class);
        assertThat(board.findPiece(sourcePos)).isEqualTo(sourcePiece);
        assertThat(board.findPiece(targetPos)).isEqualTo(targetPiece);
    }
}
