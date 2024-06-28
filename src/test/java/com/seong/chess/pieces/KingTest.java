package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 다음의 위치로 움직일 수 있다.")
    void kingCanMove() {
        //given
        King king = King.createWhite();
        String sourcePosition = "d3";

        //when
        List<Position> positions = king.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "d4", "d2", "c3", "e3", "e4", "e2", "c4", "c2"
                );
    }
}
