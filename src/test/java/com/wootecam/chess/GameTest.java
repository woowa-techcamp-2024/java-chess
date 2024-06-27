package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Rank;
import com.wootecam.chess.pieces.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    private Board board;

    private CoordinatesExtractor extractor;

    @BeforeEach
    void setUp() {
        board = initialize();
        extractor = new CoordinatesExtractor();

        game = new Game(board, extractor);
    }

    private Board initialize() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());

        return new Board(ranks);
    }

    private Board initializeEmpty() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());

        return new Board(ranks);
    }

    @Nested
    class move_메소드는 {

        @Test
        void 시작좌표에서_도착좌표로_기물을_이동한다() {
            // given
            String startCoordinates = "b2";
            String targetCoordinates = "b3";

            // when
            game.move(startCoordinates, targetCoordinates);

            // then
            assertAll(
                    () -> assertThat(board.findPiece(6, 1)).isEqualTo(Piece.createBlank()),
                    () -> assertThat(board.findPiece(5, 1)).isEqualTo(Piece.createWhite(Type.PAWN))
            );
        }

        @Nested
        class 빈칸을_이동시키려_하면 {

            @Test
            void 예외가_발생한다() {
                // given
                String startCoordinates = "b3";
                String targetCoordinates = "b2";

                // expect
                assertThatThrownBy(() -> game.move(startCoordinates, targetCoordinates))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("빈칸은 이동시킬 수 없습니다.");
            }
        }
    }

    @Nested
    class calculatePoint_메소드는 {

        @BeforeEach
        void setUp() {
            board = initializeEmpty();
            game = new Game(board, extractor);
        }

        @Test
        void 검정_혹은_흰색_기물의_점수를_계산한다() {
            // given
            addPiece("b6", Piece.createBlack(Type.PAWN));
            addPiece("e6", Piece.createBlack(Type.QUEEN));
            addPiece("b8", Piece.createBlack(Type.KING));
            addPiece("c8", Piece.createBlack(Type.ROOK));
            addPiece("f2", Piece.createWhite(Type.PAWN));
            addPiece("g2", Piece.createWhite(Type.PAWN));
            addPiece("e1", Piece.createWhite(Type.ROOK));
            addPiece("f1", Piece.createWhite(Type.KING));

            // when
            double blackPoint = game.calculatePoint(Color.BLACK);
            double whitePoint = game.calculatePoint(Color.WHITE);

            // then
            assertAll(
                    () -> assertThat(blackPoint).isEqualTo(15.0),
                    () -> assertThat(whitePoint).isEqualTo(7.0)
            );
        }

        private void addPiece(String position, Piece piece) {
            int rowIndex = extractor.extractRowIndex(position);
            int columnIndex = extractor.extractColumnIndex(position);

            board.updatePiece(rowIndex, columnIndex, piece);
        }
    }
}
