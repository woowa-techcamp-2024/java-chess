package com.woopaca.javachess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스 판 테스트")
public class BoardTest {

    @DisplayName("체스 판에 폰을 추가할 수 있다.")
    @Test
    void create() {
        Board board = new Board();

        Pawn whitePawn = new Pawn(Pawn.WHITE_COLOR);
        Pawn blackPawn = new Pawn(Pawn.BLACK_COLOR);

        board.add(whitePawn);
        assertThat(board.size()).isEqualTo(1);
        assertThat(board.findPawn(0)).isEqualTo(whitePawn);

        board.add(blackPawn);
        assertThat(board.size()).isEqualTo(2);
        assertThat(board.findPawn(1)).isEqualTo(blackPawn);
    }

}
