package com.wootecam.chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PiecesTest {

    @ParameterizedTest
    @CsvSource("""
            white, p
            black, P
            """
    )
    void 지정한_색상과_기물을_지정한_폰을_생성할_수_있다(String color, String representation) {
        // when
        Piece piece = new Piece(color, representation);

        // then
        assertAll(
                () -> assertThat(piece.getColor()).isEqualTo(color),
                () -> assertThat(piece.getRepresentation()).isEqualTo(representation)
        );
    }

    @Test
    void 모든_기물을_생성할_수_있다() {
        // when
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        Piece whiteKnight = Piece.createWhiteKnight();
        Piece blackKnight = Piece.createBlackKnight();

        Piece whiteRook = Piece.createWhiteRook();
        Piece blackRook = Piece.createBlackRook();

        Piece whiteBishop = Piece.createWhiteBishop();
        Piece blackBishop = Piece.createBlackBishop();

        Piece whiteQueen = Piece.createWhiteQueen();
        Piece blackQueen = Piece.createBlackQueen();

        Piece whiteKing = Piece.createWhiteKing();
        Piece blackKing = Piece.createBlackKing();

        // then
        assertAll(
                () -> verifyPiece(whitePawn, Piece.COLOR_WHITE, Piece.WHITE_PAWN_REPRESENTATION),
                () -> verifyPiece(blackPawn, Piece.COLOR_BLACK, Piece.BLACK_PAWN_REPRESENTATION),
                () -> verifyPiece(whiteKnight, Piece.COLOR_WHITE, Piece.WHITE_KNIGHT_REPRESENTATION),
                () -> verifyPiece(blackKnight, Piece.COLOR_BLACK, Piece.BLACK_KNIGHT_REPRESENTATION),
                () -> verifyPiece(whiteRook, Piece.COLOR_WHITE, Piece.WHITE_ROOK_REPRESENTATION),
                () -> verifyPiece(blackRook, Piece.COLOR_BLACK, Piece.BLACK_ROOK_REPRESENTATION),
                () -> verifyPiece(whiteBishop, Piece.COLOR_WHITE, Piece.WHITE_BISHOP_REPRESENTATION),
                () -> verifyPiece(blackBishop, Piece.COLOR_BLACK, Piece.BLACK_BISHOP_REPRESENTATION),
                () -> verifyPiece(whiteQueen, Piece.COLOR_WHITE, Piece.WHITE_QUEEN_REPRESENTATION),
                () -> verifyPiece(blackQueen, Piece.COLOR_BLACK, Piece.BLACK_QUEEN_REPRESENTATION),
                () -> verifyPiece(whiteKing, Piece.COLOR_WHITE, Piece.WHITE_KING_REPRESENTATION),
                () -> verifyPiece(blackKing, Piece.COLOR_BLACK, Piece.BLACK_KING_REPRESENTATION)
        );
    }

    private void verifyPiece(Piece piece, String color, String representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
