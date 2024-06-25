package org.example.pieces;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    @DisplayName("올바른 색의 폰이 생성되어야 한다")
    public void create() {
        final String BLACK = "black";
        final String WHITE = "white";
        verifyPawn(BLACK);
        verifyPawn(WHITE);
    }

    @Test
    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        Assertions.assertEquals("white",pawn.getColor());
    }

    private static void verifyPawn(String color) {
        Pawn pawn1 = new Pawn(color);
        assertThat(pawn1.getColor()).isEqualTo(color);
    }
}