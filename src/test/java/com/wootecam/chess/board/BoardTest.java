package com.wootecam.chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 테스트")
class BoardTest {
    private Board board;

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
            var pawn = Piece.createBlackPawn();

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

    public static class PieceTypeConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            if (source instanceof String && PieceType.class.isAssignableFrom(targetType)) {
                return PieceType.valueOf((String) source);
            }
            throw new ArgumentConversionException("Conversion failed.");
        }
    }

    public static class ColorConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            if (source instanceof String && Color.class.isAssignableFrom(targetType)) {
                return Color.valueOf((String) source);
            }
            throw new ArgumentConversionException("Conversion failed.");
        }
    }

    @Nested
    class 기물_타입과_색으로_체스판에_존재하는_기물의_개수를_조회한다 {
        @BeforeEach
        void setUp() {
            board = createBoard();
        }

        @Test
        void 기물_타입과_색으로_체스판에_존재하는_해당_기물_개수를_조회할_수_있다() {
            board.add(Piece.createBlackPawn());
            board.add(Piece.createBlackPawn());
            board.add(Piece.createBlackPawn());
            board.add(Piece.createBlackPawn());
            board.add(Piece.createWhitePawn());

            assertThat(board.countPiece(PieceType.PAWN, Color.BLACK)).isEqualTo(4);
        }
    }

    @Nested
    class 체스판을_초기화한다 {

        @BeforeEach
        void setUp() {
            board = createBoard();
        }

        @Test
        void 체스판을_초기화하면_총_32개_기물들이_존재해야_한다() {
            board.initialize();

            assertThat(board.size()).isEqualTo(32);
        }

        @Test
        void 체스판을_초기화하면_모든_기물들이_제자리에_배치되어_있어야_한다() {
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

    @Nested
    class 주어진_위치의_기물을_조회한다 {

        @BeforeEach
        void setUp() {
            board = createBoard();
            board.initialize();
        }

        @CsvSource({
                "a8, ROOK, BLACK",
                "b7, PAWN, BLACK",
                "f2, PAWN, WHITE",
                "e1, KING, WHITE",
        })
        @ParameterizedTest
        void 주어진_위치의_기물을_조회할_수_있다(String position,
                                  @ConvertWith(PieceTypeConverter.class) PieceType type,
                                  @ConvertWith(ColorConverter.class) Color color) {
            Piece piece = board.get(position);

            assertAll(
                    () -> assertThat(piece.getType()).isEqualTo(type),
                    () -> assertThat(piece.getColor()).isEqualTo(color)
            );
        }

        @ValueSource(strings = {"a88", "i8", "a9"})
        @ParameterizedTest
        void 유효하지_않은_위치라면_예외가_발생한다(String position) {
            assertThatThrownBy(() -> board.get(position))
                    .isInstanceOf(IllegalArgumentException.class);
        }

    }
}
