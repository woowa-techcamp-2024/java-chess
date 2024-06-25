package com.example.demo.handler;

import com.example.demo.context.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CliHandlerTest {

    ChessHandler handler = new ChessHandler();
    @Test
    @DisplayName("start 입력시 게임을 시작하고 보드의 상태를 출력한다.")
    public void handle_start(){

        // given
        String command = "start";
        Game game = mock();

        // when
        handler.handle(command, game);

        // then
        verify(game).start();
        verify(game).printBoard();
    }

    @Test
    @DisplayName("end 입력시 게임을 종료한다.")
    public void handle_end(){

        // given
        String command = "end";
        Game game = mock();

        // when
        handler.handle(command, game);

        // then
        verify(game).end();
    }

}