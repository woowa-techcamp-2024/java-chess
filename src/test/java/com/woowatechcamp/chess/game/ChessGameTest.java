package com.woowatechcamp.chess.game;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.woowatechcamp.chess.pieces.Position;

public class ChessGameTest {

    private ChessGame chessGame;

    @Before
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
    public void 킹이_잡히면_게임_종료() {
        // 백색 폰을 전진시켜 흑색 퀸의 경로를 열어줍니다.
        chessGame.move(new Position("e2"), new Position("e4"));
        chessGame.move(new Position("e7"), new Position("e5"));

        // 백색 나이트를 밖으로 빼냅니다.
        chessGame.move(new Position("g1"), new Position("f3"));
        assertThat(chessGame.isFinish()).isFalse();

        // 흑색 퀸을 공격 위치로 이동합니다.
        chessGame.move(new Position("d8"), new Position("h4"));
        assertThat(chessGame.isFinish()).isFalse();

        // 백색이 체크 상태를 무시하고 다른 말을 움직입니다.
        chessGame.move(new Position("d2"), new Position("d3"));
        assertThat(chessGame.isFinish()).isFalse();

        // 흑색 퀸이 백색 폰을 잡고 f2로 이동합니다.
        chessGame.move(new Position("h4"), new Position("e4"));
        assertThat(chessGame.isFinish()).isFalse();

        // 백색이 다시 체크 상태를 무시하고 다른 말을 움직입니다.
        chessGame.move(new Position("c2"), new Position("c3"));
        assertThat(chessGame.isFinish()).isFalse();

        // 흑색 퀸이 백색 킹을 잡습니다.
        chessGame.move(new Position("e4"), new Position("e1"));
        // 킹이 잡혔으므로 게임이 종료되어야 합니다.
        assertThat(chessGame.isFinish()).isTrue();
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
