package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    void 폰이_움직이는_방향에_다른_위치를_찾는다() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        // when
        Direction direction1 = pawn.findDirection(new Position(1, 1), new Position(2, 1)).get();
        Direction direction2 = pawn.findDirection(new Position(1, 1), new Position(2, 2)).get();

        // then
        Assertions.assertAll(
                () -> assertThat(direction1).isEqualTo(Direction.SOUTH),
                () -> assertThat(direction2).isEqualTo(Direction.SOUTHEAST)
        );
    }

}
