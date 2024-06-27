package woowa.camp.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static woowa.camp.pieces.Piece.Color.BLACK;
import static woowa.camp.pieces.Piece.Color.WHITE;
import static woowa.camp.pieces.Piece.Type.BISHOP;
import static woowa.camp.pieces.Piece.Type.KING;
import static woowa.camp.pieces.Piece.Type.KNIGHT;
import static woowa.camp.pieces.Piece.Type.PAWN;
import static woowa.camp.pieces.Piece.Type.QUEEN;
import static woowa.camp.pieces.Piece.Type.ROOK;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;

public class PieceTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Piece pieceWhite = Piece.createWhitePieceOf(PAWN);
        Piece pieceBlack = Piece.createBlackPieceOf(PAWN);

        verifyPawnColor(pieceWhite, WHITE, "p");
        verifyPawnColor(pieceBlack, BLACK, "P");
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Piece piece = Piece.createWhitePieceOf(PAWN);
        verifyPawnColor(piece, WHITE, "p");
    }

    private void verifyPawnColor(Piece piece, Color expectedColor, String expectedRepresentation) {
        assertThat(piece.isSameColor(expectedColor)).isTrue();
        assertThat(piece.getRepresentation()).isEqualTo(expectedRepresentation);
    }

    @Test
    @DisplayName("[Success] 색상, 종류에 따른 기물 생성")
    void create_piece() {
        Piece blackKing = Piece.createBlackPieceOf(KING);
        Piece blackQueen = Piece.createBlackPieceOf(QUEEN);
        Piece blackRook = Piece.createBlackPieceOf(ROOK);
        Piece blackBishop = Piece.createBlackPieceOf(BISHOP);
        Piece blackKnight = Piece.createBlackPieceOf(KNIGHT);
        Piece blackPawn = Piece.createBlackPieceOf(PAWN);

        Piece whiteKing = Piece.createWhitePieceOf(KING);
        Piece whiteQueen = Piece.createWhitePieceOf(QUEEN);
        Piece whiteRook = Piece.createWhitePieceOf(ROOK);
        Piece whiteBishop = Piece.createWhitePieceOf(BISHOP);
        Piece whiteKnight = Piece.createWhitePieceOf(KNIGHT);
        Piece whitePawn = Piece.createWhitePieceOf(PAWN);

        verifyPiece(blackKing, KING, BLACK, "K");
        verifyPiece(blackQueen, QUEEN, BLACK, "Q");
        verifyPiece(blackRook, ROOK, BLACK, "R");
        verifyPiece(blackBishop, BISHOP, BLACK, "B");
        verifyPiece(blackKnight, KNIGHT, BLACK, "N");
        verifyPiece(blackPawn, PAWN, BLACK, "P");

        verifyPiece(whiteKing, KING, WHITE, "k");
        verifyPiece(whiteQueen, QUEEN, WHITE, "q");
        verifyPiece(whiteRook, ROOK, WHITE, "r");
        verifyPiece(whiteBishop, BISHOP, WHITE, "b");
        verifyPiece(whiteKnight, KNIGHT, WHITE, "n");
        verifyPiece(whitePawn, PAWN, WHITE, "p");
    }

    private void verifyPiece(Piece piece, Type type, Color color, String representation) {
        assertThat(piece.getType()).isEqualTo(type);
        assertThat(piece.isSameColor(color)).isTrue();
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

}
