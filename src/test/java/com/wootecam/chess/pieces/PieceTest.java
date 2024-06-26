package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("기물 테스트")
class PieceTest {

    @Nested
    class 기물을_생성한다 {

        @Test
        void 기물은_색과_타입에_따라_생성할_수_있다() {
            verifyPiece(Piece.createWhitePawn(), Piece.createBlackPawn(), PieceType.PAWN);
            verifyPiece(Piece.createWhiteKnight(), Piece.createBlackKnight(), PieceType.KNIGHT);
            verifyPiece(Piece.createWhiteRook(), Piece.createBlackRook(), PieceType.ROOK);
            verifyPiece(Piece.createWhiteBishop(), Piece.createBlackBishop(), PieceType.BISHOP);
            verifyPiece(Piece.createWhiteQueen(), Piece.createBlackQueen(), PieceType.QUEEN);
            verifyPiece(Piece.createWhiteKing(), Piece.createBlackKing(), PieceType.KING);
        }

        private void verifyPiece(Piece whitePiece, Piece blackPiece, PieceType type) {
            assertAll(
                    () -> assertThat(whitePiece.getType()).isEqualTo(type),
                    () -> assertThat(whitePiece.getColor().isWhite()).isTrue(),
                    () -> assertThat(blackPiece.getType()).isEqualTo(type),
                    () -> assertThat(blackPiece.getColor().isBlack()).isTrue()
            );
        }

        @Test
        void 타입과_색이_없는_기물도_존재할_수_있다() {
            Piece blank = Piece.createBlank();
            assertAll(
                    () -> assertThat(blank.getType()).isEqualTo(PieceType.NO_PIECE),
                    () -> assertThat(blank.getColor()).isEqualTo(Color.NO_COLOR)
            );
        }
    }

    @Nested
    class 기물의_타입과_색에_따른_표현값을_조회한다 {

        static Stream<Arguments> allPiecesAndColor() {
            return Stream.of(
                    Arguments.arguments(Piece.createWhitePawn(), PieceType.PAWN, Color.WHITE),
                    Arguments.arguments(Piece.createWhiteKnight(), PieceType.KNIGHT, Color.WHITE),
                    Arguments.arguments(Piece.createWhiteRook(), PieceType.ROOK, Color.WHITE),
                    Arguments.arguments(Piece.createWhiteBishop(), PieceType.BISHOP, Color.WHITE),
                    Arguments.arguments(Piece.createWhiteQueen(), PieceType.QUEEN, Color.WHITE),
                    Arguments.arguments(Piece.createWhiteKing(), PieceType.KING, Color.WHITE),
                    Arguments.arguments(Piece.createBlackPawn(), PieceType.PAWN, Color.BLACK),
                    Arguments.arguments(Piece.createBlackKnight(), PieceType.KNIGHT, Color.BLACK),
                    Arguments.arguments(Piece.createBlackRook(), PieceType.ROOK, Color.BLACK),
                    Arguments.arguments(Piece.createBlackBishop(), PieceType.BISHOP, Color.BLACK),
                    Arguments.arguments(Piece.createBlackQueen(), PieceType.QUEEN, Color.BLACK),
                    Arguments.arguments(Piece.createBlackKing(), PieceType.KING, Color.BLACK)
            );
        }

        @MethodSource("allPiecesAndColor")
        @ParameterizedTest(name = "{1} 타입 {2} 색 기물")
        void 기물은_색과_타입에_따른_표현값을_반환할_수_있다(Piece piece, PieceType type, Color color) {
            assertThat(piece.getRepresentation()).isEqualTo(PieceRepresentation.findByTypeAndColor(type, color));
        }
    }

    @Nested
    class 기물의_색을_확인한다 {

        static Stream<Arguments> pieceAndColor() {
            return Stream.of(
                    Arguments.arguments(Piece.createWhitePawn(), true, false),
                    Arguments.arguments(Piece.createWhiteKing(), true, false),
                    Arguments.arguments(Piece.createBlackPawn(), false, true),
                    Arguments.arguments(Piece.createBlackQueen(), false, true)
            );
        }

        @MethodSource("pieceAndColor")
        @ParameterizedTest(name = "{0}")
        void 기물의_색을_확인할_수_있다(Piece piece, boolean isWhite, boolean isBlack) {
            assertAll(
                    () -> assertThat(piece.isWhite()).isEqualTo(isWhite),
                    () -> assertThat(piece.isBlack()).isEqualTo(isBlack));
        }
    }
}

