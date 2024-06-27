package chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlankTest {

    @DisplayName("Blank은 이동할 수 없다.")
    @Test
    void verifyMoveCoordinate() {
        // given
        Blank blank = Blank.createBlank();

        // when
        boolean b = blank.verifyMoveCoordinate("a1", "a2");

        // then
        assertFalse(b);
    }

}