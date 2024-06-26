package com.example.demo.context;

import com.example.demo.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
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
            board.addPiece(new Pawn(Rank.THREE, File.A));
            Game game = new Game(board);

            // when : 모든 폰 경로 방향에 동일한 색상의 폰을 배치
            for (File file : File.values()) {
                board.addPiece(new Pawn(Color.WHITE, Rank.THREE, file));
                board.addPiece(new Pawn(Color.BLACK, Rank.SIX, file));
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

        @ParameterizedTest
        @DisplayName("폰 이동 실패 테스트 : 처음 이동하는 폰이 아니라면 2칸 전진을 할 수 없다.")
        @MethodSource("movePawnFailCaseWhenNotFirstMove")
        public void movePawnFailWhenNotFirstMove(Location first, Location from, Location to) {
            // given
            Board board = createBoard();
            Game game = new Game(board);
            game.move(first, from);

            // then
            assertThatThrownBy(() -> game.move(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        public static Stream<Arguments> movePawnFailCaseWhenNotFirstMove() {
            return Stream.of(
                    Arguments.of(new Location(Rank.TWO, File.A), new Location(Rank.THREE, File.A), new Location(Rank.FIVE, File.A)),
                    Arguments.of(new Location(Rank.SEVEN, File.A), new Location(Rank.SIX, File.A), new Location(Rank.FOUR, File.A))
            );
        }
    }


    @Nested
    @DisplayName("비숍 규칙 테스트")
    public class BishopRule {

        @ParameterizedTest
        @DisplayName("비숍 이동 성공 테스트")
        @MethodSource("successCase")
        public void moveBishop(Location from, Location to) {
            // given
            Piece bishop = new Bishop(from.rank(), from.file());
            Board board = new Board();
            board.addPiece(bishop);
            Game game = new Game(board);

            // when
            game.move(from, to);

            // then
            assertThat(board.getPiece(from)).isNull();
            assertThat(board.getPiece(to)).isEqualTo(bishop);
        }

        public static Stream<Arguments> successCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.FIVE, File.C), new Location(Rank.THREE, File.A)),
                    Arguments.of(new Location(Rank.ONE, File.F), new Location(Rank.FOUR, File.C)),
                    Arguments.of(new Location(Rank.EIGHT, File.C), new Location(Rank.FIVE, File.F)),
                    Arguments.of(new Location(Rank.EIGHT, File.F), new Location(Rank.FIVE, File.C))
            );
        }

        @ParameterizedTest
        @DisplayName("비숍 이동 실패 테스트")
        @MethodSource("failCase")
        public void moveBishopFailTest(Location from, Location to) {
            // given
            Piece bishop = new Bishop(from.rank(), from.file());
            Board board = new Board();
            board.addPiece(bishop);
            Game game = new Game(board);

            // when & then
            assertThatThrownBy(() -> game.move(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        public static Stream<Arguments> failCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.FIVE, File.C), new Location(Rank.THREE, File.B)),
                    Arguments.of(new Location(Rank.ONE, File.F), new Location(Rank.FOUR, File.D)),
                    Arguments.of(new Location(Rank.EIGHT, File.C), new Location(Rank.FIVE, File.E)),
                    Arguments.of(new Location(Rank.EIGHT, File.F), new Location(Rank.FIVE, File.B))
            );
        }
    }

    @Nested
    @DisplayName("룩 규칙 테스트")
    public class RookRule {

        @ParameterizedTest
        @DisplayName("룩 이동 성공 테스트")
        @MethodSource("successCase")
        public void moveRook(Location from, Location to) {
            // given
            Piece rook = new Rook(from.rank(), from.file());
            Board board = new Board();
            board.addPiece(rook);
            Game game = new Game(board);

            // when
            game.move(from, to);

            // then
            assertThat(board.getPiece(from)).isNull();
            assertThat(board.getPiece(to)).isEqualTo(rook);
        }

        public static Stream<Arguments> successCase() {
            List<Arguments> cases = new ArrayList<>();
            for (File file : File.values()) {
                Rank sameRank = Rank.ONE;
                if (file != File.A)
                    cases.add(Arguments.of(new Location(sameRank, File.A), new Location(sameRank, file)));
            }
            for (Rank rank : Rank.values()) {
                File sameFile = File.A;
                if (rank != Rank.ONE)
                    cases.add(Arguments.of(new Location(Rank.ONE, sameFile), new Location(rank, sameFile)));
            }
            return cases.stream();
        }

        @ParameterizedTest
        @DisplayName("룩 이동 실패 테스트")
        @MethodSource("failCase")
        public void moveRookFailTest(Location from, Location to) {
            // given
            Piece rook = new Rook(from.rank(), from.file());
            Board board = new Board();
            board.addPiece(rook);
            Game game = new Game(board);

            // when & then
            assertThatThrownBy(() -> game.move(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        public static Stream<Arguments> failCase() {
            List<Arguments> cases = new ArrayList<>();
            for (File file : File.values()) {
                if (file != File.A)
                    cases.add(Arguments.of(new Location(Rank.ONE, File.A), new Location(Rank.TWO, file)));
            }
            for (Rank rank : Rank.values()) {
                if (rank != Rank.ONE)
                    cases.add(Arguments.of(new Location(Rank.ONE, File.A), new Location(rank, File.B)));
            }
            return cases.stream();
        }
    }
}