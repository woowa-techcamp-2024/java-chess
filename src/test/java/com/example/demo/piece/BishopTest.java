package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color){
        Piece bishop = new Bishop(color);
        assertThat(bishop.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 비숍을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Piece bishop = new Bishop();
        assertThat(bishop.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 비숍이라면 대문자 B를 출력한다.")
    public void print_black(){
        Piece bishop = new Bishop(Color.BLACK);
        assertThat(bishop.toString()).isEqualTo("B");
    }

    @Test
    @DisplayName("흰 색 비숍이라면 소문자 b를 출력한다.")
    public void print_white(){
        Piece bishop = new Bishop(Color.WHITE);
        assertThat(bishop.toString()).isEqualTo("b");
    }

    @Test
    @DisplayName("비숍의 점수는 3점이다.")
    public void score(){
        Piece bishop = new Bishop();
        assertThat(bishop.getPoint()).isEqualTo(3);
    }
}