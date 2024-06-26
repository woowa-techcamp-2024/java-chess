package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Pawn;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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

        @ParameterizedTest
        @DisplayName("폰 이동 실패 테스트 : 이동 경로에 같은 편의 말이 존재할 때")
        @MethodSource("movePawnSuccessCase")
        public void movePawnFail(Location from, Location to) {
            // given
            Board board = createBoard();
            board.setPiece(Rank.THREE, File.A, new Pawn());
            Game game = new Game(board);

            // when : 모든 폰 경로 방향에 동일한 색상의 폰을 배치
            for(File file: File.values()){
                board.setPiece(Rank.THREE, file, new Pawn());
                board.setPiece(Rank.SIX, file, new Pawn(Color.BLACK));
            }

            // then
            assertThatThrownBy(() -> game.move(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        @ParameterizedTest
        @DisplayName("폰 이동 실패 테스트 : 초과한 이동을 할때")
        @MethodSource("movePawnFailCaseWhenExceed")
        public void movePawnFailWhenExceed(Location from, Location to) {
            // given
            Board board = createBoard();
            Game game = new Game(board);

            // then
            assertThatThrownBy(() -> game.move(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        public static Stream<Arguments> movePawnFailCaseWhenExceed() {
            return Stream.of(
                    Arguments.of(new Location(Rank.TWO, File.A), new Location(Rank.FIVE, File.A)),
                    Arguments.of(new Location(Rank.SEVEN, File.A), new Location(Rank.FOUR, File.B))
            );
        }
    }
}