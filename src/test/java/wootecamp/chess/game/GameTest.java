package wootecamp.chess.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import wootecamp.chess.board.Board;
import wootecamp.chess.board.BoardPosition;
import wootecamp.chess.pieces.Piece;
import wootecamp.chess.pieces.PieceFactory;
import wootecamp.game.*;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameTest {
    private Board board;
    private GameInputManager gameInputManager;
    private TestGameOutputManager gameOutputManager;
    private Game game;

    private static class TestGameOutputManager implements GameOutputManager {
        int showErrorCall = 0;
        @Override
        public void showBoard(Board board) {

        }

        @Override
        public void showError(String message) {
            showErrorCall++;
        }
    }

    @BeforeEach
    void init() {
        board = new Board();
        gameInputManager = new ConsoleGameInputManager();
        gameOutputManager = new TestGameOutputManager();
        game = new Game(gameInputManager, gameOutputManager, board);
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
                Arguments.of("e1", "e2")  // Invalid King move at start
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
        game.move(sourcePos, targetPos);

        assertThat(gameOutputManager.showErrorCall).isEqualTo(1);
        assertThat(board.findPiece(sourcePos)).isEqualTo(sourcePiece);
        assertThat(board.findPiece(targetPos)).isEqualTo(targetPiece);
    }
}
