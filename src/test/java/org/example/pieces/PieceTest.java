package org.example.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.example.pieces.Piece.Color;
import org.example.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PieceTest {


    @DisplayName("색깔에 맞는 기물이 생성되어야 한다")
    @ParameterizedTest(name = "기물의 색깔은 {1}이고 표현은 {2}이어야 한다.")
    @MethodSource("argumentsStream")
    public void create(Piece piece, Color color, char representation) {
        verifyPiece(piece, color, representation);
    }

    @DisplayName("기물의 색상을 확인")
    @ParameterizedTest(name = "기물의 색깔은 {0}이다.")
    @MethodSource("argumentsStream")
    public void checkIsBlack(Piece piece, Color color, Type notUsed) {
        verifyColor(piece, color);
    }

    //verifyMethod

    private void verifyColor(Piece piece, Color color) {
        if (color == Color.WHITE) {
            assertThat(piece.isWhite()).isTrue();
            assertThat(piece.isBlack()).isFalse();
        } else {
            assertThat(piece.isWhite()).isFalse();
            assertThat(piece.isBlack()).isTrue();
        }
    }

    private void verifyPiece(final Piece piece, final Color color, final Type representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }


    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
            Arguments.arguments(
                Piece.createWhitePawn(),
                Color.WHITE,
                Type.PAWN.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackPawn(),
                Color.BLACK,
                Type.PAWN.getBlackRepresentation()
            ),
            Arguments.arguments(
                Piece.createWhiteKnight(),
                Color.WHITE,
                Type.KNIGHT.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackKnight(),
                Color.BLACK,
                Type.KNIGHT.getBlackRepresentation()
            ),
            Arguments.arguments(
                Piece.createWhiteRook(),
                Color.WHITE,
                Type.ROOK.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackRook(),
                Color.BLACK,
                Type.ROOK.getBlackRepresentation()
            ),
            Arguments.arguments(
                Piece.createWhiteBishop(),
                Color.WHITE,
                Type.BISHOP.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackBishop(),
                Color.BLACK,
                Type.BISHOP.getBlackRepresentation()
            ),
            Arguments.arguments(
                Piece.createWhiteQueen(),
                Color.WHITE,
                Type.QUEEN.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackQueen(),
                Color.BLACK,
                Type.QUEEN.getBlackRepresentation()
            ),
            Arguments.arguments(
                Piece.createWhiteKing(),
                Color.WHITE,
                Type.KING.getWhiteRepresentation()
            ),
            Arguments.arguments(
                Piece.createBlackKing(),
                Color.BLACK,
                Type.KING.getBlackRepresentation()
            )
        );
    }
}