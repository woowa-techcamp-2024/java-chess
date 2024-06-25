package com.example.demo.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("킹 생성시 색상을 가진다.")
    @ParameterizedTest
    @MethodSource("colors")
    public void create(final Color color){
        Piece king = new King(color);
        assertThat(king.getColor()).isEqualTo(color);
    }

    private static Color[] colors() {
        return new Color[] {Color.WHITE, Color.BLACK};
    }

    @Test
    @DisplayName("색을 지정하지 않고 킹을 생성하면 흰색이다.")
    public void create_기본생성자() {
        Piece king = new King();
        assertThat(king.getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("검은 색 킹이라면 대문자 K를 출력한다.")
    public void print_black_pawn(){
        Piece king = new King(Color.BLACK);
        assertThat(king.toString()).isEqualTo("K");
    }

    @Test
    @DisplayName("흰 색 킹이라면 소문자 k를 출력한다.")
    public void print_white_pawn(){
        Piece king = new King(Color.WHITE);
        assertThat(king.toString()).isEqualTo("k");
    }
}