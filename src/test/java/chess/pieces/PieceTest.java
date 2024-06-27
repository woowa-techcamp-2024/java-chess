package chess.pieces;

import chess.pieces.type.Color;
import chess.pieces.type.Type;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.create(Type.PAWN, Color.WHITE), Piece.create(Type.PAWN, Color.BLACK), Type.PAWN);
        verifyPiece(Piece.create(Type.KNIGHT, Color.WHITE), Piece.create(Type.KNIGHT, Color.BLACK), Type.KNIGHT);
        verifyPiece(Piece.create(Type.ROOK, Color.WHITE), Piece.create(Type.ROOK, Color.BLACK), Type.ROOK);
        verifyPiece(Piece.create(Type.BISHOP, Color.WHITE), Piece.create(Type.BISHOP, Color.BLACK), Type.BISHOP);
        verifyPiece(Piece.create(Type.QUEEN, Color.WHITE), Piece.create(Type.QUEEN, Color.BLACK), Type.QUEEN);
        verifyPiece(Piece.create(Type.KING, Color.WHITE), Piece.create(Type.KING, Color.BLACK), Type.KING);

        Piece blank = Piece.create(Type.NO_PIECE, Color.NOCOLOR);
        assertFalse(blank.getColor().isWhite());
        assertFalse(blank.getColor().isBlack());
        assertEquals(Type.NO_PIECE, blank.getType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.getColor().isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.getColor().isBlack());
        assertEquals(type, blackPiece.getType());
    }
}
