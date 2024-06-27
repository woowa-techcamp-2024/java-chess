package com.seong.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seong.chess.pieces.Direction;
import com.seong.chess.pieces.King;
import com.seong.chess.pieces.Piece;
import com.seong.chess.pieces.Piece.Color;
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
            assertEquals(Piece.createBlank(), board.findPiece(sourcePosition));
            assertEquals(Piece.createWhitePawn(), board.findPiece(targetPosition));
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

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, chessGame.calculatePoint(Color.BLACK), 0.01);
        assertEquals(7.0, chessGame.calculatePoint(Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    @Test
    @DisplayName("체스 게임의 기물을 움직일 수 있다.")
    public void moveChessPieces() {
        //given
        Board onlyKing = new Board();
        ChessGame kingChessGame = new ChessGame(onlyKing);
        onlyKing.initializeEmpty();
        onlyKing.move("a2", King.createBlack());

        //when
        kingChessGame.move("a2", Direction.NORTH, 1);

        //then
        assertThat(onlyKing.findPiece("a3")).isEqualTo(King.createBlack());
    }
}
