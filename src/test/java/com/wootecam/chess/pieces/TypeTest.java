package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @CsvSource(textBlock = """
            1, 1.0
            2, 1.0
            """)
    void 폰의_갯수에_따른_점수를_계산할_수_있다(int pawnCount, double expected) {
        // when
        double pawnPoint = Type.getPawnPoint(pawnCount);

        // then
        assertThat(pawnPoint).isEqualTo(expected);
    }
}
