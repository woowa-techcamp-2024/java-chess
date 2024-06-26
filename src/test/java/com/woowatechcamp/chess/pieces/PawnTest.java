package com.woowatechcamp.chess.pieces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PawnTest {
    @Test
    public void 폰이_한_칸_앞으로_이동해야_한다() {
        Pawn pawn = new Pawn(Piece.Color.WHITE, new Position("a2"));

        pawn.move(new Position("a3")); // Valid move
        assertThat(pawn.getPosition()).isEqualTo(new Position("a3"));

        assertThatThrownBy(() -> pawn.move(new Position("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Pawn can only move forward one square.");
    }
}
