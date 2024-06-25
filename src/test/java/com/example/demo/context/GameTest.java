package com.example.demo.context;

import com.example.demo.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.demo.context.Board.Location;
import static com.example.demo.context.Board.createBoard;
import static org.assertj.core.api.Assertions.assertThat;


class GameTest {

    @Nested
    @DisplayName("폰 규칙 테스트")
    class PawnRule {

        @ParameterizedTest
        @DisplayName("폰 이동 성공 테스트")
        @MethodSource("movePawnSuccessCase")
        public void movePawn(Location from, Location to) {
            // given
            Board board = createBoard();
            Piece target = board.getPiece(from);
            Game game = new Game(board);

            // when
            game.move(from, to);

            // then
            assertThat(board.getPiece(to)).isEqualTo(target);
            assertThat(board.getPiece(from)).isNull();
        }

        public static Stream<Arguments> movePawnSuccessCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.TWO, File.A), new Location(Rank.FOUR, File.A)),
                    Arguments.of(new Location(Rank.TWO, File.A), new Location(Rank.THREE, File.A)),
                    Arguments.of(new Location(Rank.SEVEN, File.A), new Location(Rank.SIX, File.A)),
                    Arguments.of(new Location(Rank.SEVEN, File.A), new Location(Rank.FIVE, File.A))
            );
        }
    }
}