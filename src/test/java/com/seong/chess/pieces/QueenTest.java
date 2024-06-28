package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸은 다음의 위치로 움직일 수 있다.")
    void queenMove() {
        //given
        Queen queen = Queen.createWhite();
        String sourcePosition = "d4";

        //when
        List<Position> positions = queen.findMovablePosition(sourcePosition);

        //then
        assertThat(positions).map(Position::convert)
                .containsExactlyInAnyOrder(
                        "d3", "d2", "d1", "d5", "d6", "d7", "d8",
                        "a4", "b4", "c4", "e4", "f4", "g4", "h4",
                        "a1", "b2", "c3", "e5", "f6", "g7", "h8",
                        "a7", "b6", "c5", "e3", "f2", "g1"
                );
    }
}
