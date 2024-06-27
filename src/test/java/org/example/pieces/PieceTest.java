package org.example.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.pieces.PieceFactory.createBlackBishop;
import static org.example.pieces.PieceFactory.createBlackKing;
import static org.example.pieces.PieceFactory.createBlackKnight;
import static org.example.pieces.PieceFactory.createBlackPawn;
import static org.example.pieces.PieceFactory.createBlackQueen;
import static org.example.pieces.PieceFactory.createBlackRook;
import static org.example.pieces.PieceFactory.createNoColorPiece;
import static org.example.pieces.PieceFactory.createWhiteBishop;
import static org.example.pieces.PieceFactory.createWhiteKing;
import static org.example.pieces.PieceFactory.createWhiteKnight;
import static org.example.pieces.PieceFactory.createWhitePawn;
import static org.example.pieces.PieceFactory.createWhiteQueen;
import static org.example.pieces.PieceFactory.createWhiteRook;
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
    @ParameterizedTest(name = "기물의 색깔은 {1}이다.")
    @MethodSource("argumentsStream")
    public void checkIsBlack(Piece piece, Color color, char notUsed) {
        verifyColor(piece, color);
    }

    //verifyMethod

    private void verifyColor(Piece piece, Color color) {
        if (color == Color.WHITE) {
            assertThat(piece.isWhite()).isTrue();
            assertThat(piece.isBlack()).isFalse();
        } else if (color == Color.BLACK){
            assertThat(piece.isWhite()).isFalse();
            assertThat(piece.isBlack()).isTrue();
        } else {
            assertThat(piece.isWhite()).isFalse();
            assertThat(piece.isBlack()).isFalse();
        }
    }

    private void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }


    private static Stream<Arguments> argumentsStream() {
        return Stream.of(
            Arguments.arguments(
                createWhitePawn(),
                Color.WHITE,
                Type.PAWN.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackPawn(),
                Color.BLACK,
                Type.PAWN.getBlackRepresentation()
            ),
            Arguments.arguments(
                createWhiteKnight(),
                Color.WHITE,
                Type.KNIGHT.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackKnight(),
                Color.BLACK,
                Type.KNIGHT.getBlackRepresentation()
            ),
            Arguments.arguments(
                createWhiteRook(),
                Color.WHITE,
                Type.ROOK.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackRook(),
                Color.BLACK,
                Type.ROOK.getBlackRepresentation()
            ),
            Arguments.arguments(
                createWhiteBishop(),
                Color.WHITE,
                Type.BISHOP.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackBishop(),
                Color.BLACK,
                Type.BISHOP.getBlackRepresentation()
            ),
            Arguments.arguments(
                createWhiteQueen(),
                Color.WHITE,
                Type.QUEEN.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackQueen(),
                Color.BLACK,
                Type.QUEEN.getBlackRepresentation()
            ),
            Arguments.arguments(
                createWhiteKing(),
                Color.WHITE,
                Type.KING.getWhiteRepresentation()
            ),
            Arguments.arguments(
                createBlackKing(),
                Color.BLACK,
                Type.KING.getBlackRepresentation()
            ),
            Arguments.arguments(
                createNoColorPiece(),
                Color.NO_COLOR,
                Type.NO_PIECE.getWhiteRepresentation()
            )
        );
    }
}