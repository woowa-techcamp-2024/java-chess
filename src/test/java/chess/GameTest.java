package chess;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    @DisplayName("기물을 이동시킬 수 있다")
    public void test_successful_move_when_path_is_clear() {
        // Given
        Game game = new Game();
        game.newGame();
        Position sourcePosition = Position.from("e2");
        Position targetPosition = Position.from("e4");

        // When
        game.move(sourcePosition, targetPosition);

        // Then
        assertEquals(".", game.findPiece(sourcePosition).getSymbol());
        assertEquals("♙", game.findPiece(targetPosition).getSymbol());
    }

    @Test
    @DisplayName("기물을 이동시킬 수 있다2")
    public void test_successful_move_when_path_is_clear2() {
        // Given
        Game game = new Game();
        game.newGame();
        Position sourcePosition = Position.from("d7");
        Position targetPosition = Position.from("d6");

        // When
        game.move(sourcePosition, targetPosition);

        // Then
        assertEquals(".", game.findPiece(sourcePosition).getSymbol());
        assertEquals("♟", game.findPiece(targetPosition).getSymbol());
    }

    @Test
    @DisplayName("빈칸은 이동할 수 없다")
    public void test_move_from_blank_position_throws_exception() {
        // Given
        Game game = new Game();
        game.newGame();
        Position sourcePosition = Position.from("e3");
        Position targetPosition = Position.from("e4");

        // When
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(sourcePosition, targetPosition);
        });
    }

    @Test
    @DisplayName("같은 팀이 있는 곳으로는 이동할 수 없다")
    public void test_move_to_same_color_position_throws_exception() {
        // Given
        Game game = new Game();
        game.newGame();
        Position sourcePosition = Position.from("e2");
        Position targetPosition = Position.from("e1");

        // When
        assertThrows(IllegalArgumentException.class, () -> {
            game.move(sourcePosition, targetPosition);
        });
    }
}