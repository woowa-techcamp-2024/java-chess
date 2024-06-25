package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @DisplayName("퀀 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color){
        Piece queen = new Queen(color);
        assertThat(queen.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 퀀을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Piece queen = new Queen();
        assertThat(queen.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 퀀이라면 대문자 q를 출력한다.")
    public void print_black(){
        Piece queen = new Queen(Color.BLACK);
        assertThat(queen.toString()).isEqualTo("Q");
    }

    @Test
    @DisplayName("흰 색 퀀이라면 소문자 q를 출력한다.")
    public void print_white(){
        Piece queen = new Queen(Color.WHITE);
        assertThat(queen.toString()).isEqualTo("q");
    }

}