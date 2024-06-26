package woowa.camp.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Piece pieceWhite = Piece.createWhitePawn();
        Piece pieceBlack = Piece.createBlackPawn();

        verifyPawnColor(pieceWhite, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
        verifyPawnColor(pieceBlack, Color.PAWN_BLACK.getName(), Color.PAWN_BLACK.getRepresentation());

        assertThat(pieceWhite.getRepresentation()).isEqualTo(Color.PAWN_WHITE.getRepresentation());
        assertThat(pieceBlack.getRepresentation()).isEqualTo(Color.PAWN_BLACK.getRepresentation());
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Piece piece = Piece.createWhitePawn();
        verifyPawnColor(piece, Color.PAWN_WHITE.getName(), Color.PAWN_WHITE.getRepresentation());
    }

    private void verifyPawnColor(final Piece piece, final String color, final String representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

    @Test
    @DisplayName("[Success] 색상, 종류에 따른 기물 생성")
    void create_piece() {
        Piece blackKing = Piece.createBlackKing();
        Piece blackQueen = Piece.createBlackQueen();
        Piece blackRook = Piece.createBlackRook();
        Piece blackBishop = Piece.createBlackBishop();
        Piece blackKnight = Piece.createBlackKnight();
        Piece blackPawn = Piece.createBlackPawn();

        Piece whiteKing = Piece.createWhiteKing();
        Piece whiteQueen = Piece.createWhiteQueen();
        Piece whiteRook = Piece.createWhiteRook();
        Piece whiteBishop = Piece.createWhiteBishop();
        Piece whiteKnight = Piece.createWhiteKnight();
        Piece whitePawn = Piece.createWhitePawn();

        verifyPiece(blackKing, Piece.KING, Color.KING_BLACK);
        verifyPiece(blackQueen, Piece.QUEEN, Color.QUEEN_BLACK);
        verifyPiece(blackRook, Piece.ROOK, Color.ROOK_BLACK);
        verifyPiece(blackBishop, Piece.BISHOP, Color.BISHOP_BLACK);
        verifyPiece(blackKnight, Piece.KNIGHT, Color.KNIGHT_BLACK);
        verifyPiece(blackPawn, Piece.PAWN, Color.PAWN_BLACK);

        verifyPiece(whiteKing, Piece.KING, Color.KING_WHITE);
        verifyPiece(whiteQueen, Piece.QUEEN, Color.QUEEN_WHITE);
        verifyPiece(whiteRook, Piece.ROOK, Color.ROOK_WHITE);
        verifyPiece(whiteBishop, Piece.BISHOP, Color.BISHOP_WHITE);
        verifyPiece(whiteKnight, Piece.KNIGHT, Color.KNIGHT_WHITE);
        verifyPiece(whitePawn, Piece.PAWN, Color.PAWN_WHITE);
    }

    private void verifyPiece(Piece piece, String name, Color color) {
        assertThat(piece.getName()).isEqualTo(name);
        assertThat(piece.getColor()).isEqualTo(color.getName());
        assertThat(piece.getRepresentation()).isEqualTo(color.getRepresentation());
    }

}
