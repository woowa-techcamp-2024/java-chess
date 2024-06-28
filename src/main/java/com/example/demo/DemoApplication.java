package com.example.demo;

import com.example.demo.context.Game;
import com.example.demo.handler.ChessHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

        final Scanner input = new Scanner(System.in);
        ChessHandler cliHandler = new ChessHandler();

        Game game = Game.builder().build();

        while (!game.isEnd()) {
            cliHandler.handle(input.next(), game);
            game.calculateCheckPoint();
        }
    }

}
