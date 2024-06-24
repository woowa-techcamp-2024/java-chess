package com.seong.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    @ParameterizedTest
    @CsvSource({
            Piece.Colors.BLACK_COLOR, Piece.Colors.WHITE_COLOR
    })
    @DisplayName("다양한 색을 가진 폰이 생성되어야 한다.")
    public void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("흰색 폰이 기본으로 생성되어야 한다.")
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Piece.Colors.WHITE_COLOR);
    }
}
