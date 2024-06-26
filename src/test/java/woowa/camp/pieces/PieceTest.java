package woowa.camp.pieces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import woowa.camp.pieces.Piece.Color;
import woowa.camp.pieces.Piece.Type;

public class PieceTest {

    @Test
    @DisplayName("[Success] 색상에 맞는 폰이 생성되어야 한다")
    void create() {
        Piece pieceWhite = Piece.createWhitePawn();
        Piece pieceBlack = Piece.createBlackPawn();

        verifyPawnColor(pieceWhite, Color.WHITE, Representation.p);
        verifyPawnColor(pieceBlack, Color.BLACK, Representation.P);
    }

    @Test
    @DisplayName("[Success] 색이 없는 Pawn을 생성하는 경우 기본 색상이 흰색(white)이다")
    void default_create_is_white() {
        Piece piece = Piece.createWhitePawn();
        verifyPawnColor(piece, Color.WHITE, Representation.p);
    }

    private void verifyPawnColor(Piece piece, Color expectedColor, Representation expectedRepresentation) {
        assertThat(piece.isSameColor(expectedColor)).isTrue();
        assertThat(piece.getRepresentation()).isEqualTo(expectedRepresentation);
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

        verifyPiece(blackKing, Type.KING, Color.BLACK, Representation.K);
        verifyPiece(blackQueen, Type.QUEEN, Color.BLACK, Representation.Q);
        verifyPiece(blackRook, Type.ROOK, Color.BLACK, Representation.R);
        verifyPiece(blackBishop, Type.BISHOP, Color.BLACK, Representation.B);
        verifyPiece(blackKnight, Type.KNIGHT, Color.BLACK, Representation.N);
        verifyPiece(blackPawn, Type.PAWN, Color.BLACK, Representation.P);

        verifyPiece(whiteKing, Type.KING, Color.WHITE, Representation.k);
        verifyPiece(whiteQueen, Type.QUEEN, Color.WHITE, Representation.q);
        verifyPiece(whiteRook, Type.ROOK, Color.WHITE, Representation.r);
        verifyPiece(whiteBishop, Type.BISHOP, Color.WHITE, Representation.b);
        verifyPiece(whiteKnight, Type.KNIGHT, Color.WHITE, Representation.n);
        verifyPiece(whitePawn, Type.PAWN, Color.WHITE, Representation.p);
    }

    private void verifyPiece(Piece piece, Type type, Color color, Representation representation) {
        assertThat(piece.getType()).isEqualTo(type);
        assertThat(piece.getColor()).isEqualTo(color.getName());
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }

}
