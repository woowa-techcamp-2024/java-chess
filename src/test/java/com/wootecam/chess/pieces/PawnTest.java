package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @ParameterizedTest
    @CsvSource("""
            white, p
            black, P
            """
    )
    void 지정한_색상과_기물을_지정한_폰을_생성할_수_있다(String color, String representation) {
        // when
        Pawn pawn = new Pawn(color, representation);

        // then
        assertAll(
                () -> assertThat(pawn.getColor()).isEqualTo(color),
                () -> assertThat(pawn.getRepresentation()).isEqualTo(representation)
        );
    }

    @Test
    void create_기본생성자() {
        // when
        Pawn pawn = new Pawn();

        // then
        assertAll(
                () -> assertThat(pawn.getColor()).isEqualTo(Pawn.COLOR_WHITE),
                () -> assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION)
        );

    }
}
