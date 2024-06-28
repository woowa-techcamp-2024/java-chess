package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Piece;
import com.example.demo.piece.Queen;
import com.example.demo.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class GameTest {

    private void synTurn(Game game, Color color) {
        game.forceTurnColor(color);
    }

    @Nested
    @DisplayName("폰 규칙 테스트")
    class PawnRule {

        @ParameterizedTest
        @DisplayName("폰 이동 성공 테스트")
        @MethodSource("movePawnSuccessCase")
        public void movePawn(Location from, Location to) {
            // given
            Game game = Game.builder().build();
            game.initBoard();
            Piece target = game.getPiece(from);
            synTurn(game, target.getColor());

            // when
            game.handleMoveCommand(from, to);

            // then
            assertThat(game.getPiece(to)).isEqualTo(target);
            assertThat(game.getPiece(from)).isNull();
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
            Game game = Game.builder().build();
            game.initBoard();
            game.addPiece(Piece.builder(Type.PAWN).rank(Rank.THREE).file(File.A).build());
            synTurn(game, game.getPiece(from).getColor());

            // when : 모든 폰 경로 방향에 동일한 색상의 폰을 배치
            for (File file : File.values()) {
                game.addPiece(Piece.builder(Type.PAWN).color(Color.WHITE).rank(Rank.THREE).file(file).build());
                game.addPiece(Piece.builder(Type.PAWN).color(Color.BLACK).rank(Rank.SIX).file(file).build());
            }

            // then
            assertThatThrownBy(() -> game.handleMoveCommand(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        @ParameterizedTest
        @DisplayName("폰 이동 실패 테스트 : 초과한 이동을 할때")
        @MethodSource("movePawnFailCaseWhenExceed")
        public void movePawnFailWhenExceed(Location from, Location to) {
            // given
            Game game = Game.builder().build();
            game.initBoard();
            synTurn(game, game.getPiece(from).getColor());

            // then
            assertThatThrownBy(() -> game.handleMoveCommand(from, to))
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
            Game game = Game.builder().build();
            game.initBoard();
            synTurn(game, game.getPiece(first).getColor());
            game.handleMoveCommand(first, from);

            // then
            assertThatThrownBy(() -> {
                synTurn(game, game.getPiece(from).getColor());
                game.handleMoveCommand(from, to);
            })
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
            Piece bishop = Piece.builder(Type.BISHOP)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(bishop);

            // when
            game.handleMoveCommand(from, to);

            // then
            assertThat(game.getPiece(from)).isNull();
            assertThat(game.getPiece(to)).isEqualTo(bishop);
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
        @DisplayName("비숍 공격 테스트")
        @MethodSource("attackSuccessCase")
        public void attackBishop(Location from, Location to) {
            // given
            Piece bishop = Piece.builder(Type.BISHOP)
                    .rank(from.rank())
                    .file(from.file())
                    .build();

            Piece target = Piece.builder(Type.PAWN)
                    .color(Color.BLACK)
                    .rank(to.rank())
                    .file(to.file())
                    .build();

            Game game = Game.builder().build();
            game.addPiece(bishop);
            game.addPiece(target);

            // when
            game.handleMoveCommand(from, to);

            // then
            assertThat(game.getPiece(from)).isNull();
            assertThat(game.getPiece(to)).isEqualTo(bishop);
        }

        public static Stream<Arguments> attackSuccessCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.FIVE, File.C), new Location(Rank.THREE, File.E))
            );
        }

        @ParameterizedTest
        @DisplayName("비숍 이동 실패 테스트")
        @MethodSource("failCase")
        public void moveBishopFailTest(Location from, Location to) {
            // given
            Piece bishop = Piece.builder(Type.BISHOP)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(bishop);

            // when & then
            assertThatThrownBy(() -> game.handleMoveCommand(from, to))
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
            Piece rook = Piece.builder(Type.ROOK)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(rook);

            // when
            game.handleMoveCommand(from, to);

            // then
            assertThat(game.getPiece(from)).isNull();
            assertThat(game.getPiece(to)).isEqualTo(rook);
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
            Piece rook = Piece.builder(Type.ROOK)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(rook);

            // when & then
            assertThatThrownBy(() -> game.handleMoveCommand(from, to))
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

    @Nested
    @DisplayName("나이트 규칙 테스트")
    public class KnightRule {

        @ParameterizedTest
        @DisplayName("나이트 이동 성공 테스트")
        @MethodSource("successCase")
        public void moveKnight(Location from, Location to) {
            // given
            Piece knight = Piece.builder(Type.KNIGHT)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(knight);

            // when
            game.handleMoveCommand(from, to);

            // then
            assertThat(game.getPiece(from)).isNull();
            assertThat(game.getPiece(to)).isEqualTo(knight);
        }

        public static Stream<Arguments> successCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.FOUR, File.D), new Location(Rank.TWO, File.C)),
                    Arguments.of(new Location(Rank.FOUR, File.D), new Location(Rank.THREE, File.B)),
                    Arguments.of(new Location(Rank.FOUR, File.D), new Location(Rank.SIX, File.E)),
                    Arguments.of(new Location(Rank.FOUR, File.D), new Location(Rank.TWO, File.E))
            );
        }

        @ParameterizedTest
        @DisplayName("나이트 이동 실패 테스트")
        @MethodSource("failCase")
        public void moveKnightFailTest(Location from, Location to) {
            // given
            Piece knight = Piece.builder(Type.KNIGHT)
                    .rank(from.rank())
                    .file(from.file())
                    .build();
            Game game = Game.builder().build();
            game.addPiece(knight);

            // when & then
            assertThatThrownBy(() -> game.handleMoveCommand(from, to))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }

        public static Stream<Arguments> failCase() {
            return Stream.of(
                    Arguments.of(new Location(Rank.ONE, File.B), new Location(Rank.THREE, File.D)),
                    Arguments.of(new Location(Rank.ONE, File.G), new Location(Rank.FOUR, File.F)),
                    Arguments.of(new Location(Rank.EIGHT, File.B), new Location(Rank.SIX, File.D)),
                    Arguments.of(new Location(Rank.EIGHT, File.G), new Location(Rank.FIVE, File.F))
            );
        }
    }

    @Nested
    @DisplayName("같은 편은 공격할 수 없는 규칙 테스트")
    public class DoNotAttackSameColorPiece {

        @Test
        @DisplayName("비숍이 같은 편을 공격하면 실패한다.")
        public void bishopAttackSameColorPiece() {
            // given
            Piece bishop = Piece.builder(Type.BISHOP)
                    .rank(Rank.FIVE)
                    .file(File.C)
                    .build();
            Piece target = Piece.builder(Type.PAWN)
                    .rank(Rank.THREE)
                    .file(File.E)
                    .build();
            Game game = Game.builder().build();
            game.addPiece(bishop);
            game.addPiece(target);

            // when & then
            assertThatThrownBy(() -> game.handleMoveCommand(new Location(Rank.FIVE, File.C), new Location(Rank.THREE, File.E)))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("이동할 수 없습니다.");
        }
    }

    @Test
    @DisplayName("승진 규칙 테스트")
    public void promotionTest() {
        // given
        Piece pawn = Piece.builder(Type.PAWN)
                .color(Color.WHITE)
                .rank(Rank.SEVEN)
                .file(File.A)
                .build();

        Game game = Game.builder().build();
        game.setPiece(new Location(pawn.getRank(), pawn.getFile()), pawn);


        // when
        game.handleMoveCommand(new Location(Rank.SEVEN, File.A), new Location(Rank.EIGHT, File.A));
        game.promotion(Type.QUEEN);

        // then
        assertThat(game.getPiece(new Location(Rank.EIGHT, File.A))).isInstanceOf(Queen.class);
    }

    @ParameterizedTest
    @DisplayName("케슬링 동작 테스트")
    @MethodSource("castlingCase")
    public void castlingCase(
            Piece king,
            Piece rook,
            Piece enemy,
            Location kingTo,
            Location rookTo
    ) {
        // given
        Game game = Game.builder().build();
        game.setPiece(king.getRank(), king.getFile(), king);
        game.setPiece(rook.getRank(), rook.getFile(), rook);
        game.setPiece(enemy.getRank(), enemy.getFile(), enemy);
        game.calculateCheckPoint();
        synTurn(game, king.getColor());

        // when
        game.handleMoveCommand(king.getLocation(), kingTo);
//        game.handle(EventPublisher.INSTANCE.consume());

        // then
        assertThat(game.getPiece(kingTo)).isEqualTo(king);
        assertThat(game.getPiece(rookTo)).isEqualTo(rook);
    }

    public static Stream<Arguments> castlingCase() {
        return Stream.of(
                Arguments.of(
                        Piece.builder(Type.KING).color(Color.WHITE).rank(Rank.ONE).file(File.E).build(),
                        Piece.builder(Type.ROOK).color(Color.WHITE).rank(Rank.ONE).file(File.H).build(),
                        Piece.builder(Type.KNIGHT).color(Color.BLACK).rank(Rank.EIGHT).file(File.F).build(),
                        new Location(Rank.ONE, File.G),
                        new Location(Rank.ONE, File.F)
                ),
                Arguments.of(
                        Piece.builder(Type.KING).color(Color.BLACK).rank(Rank.EIGHT).file(File.E).build(),
                        Piece.builder(Type.ROOK).color(Color.BLACK).rank(Rank.EIGHT).file(File.A).build(),
                        Piece.builder(Type.KNIGHT).color(Color.WHITE).rank(Rank.ONE).file(File.F).build(),
                        new Location(Rank.EIGHT, File.C),
                        new Location(Rank.EIGHT, File.D)
                ),
                Arguments.of(
                        Piece.builder(Type.KING).color(Color.BLACK).rank(Rank.EIGHT).file(File.E).build(),
                        Piece.builder(Type.ROOK).color(Color.BLACK).rank(Rank.EIGHT).file(File.H).build(),
                        Piece.builder(Type.KNIGHT).color(Color.WHITE).rank(Rank.ONE).file(File.F).build(),
                        new Location(Rank.EIGHT, File.G),
                        new Location(Rank.EIGHT, File.F)
                ),
                Arguments.of(
                        Piece.builder(Type.KING).color(Color.WHITE).rank(Rank.ONE).file(File.E).build(),
                        Piece.builder(Type.ROOK).color(Color.WHITE).rank(Rank.ONE).file(File.A).build(),
                        Piece.builder(Type.KNIGHT).color(Color.BLACK).rank(Rank.EIGHT).file(File.F).build(),
                        new Location(Rank.ONE, File.C),
                        new Location(Rank.ONE, File.D)
                ));
    }

    @Test
    @DisplayName("앙파상 테스트")
    public void enPassantTest() {
        // given
        Piece pawn = Piece.builder(Type.PAWN)
                .color(Color.WHITE)
                .rank(Rank.FIVE)
                .file(File.B)
                .build();

        Piece enemy = Piece.builder(Type.PAWN)
                .color(Color.BLACK)
                .rank(Rank.SEVEN)
                .file(File.A)
                .build();

        Game game = Game.builder().build();
        game.setPiece(pawn.getRank(), pawn.getFile(), pawn);
        game.setPiece(enemy.getRank(), enemy.getFile(), enemy);
        synTurn(game, enemy.getColor());
        game.handleMoveCommand(Location.of("a7"), Location.of("a5"));
        game.calculateCheckPoint();
        System.out.println(game);

        // when
        game.handleMoveCommand(Location.of("b5"), Location.of("a6"));
        System.out.println(game);

        // then
        assertThat(game.getPiece(new Location(Rank.FIVE, File.A))).isNull();
        assertThat(game.getPiece(new Location(Rank.SIX, File.A))).isEqualTo(pawn);
    }
}
