package chess.pieces;

import chess.CommandChanger;
import org.junit.jupiter.api.*;

import static chess.ChessGame.initializeCmdToPos;
import static chess.pieces.Piece.Color.BLACK;
import static chess.pieces.Piece.Color.NOCOLOR;
import static chess.pieces.Piece.Color.WHITE;

import static chess.pieces.Piece.Type;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @BeforeAll
    static void setUp() {
        initializeCmdToPos();
    }

    @Test
    @DisplayName("해당하는 색 폰이 생성되어야 한다")
    void create_piece() {
        var pos = CommandChanger.getPosition("a1");
        verifyPiece(Piece.of(Pawn.class, WHITE, pos), Piece.of(Pawn.class, BLACK, pos), Type.PAWN);
        verifyPiece(Piece.of(Knight.class, WHITE, pos), Piece.of(Knight.class, BLACK, pos), Type.KNIGHT);
        verifyPiece(Piece.of(Rook.class, WHITE, pos), Piece.of(Rook.class, BLACK, pos), Type.ROOK);
        verifyPiece(Piece.of(Bishop.class, WHITE, pos), Piece.of(Bishop.class, BLACK, pos), Type.BISHOP);
        verifyPiece(Piece.of(Queen.class, WHITE, pos), Piece.of(Queen.class, BLACK, pos), Type.QUEEN);
        verifyPiece(Piece.of(King.class, WHITE, pos), Piece.of(King.class, BLACK, pos), Type.KING);

        Piece blank = Piece.of(NoPiece.class, NOCOLOR, pos);
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();

        assertThat(Type.NO_PIECE.getRepresentation(NOCOLOR)).isEqualTo(blank.getType());
    }

    @Test
    @DisplayName("같은 피스인가 ?")
    void checkPiece() {
        Rook r1 = (Rook) Piece.of(Rook.class, WHITE, "a1");
        Rook r2 = (Rook) Piece.of(Rook.class, WHITE, "a1");

        assertEquals(r1, r2);
    }

    @Test
    @DisplayName("검정색 피스인가?")
    void isBlack() {
        Piece piece = Piece.of(Pawn.class, BLACK, "a1");
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 피스인가?")
    void isWhite() {
        Piece piece = Piece.of(Pawn.class, WHITE);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    void getRepresentation() {
        assertThat('p').isEqualTo(Piece.Type.PAWN.getRepresentation(WHITE));
        assertThat('P').isEqualTo(Piece.Type.PAWN.getRepresentation(BLACK));
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type.getRepresentation(WHITE), whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type.getRepresentation(BLACK), blackPiece.getType());

    }
}
