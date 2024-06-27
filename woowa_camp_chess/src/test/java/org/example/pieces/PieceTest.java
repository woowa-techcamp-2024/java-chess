package org.example.pieces;

import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceFactory;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chess.pieces.Piece.Color.BLACK;
import static org.example.chess.pieces.Piece.Color.WHITE;
import static org.example.chess.pieces.PieceFactory.createBlackPawn;
import static org.example.chess.pieces.PieceFactory.createWhitePawn;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {


    @Test
    @DisplayName("올바른 색과 코드의 폰이 생성되어야 한다")
    public void create() {
        verifyPiece(createBlackPawn(),BLACK,PieceType.PAWN);
        verifyPiece(createWhitePawn(),WHITE,PieceType.PAWN);
    }

    @Test
    @DisplayName("올바른 색과 코드의 피스가 생성되어야 한다")
    public void create_piece() {
        verifyPiece(createBlackPawn(),BLACK,PieceType.PAWN);
        verifyPiece(PieceFactory.createWhitePawn(),WHITE,PieceType.PAWN);
        verifyPiece(PieceFactory.createBlackKnight(),BLACK,PieceType.KNIGHT);
        verifyPiece(PieceFactory.createWhiteKnight(),WHITE,PieceType.KNIGHT);
        verifyPiece(PieceFactory.createBlackBishop(),BLACK,PieceType.BISHOP);
        verifyPiece(PieceFactory.createWhiteBishop(),WHITE,PieceType.BISHOP);
        verifyPiece(PieceFactory.createBlackRook(),BLACK,PieceType.ROOK);
        verifyPiece(PieceFactory.createWhiteRook(),WHITE,PieceType.ROOK);
        verifyPiece(PieceFactory.createBlackQueen(),BLACK,PieceType.QUEEN);
        verifyPiece(PieceFactory.createWhiteQueen(),WHITE,PieceType.QUEEN);
        verifyPiece(PieceFactory.createBlackKing(),BLACK,PieceType.KING);
        verifyPiece(PieceFactory.createWhiteKing(),WHITE,PieceType.KING);
    }




    @Test
    @DisplayName("타입에 따른 올바른 표현값을 나타내야 한다.")
    public void getRepresentationPerPiece() {
        assertEquals('P',PieceType.PAWN.getBlackRepresentation());
        assertEquals('p',PieceType.PAWN.getWhiteRepresentation());

    }


    private static void verifyPiece(final Piece piece, final Piece.Color color, PieceType pieceType) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(color.equals(BLACK) ? pieceType.getBlackRepresentation() : pieceType.getWhiteRepresentation());
    }
}