package com.seong.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import com.seong.chess.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Nested
    @DisplayName("폰은 다음 위치로 이동할 수 있다.")
    class PawnMoveSourceToTarget {

        @Test
        @DisplayName("초기 상태라면 2칸까지 이동할 수 있다.")
        void pawnMoveAtInitialState() {
            //given
            Pawn pawn = Pawn.createWhite();
            String sourcePosition = "f2";

            //when
            List<Position> positions = pawn.findMovablePosition(sourcePosition);

            //then
            assertThat(positions).map(Position::convert)
                    .containsExactlyInAnyOrder("f4", "f3");
        }

        @Test
        @DisplayName("초기 상태가 아니라면 1칸만 이동가능하다.")
        void pawnIsNotInitialState() {
            //given
            Pawn pawn = Pawn.createWhite();
            String sourcePosition = "f3";

            //when
            List<Position> positions = pawn.findMovablePosition(sourcePosition);

            //then
            assertThat(positions).map(Position::convert)
                    .containsExactlyInAnyOrder("f4");
        }
    }
}