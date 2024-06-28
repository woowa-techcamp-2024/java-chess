package org.example;

import org.example.chess.Game.GameImpl;
import org.example.chess.board.Board;
import org.example.chess.board.BoardView;
import org.example.chess.board.PointManager;

import java.util.Scanner;

public class Main {
    static GameImpl game;
    static Board board ;
    static PointManager pointManager;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("체스 게임에 오신것을 환영합니다.");

        while (true){
            System.out.println("원하시는 값을 입력하여 주시기 바랍니다.");
            System.out.println("시작 : start, 종료 : end, 말 이동 : move");
            String input = scan.next();
            switch (input){
                case "start" :
                    // 전역 변수에 새로운 Board 객체를 할당함
                    board = new Board();
                    board.initialize();
                    game = new GameImpl(board);
                    pointManager = new PointManager(board);
                    break;

                case "move" :
                    System.out.print("선택 포지션 : ");
                    String srcPosition = scan.next();
                    System.out.print("이동 포지션 : ");
                    String desPosition = scan.next();
                    try {
                        game.move(srcPosition, desPosition);
                        BoardView.viewBoard(board,pointManager);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "end" : return;
            }
        }

    }
}