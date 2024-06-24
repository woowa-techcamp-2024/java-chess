package com.wootecam;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BoardTest {

    @ParameterizedTest
    @MethodSource("generatePawns")
    void 보드에_폰을_순서대로_추가할_수_있다(List<Pawn> pawns, int pawnIndex, int size) {
        // given
        Board board = new Board();

        // when
        pawns.forEach(board::add);
        Pawn findPawn = board.findPawn(pawnIndex);

        // then
        assertAll(
                () -> assertThat(board.size()).isEqualTo(size),
                () -> assertThat(findPawn).isEqualTo(pawns.get(pawnIndex))
        );
    }

    public static Stream<Arguments> generatePawns() {
        return Stream.of(
                Arguments.of(List.of(new Pawn(Pawn.COLOR_WHITE)), 0, 1),
                Arguments.of(List.of(new Pawn(Pawn.COLOR_BLACK), new Pawn(Pawn.COLOR_WHITE)), 0, 2)
        );
    }

    @Test
    void 보드에_추가된_폰의_갯수보다_큰_인덱스의_폰을_찾으면_예외가_발생한다() {
        // given
        Board board = new Board();
        Pawn pawn = new Pawn(Pawn.COLOR_WHITE);
        board.add(pawn);

        // expect
        assertThatThrownBy(() -> board.findPawn(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰의 개수보다 큰 인덱스 입니다. size = 1");
    }
}
