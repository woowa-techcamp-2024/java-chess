package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        Pawn pawn = new Pawn("white");
        assertThat(pawn.getColor()).isEqualTo("white");
    }

    @Test
    @DisplayName("검은색 폰이 생성되어야 한다")
    public void createBlack() {
        Pawn pawn = new Pawn("black");
        assertThat(pawn.getColor()).isEqualTo("black");
    }
}