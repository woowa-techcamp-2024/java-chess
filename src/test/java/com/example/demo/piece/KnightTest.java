package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @DisplayName("나이트 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color){
        Piece knight = new Knight(color, null, null);
        assertThat(knight.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 나이트을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Piece knight = new Knight(null, null);
        assertThat(knight.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 나이트이라면 대문자 N를 출력한다.")
    public void print_black(){
        Piece knight = new Knight(Color.BLACK, null, null);
        assertThat(knight.toString()).isEqualTo("N");
    }

    @Test
    @DisplayName("흰 색 나이트이라면 소문자 n를 출력한다.")
    public void print_white(){
        Piece knight = new Knight(Color.WHITE, null, null);
        assertThat(knight.toString()).isEqualTo("n");
    }

    @Test
    @DisplayName("나이트의 점수는 2.5점이다.")
    public void score(){
        Piece knight = new Knight(null, null);
        assertThat(knight.getPoint()).isEqualTo(2.5f);
    }
}