package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("기물 테스트")
class PieceTest {

    @Nested
    class 기물을_생성한다 {

        static Stream<Arguments> pieceAndColor() {
            return Stream.of(
                    Arguments.arguments(PieceType.PAWN, Color.WHITE),
                    Arguments.arguments(PieceType.KNIGHT, Color.WHITE),
                    Arguments.arguments(PieceType.ROOK, Color.WHITE),
                    Arguments.arguments(PieceType.BISHOP, Color.WHITE),
                    Arguments.arguments(PieceType.QUEEN, Color.WHITE),
                    Arguments.arguments(PieceType.KING, Color.WHITE),
                    Arguments.arguments(PieceType.PAWN, Color.BLACK),
                    Arguments.arguments(PieceType.KNIGHT, Color.BLACK),
                    Arguments.arguments(PieceType.ROOK, Color.BLACK),
                    Arguments.arguments(PieceType.BISHOP, Color.BLACK),
                    Arguments.arguments(PieceType.QUEEN, Color.BLACK),
                    Arguments.arguments(PieceType.KING, Color.BLACK)
            );
        }

        @MethodSource("pieceAndColor")
        @ParameterizedTest(name = "{1} 색 {0}이 생성되어야 한다")
        void 해당_색을_가진_기물이_생성되어야_한다(PieceType pieceType, Color color) {
            var piece = new Piece(pieceType, color);

            assertAll(
                    () -> assertThat(piece.getColor()).isEqualTo(color),
                    () -> assertThat(piece.getColor()).isEqualTo(color));
        }

        @MethodSource("pieceAndColor")
        @ParameterizedTest(name = "{0} 색 기물은 {1}색에 따른 표현값을 반환할 수 있다")
        void 특정_색_기물은_해당_색에_따른_표현값을_반환할_수_있다(PieceType pieceType, Color color) {
            var piece = new Piece(pieceType, color);

            assertThat(piece.getRepresentation()).isEqualTo(PieceRepresentation.findByTypeAndColor(pieceType, color));
        }


        @EnumSource(PieceType.class)
        @ParameterizedTest
        void 색깔이_주어지지_않았다면_하얀색_기물이_생성되어야_한다(PieceType type) {
            var piece = new Piece(type);

            assertThat(piece.getColor()).isEqualTo(Color.WHITE);
        }

        @EnumSource(PieceType.class)
        @ParameterizedTest
        void 색깔이_주어지지_않았다면_하얀색_기물의_표현값이_반환되어야_한다(PieceType type) {
            var piece = new Piece(type);

            assertThat(piece.getRepresentation()).isEqualTo(PieceRepresentation.findByTypeAndColor(type, Color.WHITE));
        }
    }
}

