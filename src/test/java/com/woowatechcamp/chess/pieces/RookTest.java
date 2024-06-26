package com.woowatechcamp.chess.pieces;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RookTest {
    @Test
    public void 룩이_직선으로_이동해야_한다() {
        Rook rook = new Rook(Piece.Color.WHITE, new Position("a1"));

        rook.move(new Position("a8")); // Valid vertical move
        assertThat(rook.getPosition()).isEqualTo(new Position("a8"));

        rook.move(new Position("h8")); // Valid horizontal move
        assertThat(rook.getPosition()).isEqualTo(new Position("h8"));

        assertThatThrownBy(() -> rook.move(new Position("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Rook can only move in straight lines.");
    }
}
