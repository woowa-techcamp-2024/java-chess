package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    @Test
    @DisplayName("원하는 색상과 종류의 말이 생성되어야 한다")
    public void create_piece() {
        verifyPiece(PieceFactory.createPawn(Color.WHITE, new Position("a1")), PieceFactory.createPawn(Color.BLACK, new Position("a1")), Type.PAWN);
        verifyPiece(PieceFactory.createKnight(Color.WHITE, new Position("a1")), PieceFactory.createKnight(Color.BLACK, new Position("a1")), Type.KNIGHT);
        verifyPiece(PieceFactory.createRook(Color.WHITE, new Position("a1")), PieceFactory.createRook(Color.BLACK, new Position("a1")), Type.ROOK);
        verifyPiece(PieceFactory.createBishop(Color.WHITE, new Position("a1")), PieceFactory.createBishop(Color.BLACK, new Position("a1")), Type.BISHOP);
        verifyPiece(PieceFactory.createQueen(Color.WHITE, new Position("a1")), PieceFactory.createQueen(Color.BLACK, new Position("a1")), Type.QUEEN);
        verifyPiece(PieceFactory.createKing(Color.WHITE, new Position("a1")), PieceFactory.createKing(Color.BLACK, new Position("a1")), Type.KING);

        Piece blank = PieceFactory.createBlank(new Position("a1"));
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