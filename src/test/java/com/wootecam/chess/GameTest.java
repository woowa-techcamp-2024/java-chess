package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.board.Rank;
import com.wootecam.chess.pieces.Blank;
import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.King;
import com.wootecam.chess.pieces.Pawn;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.pieces.Position;
import com.wootecam.chess.pieces.Queen;
import com.wootecam.chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameTest {

    private Game game;

    private Board board;

    private PieceMoveVerifier verifier;

    private CoordinatesExtractor extractor;

    @BeforeEach
    void setUp() {
        verifier = new PieceMoveVerifier();
        board = initialize();
        extractor = new CoordinatesExtractor();
        game = new Game(board, extractor, verifier);
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
                    () -> assertThat(board.findPiece(new Position(6, 1))).isEqualTo(new Blank()),
                    () -> assertThat(board.findPiece(new Position(5, 1))).isEqualTo(new Pawn(Color.WHITE))
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
            game = new Game(board, extractor, verifier);
        }

        @Test
        void 검정_혹은_흰색_기물의_점수를_계산한다() {
            // given
            addPiece("b6", new Pawn(Color.BLACK));
            addPiece("e6", new Queen(Color.BLACK));
            addPiece("b8", new King(Color.BLACK));
            addPiece("c8", new Rook(Color.BLACK));
            addPiece("f2", new Pawn(Color.WHITE));
            addPiece("g2", new Pawn(Color.WHITE));
            addPiece("e1", new Rook(Color.WHITE));
            addPiece("f1", new King(Color.WHITE));

            // when
            double blackPoint = game.calculatePoint(Color.BLACK);
            double whitePoint = game.calculatePoint(Color.WHITE);

            // then
            assertAll(
                    () -> assertThat(blackPoint).isEqualTo(15.0),
                    () -> assertThat(whitePoint).isEqualTo(7.0)
            );
        }

        private void addPiece(String coordinates, Piece piece) {
            Position position = extractor.extractPosition(coordinates);
            board.updatePiece(position, piece);
        }
    }
}
