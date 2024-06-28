package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트는 다음 위치로 움직일 수 있다.")
    void knightMove() {
        //given
        Knight knight = Knight.createBlack();
        String sourcePosition = "d4";

        //when
        List<Position> positions = knight.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "e6", "f5", "f3", "e2", "c2", "b3", "b5", "c6"
                );
    }
}
