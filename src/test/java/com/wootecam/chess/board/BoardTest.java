package com.wootecam.chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.common.Order;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.PieceType;
import java.util.List;
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
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("체스판 테스트")
class BoardTest {

    private Board board;

    private static Board createBoard() {
        return new Board();
    }

    private static Position createPosition(String position) {
        return new Position(position);
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
    class 체스판에_말을_배치할_수_있다 {

        @Test
        void 말을_배치할_수_있다() {
            var board = createBoard();
            var pawn = Piece.createBlackPawn();
            Position pos = new Position("a8");

            assertThatNoException().isThrownBy(() -> board.add(pawn, pos));
        }
    }

    @Nested
    class 체스판에_추가된_말의_개수를_조회할_수_있다 {

        @Test
        void 말의_개수를_조회할_수_있다() {
            var board = createBoard();
            board.add(Piece.createBlackPawn(), createPosition("e8"));
            board.add(Piece.createBlackPawn(), createPosition("h7"));
            board.add(Piece.createBlackPawn(), createPosition("a6"));
            board.add(Piece.createBlackPawn(), createPosition("a8"));

            assertThat(board.size()).isEqualTo(4);
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
            board.add(Piece.createBlackPawn(), createPosition("a8"));
            board.add(Piece.createBlackPawn(), createPosition("b8"));
            board.add(Piece.createBlackPawn(), createPosition("c4"));
            board.add(Piece.createBlackPawn(), createPosition("d3"));
            board.add(Piece.createWhitePawn(), createPosition("h6"));

            assertThat(board.countPiece(PieceType.PAWN, Color.BLACK)).isEqualTo(4);
            assertThat(board.countPiece(PieceType.PAWN, Color.WHITE)).isEqualTo(1);
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
            Piece piece = board.get(new Position(position));

            assertAll(
                    () -> assertThat(piece.getType()).isEqualTo(type),
                    () -> assertThat(piece.getColor()).isEqualTo(color)
            );
        }
    }

    @Nested
    class 기물의_점수를_계산한다 {

        @BeforeEach
        void setUp() {
            board = createBoard();
        }

        @Test
        void 기물의_점수를_계산할_수_있다() {
            board.add(Piece.createBlackPawn(), createPosition("a8"));
            board.add(Piece.createBlackPawn(), createPosition("a7"));
            board.add(Piece.createWhitePawn(), createPosition("a4"));
            board.add(Piece.createBlackKnight(), createPosition("b5"));
            board.add(Piece.createBlackRook(), createPosition("c5"));
            board.add(Piece.createBlackBishop(), createPosition("d5"));
            board.add(Piece.createBlackKing(), createPosition("e5"));
            board.add(Piece.createBlackQueen(), createPosition("f5"));

            double score = board.calculateScore(Color.BLACK);

            assertThat(score).isEqualTo(20.5);
        }
    }

    @Nested
    class 체스판에_존재하는_주어진_색상의_기물들을_점수순으로_정렬한다 {

        @BeforeEach
        void setUp() {
            board = createBoard();
        }

        @Test
        void 주어진_색상의_기물들을_점수_오름차순으로_정렬한다() {
            board.add(Piece.createBlackPawn(), createPosition("a8"));
            board.add(Piece.createBlackPawn(), createPosition("a7"));
            board.add(Piece.createWhitePawn(), createPosition("a4"));
            board.add(Piece.createBlackKnight(), createPosition("b5"));
            board.add(Piece.createBlackRook(), createPosition("c5"));
            board.add(Piece.createBlackBishop(), createPosition("d5"));
            board.add(Piece.createBlackKing(), createPosition("e5"));
            board.add(Piece.createBlackQueen(), createPosition("f5"));

            List<Piece> result = board.getPiecesSortedByScore(Color.BLACK, Order.DESC);

            assertThat(result).containsExactly(
                    Piece.createBlackQueen(),
                    Piece.createBlackRook(),
                    Piece.createBlackBishop(),
                    Piece.createBlackKnight(),
                    Piece.createBlackPawn(),
                    Piece.createBlackPawn(),
                    Piece.createBlackKing());
        }

        @Test
        void 주어진_색상의_기물들을_점수_내림차순으로_정렬한다() {
            board.add(Piece.createBlackPawn(), createPosition("a8"));
            board.add(Piece.createWhitePawn(), createPosition("a7"));
            board.add(Piece.createWhitePawn(), createPosition("a4"));
            board.add(Piece.createWhiteKnight(), createPosition("b5"));
            board.add(Piece.createBlackRook(), createPosition("c5"));
            board.add(Piece.createWhiteBishop(), createPosition("d5"));
            board.add(Piece.createWhiteKing(), createPosition("e5"));
            board.add(Piece.createBlackQueen(), createPosition("f5"));

            List<Piece> result = board.getPiecesSortedByScore(Color.WHITE, Order.ASC);

            assertThat(result).containsExactly(
                    Piece.createWhiteKing(),
                    Piece.createWhitePawn(),
                    Piece.createWhitePawn(),
                    Piece.createWhiteKnight(),
                    Piece.createWhiteBishop());
        }
    }
}
