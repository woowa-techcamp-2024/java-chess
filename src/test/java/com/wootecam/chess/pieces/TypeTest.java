package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class TypeTest {

    @Test
    void 색상에_따른_기물_표현을_찾을_수_있다() {
        // when
        String whitePawnRepresentation = Type.PAWN.findRepresentation(Color.WHITE);
        String blackPawnRepresentation = Type.PAWN.findRepresentation(Color.BLACK);

        // then
        assertAll(
                () -> assertThat(whitePawnRepresentation).isEqualTo("p"),
                () -> assertThat(blackPawnRepresentation).isEqualTo("P")
        );
    }
}