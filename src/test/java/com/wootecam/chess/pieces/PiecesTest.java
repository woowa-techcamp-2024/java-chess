package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PiecesTest {

    @ParameterizedTest
    @CsvSource("""
            white, p
            black, P
            """
    )
    void 지정한_색상과_기물을_지정한_폰을_생성할_수_있다(String color, String representation) {
        // when
        Piece piece = new Piece(color, representation);

        // then
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(color),
                () -> assertThat(piece.getRepresentation()).isEqualTo(representation)
        );
    }

    @Test
    void create_기본생성자() {
        // when
        Piece piece = new Piece(Piece.COLOR_WHITE, Piece.WHITE_PAWN_REPRESENTATION);

        // then
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(Piece.COLOR_WHITE),
                () -> assertThat(piece.getRepresentation()).isEqualTo(Piece.WHITE_PAWN_REPRESENTATION)
        );
    }
}
