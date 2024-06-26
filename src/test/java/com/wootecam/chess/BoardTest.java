package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.appendNewLine;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.createBlackOtherPieces());
        ranks.add(Rank.createPawns(Color.BLACK));
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createBlanks());
        ranks.add(Rank.createPawns(Color.WHITE));
        ranks.add(Rank.createWhiteOtherPieces());
        CoordinatesExtractor extractor = new CoordinatesExtractor();

        board = new Board(ranks, extractor);
    }

    @Nested
    class showBoard_메소드는 {

        @Nested
        class 보드를_생성하고_호출하면 {

            @Test
            void 현재_보드의_상태를_문자열로_반환한다() {
                // given
                String expectedResults = generateDefaultBoardResults();

                // when
                String currentBoardResults = board.showBoard();

                // then
                assertThat(currentBoardResults).isEqualTo(expectedResults);
            }

            private String generateDefaultBoardResults() {
                String blankRank = appendNewLine("........");

                return appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr");
            }
        }
    }

    @Nested
    class pieceCount_메소드는 {

        @Nested
        class 보드를_생성하고_호출하면 {

            @Test
            void 현재_보드에_존재하는_기물의_갯수를_반환한다() {
                // when
                int count = board.countBoardPieces();

                // then
                assertThat(count).isEqualTo(32);
            }
        }
    }

    @Nested
    class countSpecificBoardPieces_메소드는 {

        @Test
        void 특정_색상과_타입을_가진_모든_Piece의_개수를_카운팅한다() {
            // when
            int blackPawnCount = board.countSpecificBoardPieces(Color.BLACK, Type.PAWN);
            int whiteBishopCount = board.countSpecificBoardPieces(Color.WHITE, Type.BISHOP);

            // then
            assertAll(
                    () -> assertThat(blackPawnCount).isEqualTo(8),
                    () -> assertThat(whiteBishopCount).isEqualTo(2)
            );
        }
    }

    @Nested
    class findPiece_메소드는 {

        @Test
        void 주어진_위치의_기물을_조회한다() {
            // expect
            assertAll(
                    () -> assertThat(board.findPiece("a8")).isEqualTo(Piece.createBlack(Type.ROOK)),
                    () -> assertThat(board.findPiece("h8")).isEqualTo(Piece.createBlack(Type.ROOK)),
                    () -> assertThat(board.findPiece("a1")).isEqualTo(Piece.createWhite(Type.ROOK)),
                    () -> assertThat(board.findPiece("h1")).isEqualTo(Piece.createWhite(Type.ROOK))
            );
        }

        @Nested
        class 만약_8x8_크기의_체스판을_벗어나는_좌표를_입력하면 {

            @ParameterizedTest
            @ValueSource(strings = {"a9", "a0", "i5"})
            void 예외가_발생한다(String invalidCoordinate) {
                // expect
                assertThatThrownBy(() -> board.findPiece(invalidCoordinate))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("8 x 8 크기의 체스판의 범위를 벗어나는 좌표입니다. coordinate = " + invalidCoordinate);
            }
        }
    }

    @Nested
    class initializeEmpty_메소드는 {

        @BeforeEach
        void setUp() {
            List<Rank> ranks = new ArrayList<>();
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            CoordinatesExtractor extractor = new CoordinatesExtractor();

            board = new Board(ranks, extractor);
        }

        @Test
        void 아무것도_없는_체스판을_생성한다() {
            // when
            int count = board.countBoardPieces();

            // then
            assertThat(count).isZero();
        }
    }

    @Nested
    class move_메소드는 {

        @BeforeEach
        void setUp() {
            List<Rank> ranks = new ArrayList<>();
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            CoordinatesExtractor extractor = new CoordinatesExtractor();

            board = new Board(ranks, extractor);
        }

        @Test
        void 입력한_좌표로_기물을_이동시킨다() {
            // given
            Piece piece = Piece.createBlack(Type.ROOK);
            String coordinates = "b5";

            // when
            board.move(coordinates, piece);

            // then
            assertThat(board.findPiece(coordinates)).isEqualTo(piece);
        }
    }

    @Nested
    class calculatePoint_메소드는 {

        @BeforeEach
        void setUp() {
            List<Rank> ranks = new ArrayList<>();
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            ranks.add(Rank.createBlanks());
            CoordinatesExtractor extractor = new CoordinatesExtractor();

            board = new Board(ranks, extractor);
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
            double blackPoint = board.calculatePoint(Color.BLACK);
            double whitePoint = board.calculatePoint(Color.WHITE);

            // then
            assertAll(
                    () -> assertThat(blackPoint).isEqualTo(15.0),
                    () -> assertThat(whitePoint).isEqualTo(7.0)
            );
        }

        private void addPiece(String position, Piece piece) {
            board.move(position, piece);
        }
    }
}
