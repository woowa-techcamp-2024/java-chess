package com.woopaca.javachess.chess;

import com.woopaca.javachess.chess.pieces.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 테스트")
public class BoardTest {

    @DisplayName("체스판에 폰을 추가할 수 있다.")
    @Test
    void create() {
        Board board = new Board();

        Piece whitePiece = Piece.createWhitePawn();
        Piece blackPiece = Piece.createBlackPawn();

        board.add(whitePiece);
        assertThat(board.size()).isEqualTo(1);
        assertThat(board.findPawn(0)).isEqualTo(whitePiece);

        board.add(blackPiece);
        assertThat(board.size()).isEqualTo(2);
        assertThat(board.findPawn(1)).isEqualTo(blackPiece);
    }

    @DisplayName("체스판을 초기화하면 흰색 폰과 검은색 폰이 추가된다.")
    @Test
    void initialize() {
        Board board = new Board();
        board.initialize();
        assertThat(board.getWhitePawnsResult()).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(board.getBlackPawnsResult()).isEqualTo("♟♟♟♟♟♟♟♟");

        System.out.println(board.print());
    }

}
