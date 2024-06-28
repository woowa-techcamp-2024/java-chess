package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @Test
    @DisplayName("룩은 다음 위치로 움직일 수 있다.")
    void rookMove() {
        //given
        Rook rook = Rook.createWhite();
        String sourcePosition = "d5";

        //when
        List<Position> positions = rook.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "a5", "b5", "c5", "e5", "f5", "g5", "h5",
                        "d1", "d2", "d3", "d4", "d6", "d7", "d8"
                );
    }
}
