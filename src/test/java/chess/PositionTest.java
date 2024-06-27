package chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.pieces.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("스트링으로부터 Position 타입 객체를 얻는다.")
    @Test
    void getPositionFromString() {
        Position pos = Position.fromString("h8");

        assertEquals(7, pos.getRankNumber());
        assertEquals(7, pos.getFileNumber());
    }

    @DisplayName("잘못된 스트링으로부터 Position 타입 객체를 얻는데 실패한다.")
    @Test
    void failToGetPositionFromString() {
        assertThrows(IllegalArgumentException.class,
                () -> Position.fromString("h9"));
    }
}
