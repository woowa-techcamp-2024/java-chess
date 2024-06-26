package org.example.pieces;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chess.pieces.Piece.*;
import static org.example.chess.pieces.Piece.Color.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    public void create_기본생성자() {
        Piece piece = new Piece();
        assertEquals(WHITE, piece.getColor());
        assertEquals('p', piece.getRepresentation());
    }

    @Test
    @DisplayName("올바른 색과 코드의 폰이 생성되어야 한다")
    public void create() {
        verifyPiece(Piece.createBlackPawn(),BLACK,PieceType.PAWN);
        verifyPiece(Piece.createWhitePawn(),WHITE,PieceType.PAWN);
    }

    @Test
    @DisplayName("올바른 색과 코드의 피스가 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.createBlackPawn(),BLACK,PieceType.PAWN);
        verifyPiece(Piece.createWhitePawn(),WHITE,PieceType.PAWN);
        verifyPiece(Piece.createBlackKnight(),BLACK,PieceType.KNIGHT);
        verifyPiece(Piece.createWhiteKnight(),WHITE,PieceType.KNIGHT);
        verifyPiece(Piece.createBlackBishop(),BLACK,PieceType.BISHOP);
        verifyPiece(Piece.createWhiteBishop(),WHITE,PieceType.BISHOP);
        verifyPiece(Piece.createBlackRook(),BLACK,PieceType.ROOK);
        verifyPiece(Piece.createWhiteRook(),WHITE,PieceType.ROOK);
        verifyPiece(Piece.createBlackQueen(),BLACK,PieceType.QUEEN);
        verifyPiece(Piece.createWhiteQueen(),WHITE,PieceType.QUEEN);
        verifyPiece(Piece.createBlackKing(),BLACK,PieceType.KING);
        verifyPiece(Piece.createWhiteKing(),WHITE,PieceType.KING);
    }

    @Test
    @DisplayName("색을 구분한 결과값을 내야한다")
    public void compare_color() {
        Piece piece = Piece.createPiece(WHITE, PieceType.PAWN);
        Assertions.assertTrue(piece.isWhite());
        Assertions.assertFalse(piece.isBlack());
    }

    @Test
    @DisplayName("타입에 따른 올바른 표현값을 나타내야 한다.")
    public void getRepresentationPerPiece() {
        assertEquals('P',PieceType.PAWN.getBlackRepresentation());
        assertEquals('p',PieceType.PAWN.getWhiteRepresentation());

    }


    private static void verifyPiece(final Piece piece, final Color color, PieceType pieceType) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(color.equals(BLACK) ? pieceType.getBlackRepresentation() : pieceType.getWhiteRepresentation());
    }
}