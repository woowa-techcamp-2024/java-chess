package com.woowatechcamp.chess.game;

import com.woowatechcamp.chess.pieces.Piece;

import static org.assertj.core.api.Assertions.*;

import com.woowatechcamp.chess.pieces.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessGameTest {
    private ChessGame chessGame;

    @BeforeEach
    public void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    public void 게임_초기화_시_백색이_선턴() {
        // 첫 번째 턴에 백색 폰을 움직일 수 있는지 확인
        assertThatCode(() -> chessGame.move(new Position("e2"), new Position("e4")))
                .doesNotThrowAnyException();
    }

    @Test
    public void 자신의_턴이_아닐_때_이동하면_예외발생() {
        // 백색이 먼저 이동
        chessGame.move(new Position("e2"), new Position("e4"));

        // 백색이 연속으로 이동하려고 하면 예외 발생
        assertThatThrownBy(() -> chessGame.move(new Position("d2"), new Position("d4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("It's not your turn");
    }

    @Test
    public void 빈_위치에서_말을_이동하려고_하면_예외발생() {
        assertThatThrownBy(() -> chessGame.move(new Position("e3"), new Position("e4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("It's not your turn");
    }

    @Test
    public void 체크메이트_시_게임_종료() {
        // 백색 폰을 전진시켜 흑색 퀸의 경로를 열어줍니다.
        chessGame.move(new Position("f2"), new Position("f3"));
        chessGame.move(new Position("e7"), new Position("e5"));

        // 백색 폰을 한 칸 더 전진시킵니다.
        chessGame.move(new Position("g2"), new Position("g4"));
        assertThat(chessGame.isFinish()).isFalse();

        // 흑색 퀸을 공격 위치로 이동하여 체크메이트를 만듭니다. (Fool's Mate)
        chessGame.move(new Position("d8"), new Position("h4"));

        // 체크메이트가 되었으므로 게임이 종료되어야 합니다.
        assertThat(chessGame.isFinish()).isTrue();
        assertThat(chessGame.getWinner()).isEqualTo(Piece.Color.BLACK);
    }

    @Test
    public void 턴이_올바르게_변경되는지_확인() {
        chessGame.move(new Position("e2"), new Position("e4")); // 백색 이동
        assertThatCode(() -> chessGame.move(new Position("e7"), new Position("e5"))) // 흑색 이동
                .doesNotThrowAnyException();
        assertThatCode(() -> chessGame.move(new Position("d2"), new Position("d4"))) // 다시 백색 이동
                .doesNotThrowAnyException();
    }
}
