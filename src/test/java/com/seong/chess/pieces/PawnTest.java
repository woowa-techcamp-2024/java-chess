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
    public void create(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("흰색 폰이 기본으로 생성되어야 한다.")
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Piece.Colors.WHITE_COLOR);
        assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("다양한 색의 폰은 색에 맞는 문자를 출력한다.")
    public void create() {
        verifyPawn(Piece.Colors.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Piece.Colors.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}
