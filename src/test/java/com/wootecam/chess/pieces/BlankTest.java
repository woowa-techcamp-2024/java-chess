package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class BlankTest {

    @Test
    void 빈칸에서_방향을_찾고자_한다면_예외가_발생한다() {
        // given
        Blank blank = new Blank();

        // expect
        assertThatThrownBy(() -> blank.findDirection(new Position(0, 0), new Position(0, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("빈칸은 방향이 없습니다.");
    }

}
