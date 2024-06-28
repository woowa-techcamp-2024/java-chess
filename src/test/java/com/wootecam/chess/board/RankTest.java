package com.wootecam.chess.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.King;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Rook;
import com.wootecam.chess.pieces.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void Rank에_포함되는_Pawn_집합을_만들_수_있다() {
        // when
        Rank pawns = Rank.createPawns(Color.WHITE);

        // then
        assertAll(
                () -> assertThat(pawns.countPieces()).isEqualTo(8),
                () -> assertThat(pawns.createResults()).isEqualTo("pppppppp")
        );
    }

    @Test
    void Rank에_포함되는_Pawn을_제외한_기물_집합을_만들_수_있다() {
        // when
        Rank whiteOtherPieces = Rank.createWhiteOtherPieces();

        // then
        assertAll(
                () -> assertThat(whiteOtherPieces.countPieces()).isEqualTo(8),
                () -> assertThat(whiteOtherPieces.createResults()).isEqualTo("rnbqkbnr")
        );
    }

    @Nested
    class Blank인_Rank집합은 {

        private Rank blanks;

        @BeforeEach
        void setUp() {
            blanks = Rank.createBlanks();
        }

        @Test
        void piece개수를_셀때_제외된다() {
            // then
            assertThat(blanks.countPieces()).isEqualTo(0);
        }

        @Test
        void 아무_기물도_표시하지_않는다() {
            // then
            assertThat(blanks.createResults()).isEqualTo("........");
        }

    }

    @Test
    void 특정_type과_color를_가진_piece_개수를_찾을_수_있다() {
        // given
        Rank blackOtherPieces = Rank.createBlackOtherPieces();

        // when
        int count = blackOtherPieces.countSpecificPieces(Color.BLACK, Type.BISHOP);

        // then
        assertThat(count).isEqualTo(2);
    }

    @Test
    void 열_좌표에_해당되는_piece를_찾을_수_있다() {
        // given
        Rank blackOtherPieces = Rank.createBlackOtherPieces();

        // when
        Piece piece = blackOtherPieces.findPieceByColumn(0);

        // then
        assertThat(piece).isEqualTo(new Rook(Color.BLACK));
    }

    @Test
    void 특정_열_좌표에_기물을_놓을_수_있다() {
        // given
        Rank rank = Rank.createBlanks();

        // when
        Rank replacedRank = rank.placePiece(0, new King(Color.BLACK));

        // then
        assertThat(replacedRank)
                .extracting(newRank -> newRank.findPieceByColumn(0))
                .isEqualTo(new King(Color.BLACK));
    }

    @Test
    void 특정_Rank에_포함되는_모든_폰을_제외한_기물의_점수를_구한다() {
        // given
        Rank rank = Rank.createWhiteOtherPieces();

        // when
        double point = rank.calculateRankPiecesPoint(Color.WHITE);

        // then
        assertThat(point).isEqualTo(30);
    }
}
