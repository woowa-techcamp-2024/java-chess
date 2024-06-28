package chess;

import chess.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    @Test
    @DisplayName("입력된 좌표를 내부형태에 맞게 변환한다")
    void from() {
        Position position = Position.from("a1");
        assertEquals(Position.A1, position);
        assertEquals(0, position.getFile());
        assertEquals(7, position.getRank());

        Position position2 = Position.from("f6");
        assertEquals(Position.F6, position2);
        assertEquals(5, position2.getFile());
        assertEquals(2, position2.getRank());
    }
}
