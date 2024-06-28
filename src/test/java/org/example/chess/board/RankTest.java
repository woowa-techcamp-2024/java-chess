package org.example.chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.chess.pieces.Piece.Type;
import org.example.chess.pieces.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankTest {

    private Board.Rank rank;

    @BeforeEach
    public void setUp() {
        rank = new Board.Rank();
        rank.addPiece(PieceFactory.createBlackBishop());
        rank.addPiece(PieceFactory.createBlackBishop());
        rank.addPiece(PieceFactory.createBlackPawn());

        rank.addPiece(PieceFactory.createWhiteBishop());
        rank.addPiece(PieceFactory.createWhiteQueen());
    }

    @Test
    void testCountWhitePiecesWithType() {
        assertThat(rank.countWhitePiecesWithType(Type.BISHOP)).isEqualTo(1);
        assertThat(rank.countWhitePiecesWithType(Type.QUEEN)).isEqualTo(1);
        assertThat(rank.countWhitePiecesWithType(Type.PAWN)).isEqualTo(0);
    }

    @Test
    void testCountBlackPiecesWithType() {
        assertThat(rank.countBlackPiecesWithType(Type.BISHOP)).isEqualTo(2);
        assertThat(rank.countBlackPiecesWithType(Type.PAWN)).isEqualTo(1);
        assertThat(rank.countBlackPiecesWithType(Type.QUEEN)).isEqualTo(0);
    }

}