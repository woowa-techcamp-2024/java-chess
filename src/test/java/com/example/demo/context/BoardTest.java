package com.example.demo.context;

import com.example.demo.piece.*;
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
    @DisplayName("보드를 생성하면 킹의 초기 상태가 설정되어 있다.")
    public void create_kine(){
        Board board = new Board();
        Piece white = board.getPiece(Rank.ONE, File.D);
        assertThat(white).isInstanceOf(King.class);
        assertThat(white.getColor()).isEqualTo(Color.WHITE);

        Piece black = board.getPiece(Rank.EIGHT, File.D);
        assertThat(black).isInstanceOf(King.class);
        assertThat(black.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("보드를 생성하면 퀸의 초기 상태가 설정되어 있다.")
    public void create_queen(){
        Board board = new Board();
        Piece white = board.getPiece(Rank.ONE, File.E);
        assertThat(white).isInstanceOf(Queen.class);
        assertThat(white.getColor()).isEqualTo(Color.WHITE);

        Piece black = board.getPiece(Rank.EIGHT, File.E);
        assertThat(black).isInstanceOf(Queen.class);
        assertThat(black.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("보드를 출력한다.")
    public void printBoard(){
        Board board = new Board();
        System.out.println(board);
    }
}