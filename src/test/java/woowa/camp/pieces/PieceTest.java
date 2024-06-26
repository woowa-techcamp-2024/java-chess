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

        verifyPawnColor(pieceWhite, WHITE, Representation.p);
        verifyPawnColor(pieceBlack, BLACK, Representation.P);
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Piece piece = Piece.createWhitePieceOf(PAWN);
        verifyPawnColor(piece, WHITE, Representation.p);
    }

    private void verifyPawnColor(Piece piece, Color expectedColor, Representation expectedRepresentation) {
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

        verifyPiece(blackKing, KING, BLACK, Representation.K);
        verifyPiece(blackQueen, QUEEN, BLACK, Representation.Q);
        verifyPiece(blackRook, ROOK, BLACK, Representation.R);
        verifyPiece(blackBishop, BISHOP, BLACK, Representation.B);
        verifyPiece(blackKnight, KNIGHT, BLACK, Representation.N);
        verifyPiece(blackPawn, PAWN, BLACK, Representation.P);

        verifyPiece(whiteKing, KING, WHITE, Representation.k);
        verifyPiece(whiteQueen, QUEEN, WHITE, Representation.q);
        verifyPiece(whiteRook, ROOK, WHITE, Representation.r);
        verifyPiece(whiteBishop, BISHOP, WHITE, Representation.b);
        verifyPiece(whiteKnight, KNIGHT, WHITE, Representation.n);
        verifyPiece(whitePawn, PAWN, WHITE, Representation.p);
    }

    private void verifyPiece(Piece piece, Type type, Color color, Representation representation) {
        assertThat(piece.getType()).isEqualTo(type);
        assertThat(piece.getColor()).isEqualTo(color.getName());
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

}
