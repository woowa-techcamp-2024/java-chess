package org.example.pieces;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    public void create_기본생성자() {
        Piece piece = new Piece();
        assertEquals(WHITE_COLOR, piece.getColor());
        assertEquals(WHITE_REPRESENTATION, piece.getRepresentation());
    }

    @Test
    @DisplayName("올바른 색과 코드의 폰이 생성되어야 한다")
    public void create() {
        verifyPiece(BLACK_COLOR,PieceType.PAWN);
        verifyPiece(WHITE_COLOR,PieceType.PAWN);
    }

    @Test
    @DisplayName("올바른 색과 코드의 피스가 생성되어야 한다")
    public void create_piece() {
        verifyPiece(BLACK_COLOR,PieceType.PAWN);
        verifyPiece(WHITE_COLOR,PieceType.PAWN);
        verifyPiece(BLACK_COLOR,PieceType.KNIGHT);
        verifyPiece(WHITE_COLOR,PieceType.KNIGHT);
        verifyPiece(BLACK_COLOR,PieceType.BISHOP);
        verifyPiece(WHITE_COLOR,PieceType.BISHOP);
        verifyPiece(BLACK_COLOR,PieceType.ROOK);
        verifyPiece(WHITE_COLOR,PieceType.ROOK);
        verifyPiece(BLACK_COLOR,PieceType.QUEEN);
        verifyPiece(WHITE_COLOR,PieceType.QUEEN);
        verifyPiece(BLACK_COLOR,PieceType.KING);
        verifyPiece(WHITE_COLOR,PieceType.KING);
    }



    private static void verifyPiece(final String color, PieceType pieceType) {
        Piece piece1 = new Piece(color, pieceType);
        assertThat(piece1.getColor()).isEqualTo(color);
        assertThat(piece1.getRepresentation()).isEqualTo(color.equals(Piece.BLACK_COLOR) ? pieceType.getAbbreviation() : (char) (pieceType.getAbbreviation() -32 ));
    }
}