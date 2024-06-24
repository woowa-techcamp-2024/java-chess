package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.pieces.Pawn;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @ParameterizedTest
    @MethodSource("generatePawns")
    void 보드에_폰을_순서대로_추가할_수_있다(List<Pawn> pawns, int pawnIndex, int size) {
        // when
        pawns.forEach(board::add);
        Pawn findPawn = board.findPawn(pawnIndex);

        // then
        assertAll(
                () -> assertThat(board.size()).isEqualTo(size),
                () -> assertThat(findPawn).isEqualTo(pawns.get(pawnIndex))
        );
    }

    private static Stream<Arguments> generatePawns() {
        return Stream.of(
                Arguments.of(List.of(new Pawn(Pawn.COLOR_WHITE)), 0, 1),
                Arguments.of(List.of(new Pawn(Pawn.COLOR_BLACK), new Pawn(Pawn.COLOR_WHITE)), 0, 2)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2})
    void 폰의개수보다_큰_인덱스거나_0미만의_인덱스라면_예외가_발생한다(int index) {
        // given
        Pawn pawn = new Pawn(Pawn.COLOR_WHITE);
        board.add(pawn);

        // expect
        assertThatThrownBy(() -> board.findPawn(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰 인덱스는 0미만이거나 폰의 개수보다 크거나 같을 수 없습니다. size = " + board.size());
    }
}
