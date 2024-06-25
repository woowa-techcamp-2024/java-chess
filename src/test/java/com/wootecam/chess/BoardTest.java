package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.pieces.Pawn;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class add_메소드는 {

        @Nested
        class 보드에_폰을_추가하면 {

            @ParameterizedTest
            @MethodSource("generatePawns")
            void 순서가_보장된다(List<Pawn> pawns, int pawnIndex, int size) {
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
                        Arguments.of(List.of(new Pawn(Pawn.COLOR_WHITE, Pawn.WHITE_REPRESENTATION)), 0, 1),
                        Arguments.of(List.of(new Pawn(Pawn.COLOR_BLACK, Pawn.BLACK_REPRESENTATION),
                                new Pawn(Pawn.COLOR_WHITE, Pawn.WHITE_REPRESENTATION)), 0, 2)
                );
            }
        }
    }

    @Nested
    class findPawn_메소드는 {

        @Nested
        class 폰의_갯수보다_크거나_0미만의_인덱스를_입력하면 {

            @ParameterizedTest
            @ValueSource(ints = {-1, 2})
            void 예외가_발생한다(int invalidIndex) {
                // given
                Pawn pawn = new Pawn();
                board.add(pawn);

                // expect
                assertThatThrownBy(() -> board.findPawn(invalidIndex))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰 인덱스는 0미만이거나 폰의 개수보다 크거나 같을 수 없습니다. size = " + board.size());
            }
        }
    }

    @Nested
    class initialize_메소드는 {

        @Nested
        class 보드를_생성하고_호출하면 {

            private Board board;

            @BeforeEach
            void setUp() {
                board = new Board();
            }

            @Test
            void 흰색_검정색_폰의_갯수를_각각_8개로_초기화한다() {
                // when
                board.initialize();

                // then
                assertAll(
                        () -> assertThat(board.getBlackPawnsResults()).isEqualTo("PPPPPPPP"),
                        () -> assertThat(board.getWhitePawnsResults()).isEqualTo("pppppppp")
                );
            }
        }
    }
}
