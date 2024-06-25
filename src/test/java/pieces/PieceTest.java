package pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pieces.Piece.Color.BLACK;
import static pieces.Piece.Color.BLANK;
import static pieces.Piece.Color.WHITE;
import static pieces.Piece.PieceType.BISHOP;
import static pieces.Piece.PieceType.KING;
import static pieces.Piece.PieceType.KNIGHT;
import static pieces.Piece.PieceType.PAWN;
import static pieces.Piece.PieceType.QUEEN;
import static pieces.Piece.PieceType.ROOK;
import static pieces.Piece.createPiece;

import org.junit.jupiter.api.Test;
import pieces.Piece.Color;
import pieces.Piece.PieceType;

public class PieceTest {

    @Test
    public void create_piece() {
        verifyPiece(createPiece(WHITE, PAWN), createPiece(BLACK, PAWN), PAWN);
        verifyPiece(createPiece(WHITE, KNIGHT), Piece.createPiece(BLACK, KNIGHT), PieceType.KNIGHT);
        verifyPiece(Piece.createPiece(WHITE, ROOK), Piece.createPiece(BLACK, ROOK), PieceType.ROOK);
        verifyPiece(Piece.createPiece(WHITE, BISHOP), Piece.createPiece(BLACK, BISHOP), PieceType.BISHOP);
        verifyPiece(Piece.createPiece(WHITE, QUEEN), Piece.createPiece(BLACK, QUEEN), PieceType.QUEEN);
        verifyPiece(Piece.createPiece(WHITE, KING), Piece.createPiece(BLACK, KING), PieceType.KING);

        Piece blank = Piece.createPiece(BLANK, PieceType.BLANK);
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.isBlank()).isTrue();

        verifyPiece(Piece.createPiece(WHITE, PAWN), WHITE, PAWN.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, PAWN), BLACK, PAWN.getRepresentation());
        verifyPiece(Piece.createPiece(WHITE, KNIGHT), WHITE, PieceType.KNIGHT.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, KNIGHT), BLACK, PieceType.KNIGHT.getRepresentation());
        verifyPiece(Piece.createPiece(WHITE, ROOK), WHITE, PieceType.ROOK.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, ROOK), BLACK, PieceType.ROOK.getRepresentation());
        verifyPiece(Piece.createPiece(WHITE, BISHOP), WHITE, PieceType.BISHOP.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, BISHOP), BLACK, PieceType.BISHOP.getRepresentation());
        verifyPiece(Piece.createPiece(WHITE, QUEEN), WHITE, PieceType.QUEEN.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, QUEEN), BLACK, PieceType.QUEEN.getRepresentation());
        verifyPiece(Piece.createPiece(WHITE, KING), WHITE, PieceType.KING.getRepresentation());
        verifyPiece(Piece.createPiece(BLACK, KING), BLACK, PieceType.KING.getRepresentation());
    }

    private void verifyPiece(Piece piece, Color color, char representation) {
        assertEquals(piece.getColor(), color);
        assertEquals(piece.getRepresentation(),
            WHITE == color ? Character.toLowerCase(representation) : representation);
    }

    private void verifyPiece(Piece whitePiece, Piece blackPiece, PieceType type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(whitePiece.getPieceType()).isEqualByComparingTo(type);

        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(blackPiece.getPieceType()).isEqualByComparingTo(type);
    }

    @Test
    public void testColor() {
        assertTrue(Piece.createPiece(WHITE, PAWN).isWhite());
        assertTrue(Piece.createPiece(BLACK, PAWN).isBlack());
        assertTrue(Piece.createPiece(WHITE, KNIGHT).isWhite());
        assertTrue(Piece.createPiece(BLACK, KNIGHT).isBlack());
        assertTrue(Piece.createPiece(WHITE, ROOK).isWhite());
        assertTrue(Piece.createPiece(BLACK, ROOK).isBlack());
        assertTrue(Piece.createPiece(WHITE, BISHOP).isWhite());
        assertTrue(Piece.createPiece(BLACK, BISHOP).isBlack());
        assertTrue(Piece.createPiece(WHITE, QUEEN).isWhite());
        assertTrue(Piece.createPiece(BLACK, QUEEN).isBlack());
        assertTrue(Piece.createPiece(WHITE, KING).isWhite());
        assertTrue(Piece.createPiece(BLACK, KING).isBlack());
    }
}
