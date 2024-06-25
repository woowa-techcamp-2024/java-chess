package org.example.pieces;
import org.example.chess.pieces.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chess.pieces.Pawn.BLACK_COLOR;
import static org.example.chess.pieces.Pawn.WHITE_COLOR;
import static org.junit.jupiter.api.Assertions.*;

class PawnTest {
    @Test
    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    public void create_기본생성자() {
        Pawn pawn = new Pawn();
        assertEquals(WHITE_COLOR,pawn.getColor());
        assertEquals(WHITE_REPRESENTATION,pawn.getRepresentation());
    }

    @Test
    @DisplayName("올바른 색의 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(BLACK_COLOR, BLACK_REPRESENTATION);
        verifyPawn(WHITE_COLOR, WHITE_REPRESENTATION);
    }

    private static void verifyPawn(final String color,final char representation) {
        Pawn pawn1 = new Pawn(color, representation);
        assertThat(pawn1.getColor()).isEqualTo(color);
        assertThat(pawn1.getRepresentation()).isEqualTo(representation);
    }
}