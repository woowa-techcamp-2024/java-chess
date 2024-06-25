package com.example.demo.context;

import com.example.demo.piece.Color;
import com.example.demo.piece.Pawn;
import com.example.demo.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("보드를 생성하면 폰의 초기 상태가 설정되어 있다.")
    public void create_pawn() {
        Board board = new Board();

        for(File file: File.values()){
            Piece black = board.getPiece(Rank.TWO, file);
            assertThat(black).isInstanceOf(Pawn.class);
            assertThat(black.getColor()).isEqualTo(Color.WHITE);

            Piece white = board.getPiece(Rank.SEVEN, file);
            assertThat(white).isInstanceOf(Pawn.class);
            assertThat(white.getColor()).isEqualTo(Color.BLACK);
        }
    }

    @Test
    @DisplayName("보드를 출력한다.")
    public void printBoard(){
        Board board = new Board();
        System.out.println(board);
    }
}