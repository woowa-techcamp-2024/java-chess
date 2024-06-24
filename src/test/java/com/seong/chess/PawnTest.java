package com.seong.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("흰색 폰이 생성되어야 한다")
    public void create() {
        String whiteColor = "white";
        String blackColor = "black";
        
        Pawn whitePawn = new Pawn(whiteColor);
        Pawn blackPawn = new Pawn(blackColor);

        assertThat(whitePawn.getColor()).isEqualTo("white");
        assertThat(blackPawn.getColor()).isEqualTo("black");
    }
}
