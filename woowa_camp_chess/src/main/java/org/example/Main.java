package org.example;

import org.example.chess.board.Board;

import java.util.Scanner;

public class Main {
    static Board board;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("체스 게임에 오신것을 환영합니다.");
        while (true){
            System.out.println("원하시는 값을 입력하여 주시기 바랍니다.");
            System.out.println("시작 : start, 종료 : end");
            String input = scan.next();

            if(input.equals("start")){
                // 전역 변수에 새로운 Board 객체를 할당함
                board = new Board();
                board.initialize();
            }

            if(input.equals("end")) break;
        }

    }
}