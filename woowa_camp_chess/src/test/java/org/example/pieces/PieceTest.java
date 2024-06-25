package org.example.pieces;
import org.example.chess.pieces.Piece;
import org.example.chess.pieces.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chess.pieces.Piece.*;
import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    @Test
    @DisplayName("기본 생성자로 폰이 생성되어야 한다")
    public void create_기본생성자() {
        Piece piece = new Piece();
        assertEquals(WHITE_COLOR, piece.getColor());
        assertEquals(WHITE_REPRESENTATION, piece.getRepresentation());
    }

    @Test
    @DisplayName("올바른 색의 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(BLACK_COLOR);
        verifyPawn(WHITE_COLOR);
    }

    private static void verifyPawn(final String color) {
        Piece piece1 = new Piece(color, PieceType.PAWN);
        assertThat(piece1.getColor()).isEqualTo(color);
        assertThat(piece1.getRepresentation()).isEqualTo(color.equals(Piece.BLACK_COLOR) ? PieceType.PAWN.getAbbreviation() : (char) (PieceType.PAWN.getAbbreviation() -32 ));
    }
}