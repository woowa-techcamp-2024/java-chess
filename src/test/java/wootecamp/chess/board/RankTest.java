package wootecamp.chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wootecamp.chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {
    @Test
    @DisplayName("각 랭크의 초기 상태를 올바르게 생성하는지 확인한다.")
    void createInitialRank() {
        verifyInitialRank(Rank.createInitialRank1(), "rnbqkbnr");
        verifyInitialRank(Rank.createInitialRank2(), "pppppppp");
        verifyInitialRank(Rank.createEmptyRank(), "........");
        verifyInitialRank(Rank.createInitialRank7(), "PPPPPPPP");
        verifyInitialRank(Rank.createInitialRank8(), "RNBQKBNR");
    }

    private void verifyInitialRank(final Rank rank, final String shownRank) {
        assertThat(rank.showRank()).isEqualTo(shownRank);
    }

    @Test
    @DisplayName("랭크에 있는 전체 기물의 개수를 올바르게 세는지 확인한다.")
    void countAllPieces() {
        verifyPieceCount(Rank.createEmptyRank(), 0);
        verifyPieceCount(Rank.createInitialRank1(), 8);
    }

    private void verifyPieceCount(final Rank rank, final int expectedCount) {
        assertThat(rank.pieceCount()).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("랭크에 있는 특정 기물의 개수를 올바르게 세는지 확인한다.")
    void countParticularPieces() {
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.BLACK, Piece.Type.QUEEN, 0);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.BLACK, Piece.Type.PAWN, 0);

        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.PAWN, 0);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.ROOK, 2);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.KNIGHT, 2);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.BISHOP, 2);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.QUEEN, 1);
        verifyPieceCount(Rank.createInitialRank1(), Piece.Color.WHITE, Piece.Type.KING, 1);
    }

    private void verifyPieceCount(final Rank rank, final Piece.Color color, final Piece.Type type, final int expectedCount) {
        assertThat(rank.pieceCount(color,type)).isEqualTo(expectedCount);
    }
}
