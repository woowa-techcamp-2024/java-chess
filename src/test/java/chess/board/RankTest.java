package chess.board;

import chess.pieces.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {

    @DisplayName("현재 랭크의 모든 기물들을 반환할 수 있다.")
    @Test
    void getAllPieces() {
        // given
        List<Piece> initialPieces = List.of(
                Blank.createBlank(),
                King.createWhiteKing(),
                Bishop.createWhiteBishop(),
                Blank.createBlank(),
                Queen.createWhiteQueen(),
                Rook.createWhiteRook(),
                Blank.createBlank(),
                Pawn.createWhitePawn()
        );
        Rank rank = Rank.initializeRank(initialPieces);

        // when
        List<Piece> pieces = rank.getAllPieces(Piece.Color.WHITE);

        // then
        List<Piece> pieceList = initialPieces.stream().filter(piece -> !piece.isBlank())
                .toList();

        assertThat(pieces)
                .hasSize(5)
                .containsExactlyElementsOf(pieceList);
    }

    @DisplayName("랭크를 생성할 수 있다")
    @Test
    void createRank() {
        // given
        List<? extends Piece> pieces = IntStream.range(0, 8)
                .mapToObj(i -> Blank.createBlank())
                .toList();

        // when
        Rank rank = Rank.initializeRank(pieces);

        // then
        assertThat(rank).isNotNull();
    }

    @DisplayName("8개 이상의 Piece로 랭크를 생성하려 하면 예외가 발생한다.")
    @Test
    void createRankWithMoreThan8Pieces() {
        // given
        List<? extends Piece> pieces = IntStream.range(0, 9)
                .mapToObj(i -> Blank.createBlank())
                .toList();

        // when & then
        assertThatThrownBy(() -> Rank.initializeRank(pieces))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 랭크는 8개의 말을 가집니다.");
    }

    @DisplayName("8개 미만의 Piece로 랭크를 생성하려 하면 예외가 발생한다.")
    @Test
    void createRankWithLessThan8Pieces() {
        // given
        List<? extends Piece> pieces = IntStream.range(0, 7)
                .mapToObj(i -> Blank.createBlank())
                .toList();

        // when & then
        assertThatThrownBy(() -> Rank.initializeRank(pieces))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 랭크는 8개의 말을 가집니다.");
    }

    @DisplayName("랭크에서 piece의 개수를 반환할 수 있다")
    @Test
    void getTotalPieceCount() {
        // given
        List<Piece> pieces = List.of(
                Blank.createBlank(),
                King.createWhiteKing(),
                Bishop.createBlackBishop(),
                Blank.createBlank(),
                Queen.createWhiteQueen(),
                Rook.createBlackRook(),
                Blank.createBlank(),
                Pawn.createBlackPawn()
        );

        Rank rank = Rank.initializeRank(pieces);

        // when
        int pieceCount = rank.getTotalPieceCount();

        // then
        assertEquals(5, pieceCount);
    }

}