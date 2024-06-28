package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("비숍은 다음 위치로 움직일 수 있다.")
    void bishopMove() {
        //given
        Bishop bishop = Bishop.createBlack();
        String sourcePosition = "d5";

        //when
        List<Position> positions = bishop.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "a2", "b3", "c4", "e4", "f3", "g2", "h1",
                        "c6", "b7", "a8", "e6", "f7", "g8"
                );
    }
}
