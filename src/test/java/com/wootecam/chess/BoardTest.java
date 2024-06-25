package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 테스트")
class BoardTest {

    private static Board createBoard() {
        return new Board();
    }

    private static Board createBoard(List<Piece> pieces) {
        Board board = new Board();
        pieces.forEach(board::add);
        return board;
    }

    @Nested
    class 체스판에_말을_추가할_수_있다 {

        @Test
        void 말을_추가할_수_있다() {
            var board = createBoard();
            var pawn = new Piece(PieceType.PAWN);

            assertThatNoException().isThrownBy(() -> board.add(pawn));
        }
    }

    @Nested
    class 체스판에_추가된_말의_개수를_조회할_수_있다 {

        static Stream<Arguments> pawnListForSize() {
            return Stream.of(
                    Arguments.arguments(List.of(Piece.createWhitePawn())),
                    Arguments.arguments(
                            List.of(Piece.createWhitePawn(), Piece.createBlackPawn())),
                    Arguments.arguments(
                            List.of(Piece.createBlackPawn(), Piece.createBlackPawn(),
                                    Piece.createWhitePawn()))
            );
        }

        @MethodSource("pawnListForSize")
        @ParameterizedTest
        void 말의_개수를_조회할_수_있다(List<Piece> pieceList) {
            var board = createBoard(pieceList);

            assertThat(board.size()).isEqualTo(pieceList.size());
        }
    }

    @Nested
    class 추가된_순서로_말을_조회할_수_있다 {

        static Stream<Arguments> pawnListForFind() {
            return Stream.of(
                    Arguments.arguments(List.of(Piece.createWhitePawn())),
                    Arguments.arguments(List.of(Piece.createWhitePawn(), Piece.createBlackPawn())),
                    Arguments.arguments(
                            List.of(Piece.createBlackPawn(), Piece.createBlackPawn(), Piece.createWhitePawn())));
        }

        @MethodSource("pawnListForFind")
        @ParameterizedTest
        void 말을_조회할_수_있다(List<Piece> pieceList) {
            var board = createBoard(pieceList);

            for (int i = 0; i < pieceList.size(); ++i) {
                assertThat(board.findPawn(i)).isEqualTo(pieceList.get(i));
            }
        }

        @ValueSource(ints = {-1, 1})
        @ParameterizedTest(name = "{0}번째 말 조회")
        void 유효하지_않은_순서로_말을_조회하면_예외가_발생한다(int index) {
            var board = createBoard(List.of(Piece.createWhitePawn()));

            assertThatThrownBy(() -> board.findPawn(index))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 체스판을_초기화한다 {

        @Test
        void 체스판을_초기화하면_총_32개_기물들이_존재해야_한다() {
            var board = createBoard();

            board.initialize();

            assertThat(board.size()).isEqualTo(32);
        }
    }

    @Nested
    class 체스판에_존재하는_폰의_상태를_조회한다 {

        @Test
        void 체스판_초기화_시_검은색_폰은_열의_개수만큼_존재한다() {
            var board = createBoard();
            board.initialize();

            assertThat(board.getWhitePawnsResult()).isEqualTo("pppppppp");
        }

        @Test
        void 체스판_초기화_시_하얀색_폰은_열의_개수만큼_존재한다() {
            var board = createBoard();
            board.initialize();

            assertThat(board.getBlackPawnsResult()).isEqualTo("PPPPPPPP");
        }
    }

    @Nested
    class 체스판의_상태를_조회한다 {

        @Test
        void 체스판_초기화_시_모든_기물들이_제자리에_배치되어_있어야_한다() {
            var board = createBoard();
            board.initialize();

            assertThat(board.print()).isEqualTo("""
                    RKBQKBKR
                    PPPPPPPP
                    ........
                    ........
                    ........
                    ........
                    pppppppp
                    rkbqkbkr
                    """);
        }
    }
}
