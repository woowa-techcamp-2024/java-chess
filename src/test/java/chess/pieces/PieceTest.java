package chess.pieces;

import org.junit.jupiter.api.*;

import static chess.pieces.Representations.*;
import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("모든 종류의 기물이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(Piece.create(Type.PAWN, Colors.WHITE), Piece.create(Type.PAWN, Colors.BLACK), Type.PAWN);
        verifyPiece(Piece.create(Type.KNIGHT, Colors.WHITE), Piece.create(Type.KNIGHT, Colors.BLACK), Type.KNIGHT);
        verifyPiece(Piece.create(Type.ROOK, Colors.WHITE), Piece.create(Type.ROOK, Colors.BLACK), Type.ROOK);
        verifyPiece(Piece.create(Type.BISHOP, Colors.WHITE), Piece.create(Type.BISHOP, Colors.BLACK), Type.BISHOP);
        verifyPiece(Piece.create(Type.QUEEN, Colors.WHITE), Piece.create(Type.QUEEN, Colors.BLACK), Type.QUEEN);
        verifyPiece(Piece.create(Type.KING, Colors.WHITE), Piece.create(Type.KING, Colors.BLACK), Type.KING);

        Piece blank = Piece.create(Type.NO_PIECE, Colors.NOCOLOR);
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
