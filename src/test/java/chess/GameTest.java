package chess;

import chess.board.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void test_successful_move_when_path_is_clear() {
        // Given
        Game game = new Game();
        game.newGame();
        String sourcePosition = "e2";
        String targetPosition = "e4";

        // When
        game.move(sourcePosition, targetPosition);

        // Then
        assertEquals(".", game.findPiece(sourcePosition).getSymbol());
        assertEquals("♙", game.findPiece(targetPosition).getSymbol());
    }

    @Test
    public void test_move_from_blank_position_throws_exception() {
        // Given
        Game game = new Game();
        game.newGame();
        String sourcePosition = "e3";
        String targetPosition = "e4";

        // When
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(sourcePosition, targetPosition);
        });
    }

    @Test
    public void test_move_to_same_color_position_throws_exception() {
        // Given
        Game game = new Game();
        game.newGame();
        String sourcePosition = "e2";
        String targetPosition = "e1";

        // When
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(sourcePosition, targetPosition);
        });
    }
}