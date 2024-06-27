package com.wootecam.chess;

import static org.assertj.core.api.Assertions.assertThat;
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

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = initialize();
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
    class countBoardPieces_메소드는 {

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
                    () -> assertThat(board.findPiece(new Position(0, 0))).isEqualTo(Piece.createBlack(Type.ROOK)),
                    () -> assertThat(board.findPiece(new Position(0, 7))).isEqualTo(Piece.createBlack(Type.ROOK)),
                    () -> assertThat(board.findPiece(new Position(7, 0))).isEqualTo(Piece.createWhite(Type.ROOK)),
                    () -> assertThat(board.findPiece(new Position(7, 7))).isEqualTo(Piece.createWhite(Type.ROOK))
            );
        }
    }

    @Nested
    class initializeEmpty_메소드는 {

        @BeforeEach
        void setUp() {
            board = initializeEmpty();
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
    class updatePiece_메소드는 {

        @BeforeEach
        void setUp() {
            board = initialize();
        }

        @Test
        void 해당_좌표에_기물을_세운다() {
            // given
            Position position = new Position(0, 0);

            // when
            board.updatePiece(position, Piece.createWhite(Type.PAWN));

            // then
            assertThat(board.findPiece(new Position(0, 0))).isEqualTo(Piece.createWhite(Type.PAWN));
        }
    }

    @Nested
    class findDescOrderedPieces_메소드는 {

        @BeforeEach
        void setUp() {
            List<Rank> ranks = List.of(new Rank(List.of(
                    Piece.createWhite(Type.ROOK), Piece.createBlack(Type.ROOK), Piece.createWhite(Type.QUEEN),
                    Piece.createBlack(Type.ROOK), Piece.createBlack(Type.ROOK), Piece.createBlack(Type.ROOK),
                    Piece.createBlack(Type.ROOK), Piece.createBlack(Type.ROOK)))
            );
            board = new Board(ranks);
        }

        @Test
        void 지정한_색상의_기물을_점수_내림차순으로_조회한다() {
            // when
            List<Piece> descOrderedPieces = board.findDescOrderedPieces(Color.WHITE);

            // then
            assertThat(descOrderedPieces).containsExactly(Piece.createWhite(Type.QUEEN), Piece.createWhite(Type.ROOK));
        }
    }
}
