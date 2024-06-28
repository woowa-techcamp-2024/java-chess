package chess.pieces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.ChessGame.initializeCmdToPos;
import static chess.pieces.Piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KingTest {

    @BeforeAll
    static void setUp() {
        initializeCmdToPos();
    }
    @Test
    @DisplayName("king 움직임")
    void moveKing() {
        Piece king =  Piece.of(King.class, WHITE, "a1");
        king.move("a1", "a2");
        assertEquals(king.getPosition().toString(), "a2");
    }

}
