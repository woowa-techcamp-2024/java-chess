package com.example.demo.handler;

import com.example.demo.context.Game;

public class ChessHandler {
    public void handle(String input, Game game) {
        if (input.equals("start")) {
            System.out.println("게임을 시작합니다.");
            game.start();
            game.printBoard();
        } else if (input.equals("end")) {
            System.out.println("게임을 종료합니다.");
            game.end();
        } else {
            System.out.println("다시 입력해주세요.");
        }
    }

}
