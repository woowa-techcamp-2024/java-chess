package org.example;

import java.util.Scanner;
import org.example.chess.Board;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a command: ");
            String cmd = scanner.nextLine();

            if(cmd.equals("end")) {
                break;
            }else if (cmd.equals("start")) {
                // 게임 시작
                // 체스판 초기화
                Board board = new Board();
            }else{
                System.out.println("Invalid command");
            }
        }
    }
}