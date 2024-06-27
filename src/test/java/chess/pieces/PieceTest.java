package chess.pieces;

import chess.Position;
import chess.constant.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("원하는 색상과 종류의 말이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.createWhitePawn(new Position("a1")), Piece.createBlackPawn(new Position("a1")), Type.PAWN);
        verifyPiece(Piece.createWhiteKnight(new Position("a1")), Piece.createBlackKnight(new Position("a1")), Type.KNIGHT);
        verifyPiece(Piece.createWhiteRook(new Position("a1")), Piece.createBlackRook(new Position("a1")), Type.ROOK);
        verifyPiece(Piece.createWhiteBishop(new Position("a1")), Piece.createBlackBishop(new Position("a1")), Type.BISHOP);
        verifyPiece(Piece.createWhiteQueen(new Position("a1")), Piece.createBlackQueen(new Position("a1")), Type.QUEEN);
        verifyPiece(Piece.createWhiteKing(new Position("a1")), Piece.createBlackKing(new Position("a1")), Type.KING);

        Piece blank = Piece.createBlank(new Position("a1"));
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

    @Test
    @DisplayName("하얀색은 소문자, 검은색은 대문자로 표현되어야 한다")
    public void getRepresentationPerPiece() {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());
    }
}