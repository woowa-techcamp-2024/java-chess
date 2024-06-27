package com.seong.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Blank;
import com.seong.chess.pieces.King;
import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
import com.seong.chess.pieces.Queen;
import com.seong.chess.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Nested
    @DisplayName("move 호출 시")
    class Move {

        @BeforeEach
        void setUp() {
            chessGame.initialize();
        }

        @Test
        @DisplayName("체스 보드의 기물은 현재 위치에서 다른 위치로 이동할 수 있다.")
        public void move() {
            //given
            String sourcePosition = "b2";
            String targetPosition = "b3";

            //when
            chessGame.move(sourcePosition, targetPosition);

            //then
            assertEquals(Blank.create(), board.findPiece(sourcePosition));
            assertEquals(Pawn.createWhite(), board.findPiece(targetPosition));
        }

        @Test
        @DisplayName("예외(IllegalArgument): 이동할 위치에 같은 팀의 기물이 있으면")
        public void equalsColor() {
            //given
            String sourcePosition = "a1";
            String targetPosition = "b1";

            //when
            Exception exception = catchException(() -> chessGame.move(sourcePosition, targetPosition));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("예외(IllegalArgument): 기물의 이동 규칙을 따르지 않는다면")
        public void ignoreConcept() {
            //given
            String sourcePosition = "a2";
            String targetPosition = "a1";

            //when
            Exception exception = catchException(() -> chessGame.move(sourcePosition, targetPosition));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("예외(IllegalArgument): 기물의 이동 위치가 현재 위치와 같다면")
        public void samePosition() {
            //given
            String sourcePosition = "a1";
            String targetPosition = "a1";

            //when
            Exception exception = catchException(() -> chessGame.move(sourcePosition, targetPosition));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("체스판 위의 기물에 따라 점수를 계산할 수 있다.")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Pawn.createBlack());
        addPiece("e6", Queen.createBlack());
        addPiece("b8", King.createBlack());
        addPiece("c8", Rook.createBlack());

        addPiece("f2", Pawn.createWhite());
        addPiece("g2", Pawn.createWhite());
        addPiece("e1", Rook.createWhite());
        addPiece("f1", King.createWhite());

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Nested
    @DisplayName("checkRightPlayerTurn 호출 시")
    class CheckRightPlayerTurn {

        @BeforeEach
        void setUp() {
            board.initialize();
        }

        @Test
        @DisplayName("올바른 플레이어의 턴인지 검사할 수 있다.")
        void rightPlayerTurn() {
            //given
            Turn turn = Turn.start();
            String sourcePosition = "a2";

            //when
            Exception exception = catchException(() -> chessGame.checkRightPlayerTurn(turn, sourcePosition));

            //then
            assertThat(exception).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("예외(IllegalArgument): 해당 플레이어의 턴이 아닐 때")
        void notYourTour() {
            //given
            Turn turn = Turn.start();
            String sourcePosition = "a7";

            //when
            Exception exception = catchException(() -> chessGame.checkRightPlayerTurn(turn, sourcePosition));

            //then
            assertThat(exception).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
